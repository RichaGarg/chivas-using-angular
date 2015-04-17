var submitCodeApp = angular.module('SubmitCodeApp', []);

submitCodeApp.controller('SubmitCodeCtrl', ['$scope', '$modal', '$http', function ($scope, $modal, $http) {
    $scope.open = function (templateUrl, modalCtrl) {
        var modalInstance = $modal.open({
            templateUrl: templateUrl,
            controller: modalCtrl,
            backdrop: 'static',
            keyboard: false
        });
    };
    $scope.openUniqueCodePopUp = function () {
        $scope.open('templates/submitCodeModal.gsp', SubmitCodePopupCtrl);
    };
    $scope.showPrivacyPolicyPopUp = function () {
        $scope.closeUniqueCodePopUp();
        $scope.open('templates/privacyPolicyModal.gsp', PrivacyPolicyPopupCtrl);
    };
    $scope.showTermsAndConditionsPopUp = function () {
        $scope.closeUniqueCodePopUp();
        $scope.open('templates/termsAndConditionModal.gsp', TermsAndConditionPopupCtrl);
    };
}]);

var SubmitCodePopupCtrl = ['$scope', '$modalInstance', '$http', function ($scope, $modalInstance, $http) {
    $scope.uCode = {}
    $scope.accepted = false
    $scope.submitCode = function () {
        console.log($scope.uCode)
        $http.post('/user/submitCode', $scope.uCode).
            success(function (data, status, headers, config) {
                console.log('1---   ' + data.success)
                success = data.success
                if (data.success) {
                    console.log('success---')
                    return true;
                }
                return false;
            });
    };
    $scope.closeUniqueCodePopUp = function () {
        $modalInstance.close();
    };
}];

var TermsAndConditionPopupCtrl = ['$scope', '$modalInstance', function ($scope, $modalInstance) {
    $scope.closeTermsAndConditionsPopUp = function () {
        $modalInstance.close();
        //$scope.openUniqueCodePopUp();
    }
}];

var PrivacyPolicyPopupCtrl = ['$scope', '$modalInstance', function ($scope, $modalInstance) {
    $scope.closePrivacyPolicyPopUp = function () {
        $modalInstance.close();
        //$scope.openUniqueCodePopUp();
    }
}];

submitCodeApp.directive('submitUniqueCode', function () {
    return {
        restrict: 'E',
        templateUrl: 'templates/section2.gsp'
    }
});

var PHONE_REGEXP = /^[689]\d{7}$/;
submitCodeApp.directive('validPhoneNumber', function () {
    return {
        require: 'ngModel',
        link: function (scope, elm, attrs, ctrl) {
            ctrl.$validators.validPhoneNumber = function (modelValue, viewValue) {
/*                if (ctrl.$isEmpty(modelValue)) {
                    return true;
                }
                if (PHONE_REGEXP.test(viewValue)) {
                    return true;
                }
                return false;*/
                return true;
            };
        }
    };
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
                        console.log('1---   ' + data.success)
                        success = data.success
                        if (data.success) {
                            console.log('success---')
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