var submitCodeApp = angular.module('SubmitCodeApp', []);

submitCodeApp.factory('ShowDiv', function() {
    var showDiv = {}
    showDiv.congTitle = true
    showDiv.fbShare = true
    showDiv.submitSpinner = false
    showDiv.fbSpinner = false
    showDiv.requestMessage = ''
    return showDiv
})

submitCodeApp.factory('PopUps', ['$modal', function ($modal) {
    var popupModal = {
        submitUniqueCode : function (successCallback, dismissCallback) {
            var modalInstance = $modal.open({
                templateUrl: 'templates/submitCodeModal.gsp',
                controller: SubmitCodePopupCtrl,
                backdrop: 'static',
                keyboard: false
            });
            modalInstance.result.then(successCallback, dismissCallback);
        },
        privacyPolicy : function (successCallback, dismissCallback) {
            var modalInstance = $modal.open({
                templateUrl: 'templates/privacyPolicyModal.gsp',
                controller: PrivacyPolicyPopupCtrl,
                backdrop: 'static',
                keyboard: false
            });
            modalInstance.result.then(successCallback, dismissCallback);
        },
        termsAndCondition : function (successCallback, dismissCallback) {
            var modalInstance = $modal.open({
                templateUrl: 'templates/termsAndConditionModal.gsp',
                controller: TermsAndConditionPopupCtrl,
                backdrop: 'static',
                keyboard: false
            });
            modalInstance.result.then(successCallback, dismissCallback);
        },
        popupAlert : function (successCallback, dismissCallback) {
            var modalInstance = $modal.open({
                templateUrl: 'templates/popupAlert.gsp',
                controller: AlertPopupCtrl,
                backdrop: 'static',
                keyboard: false
            });
            modalInstance.result.then(successCallback, dismissCallback);
        },
        submitCodeMsg : function (successCallback, dismissCallback) {
            var modalInstance = $modal.open({
                templateUrl: 'templates/submitCodeMsgModal.gsp',
                controller: SubmitCodeMsgCtrl,
                backdrop: 'static',
                keyboard: false
            });
            modalInstance.result.then(successCallback, dismissCallback);
        }
    }
    return popupModal
}]);

submitCodeApp.controller('SubmitCodeCtrl', ['$scope', '$http', 'PopUps', 'ShowDiv', function ($scope, $http, PopUps, ShowDiv) {
    $scope.showDiv = ShowDiv
    $scope.openSubmitUniqueCode = function() {
        PopUps.submitUniqueCode(function (result) {
            if(result){
                if(result.next == "OPEN_PRIVACY_POLICY") {
                    PopUps.privacyPolicy(function(){
                        $scope.openSubmitUniqueCode();
                    })
                } else if(result.next == "OPEN_TERMS_AND_CONDITION") {
                    PopUps.termsAndCondition(function(){
                        $scope.openSubmitUniqueCode();
                    })
                } else if(result.next == "OPEN_POPUP_ALERT") {
                    PopUps.popupAlert(function(){
                        $scope.openSubmitCodeMsg();
                    })
                }
            }
        }, function () {

        })
    }
    $scope.openSubmitCodeMsg = function() {
        PopUps.submitCodeMsg(function () {

        }, function () {

        })
    }
}]);

var SubmitCodePopupCtrl = ['$scope', '$modalInstance', '$http', 'ShowDiv', function ($scope, $modalInstance, $http, ShowDiv) {
    $scope.uCode = {}
    $scope.accepted = false
    $scope.showDiv = ShowDiv
    $scope.showDiv.requestMessage = ''

    $scope.submitCode = function () {
        $scope.showDiv.submitSpinner = true
        var promise = $http.post('/user/submitCode', $scope.uCode);
        promise.then(function (promise) {
            $scope.showDiv.submitSpinner = false
            $scope.showDiv.requestMessage = promise.data.msg
            $scope.showDiv.congTitle = promise.data.success
            $scope.showDiv.fbShare = promise.data.shared
            $modalInstance.close({next: "OPEN_POPUP_ALERT"});
        });
    };
    $scope.closeUniqueCodePopUp = function() {
        $modalInstance.close();
    }

    $scope.showPrivacyPolicyPopUp = function() {
        $modalInstance.close({next: "OPEN_PRIVACY_POLICY"});
    }

    $scope.showTermsAndConditionsPopUp = function() {
        $modalInstance.close({next: "OPEN_TERMS_AND_CONDITION"});
    }

}];

var PrivacyPolicyPopupCtrl = ['$scope', '$modalInstance', function ($scope, $modalInstance) {
    $scope.closePrivacyPolicyPopUp = function() {
        $modalInstance.close();
    }
}];

var TermsAndConditionPopupCtrl = ['$scope', '$modalInstance', function ($scope, $modalInstance) {
    $scope.closeTermsAndConditionsPopUp = function () {
        $modalInstance.close();
    }
}];

var AlertPopupCtrl = ['$scope', '$modalInstance', function ($scope, $modalInstance) {
    $scope.closePopUpAlert = function () {
        $modalInstance.close({next: "OPEN_SUBMIT_CODE_MSG"});
    };
}];

var SubmitCodeMsgCtrl = ['$scope', '$modalInstance', '$http', 'ShowDiv', function ($scope, $modalInstance, $http, ShowDiv) {
    $scope.showDiv = ShowDiv

    $scope.closeSubmitCodeMsgPopUp = function () {
        $modalInstance.close();
    };
    $scope.shareOnFacebook = function () {
        $scope.showDiv.fbSpinner = true
        testLogin('http://www.example.com')
        $scope.showDiv.fbSpinner = false
        $modalInstance.close();
    };
}];


submitCodeApp.directive('submitUniqueCode', function () {
    return {
        restrict: 'E',
        templateUrl: 'templates/section2.gsp'
    }
});

submitCodeApp.directive('validUniqueCode', ['$http', function ($http) {
    return {
        require: 'ngModel',
        link: function (scope, elm, attrs, ctrl) {
            ctrl.$validators.validUniqueCode = function (modelValue, viewValue) {
                if (ctrl.$isEmpty(modelValue)) {
                    return true;
                }
                $http.get('/user/isUniqueCodeValid?ucode=' + viewValue).
                    success(function (data, status, headers, config) {
                        success = data.success
                        if (data.success) {
                            return true;
                        }
                        return false;
                    });
            };
        }
    };
}]);

submitCodeApp.directive('validNric', function () {
    return {
        require: 'ngModel',
        link: function (scope, elm, attrs, ctrl) {
            ctrl.$validators.validNric = function (modelValue, viewValue) {
                if (ctrl.$isEmpty(modelValue)) {
                    return true;
                }
                if (validateNRIC(viewValue)) {
                    return true;
                }
                return false;
            };
        }
    };
});