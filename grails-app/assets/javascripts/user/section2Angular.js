var submitCodeApp = angular.module('SubmitCodeApp', []);

submitCodeApp.factory('CodeSubmitted', function () {
    var codeSubmit = {codeSubmitted: false, sharedOnFb: false, success: false, msg: '', nric: '', coupon: '', registerCouponId: 0, requestMessage: 'You’re now in the running to win your own private island. you’ll receive an SMS confirmation shortly.'}
    return codeSubmit
});

submitCodeApp.factory('ShowModal', function () {
    //var showModal = {uniqueCode: false, privacyPolicy:false}
    var showModal = {uniqueCode: false, privacyPolicy:false, tAndC: false, popupAlert: false, submitCodeMsg: false, shareOnFb: false}
    return showModal
});

submitCodeApp.controller('SubmitCodeCtrl', ['$scope', '$modal', '$http', 'CodeSubmitted', 'ShowModal', function ($scope, $modal, $http, codeSubmitted, showModal) {
    $scope.codeSubmit = codeSubmitted
    $scope.requestMessage = codeSubmitted.requestMessage
    $scope.showModal = showModal

    $scope.open = function (templateUrl, modalCtrl) {
        $modal.open({
            templateUrl: templateUrl,
            controller: modalCtrl,
            backdrop: 'static',
            keyboard: false
        });
    };
    $scope.openUniqueCodePopUp = function () {
        $scope.showModal.uniqueCode = true
    };
    $scope.showPrivacyPolicyPopUp = function () {
        $scope.showModal.uniqueCode = false
        $scope.showModal.privacyPolicy = true
    };
    $scope.showTermsAndConditionsPopUp = function () {
        $scope.showModal.uniqueCode = false
        $scope.showModal.tAndC = true
    };
    $scope.showAlertPopUp = function () {
        $scope.showModal.uniqueCode = false
        $scope.showModal.popupAlert = true
    };

    $scope.$watch("codeSubmit.codeSubmitted", function (newValue, oldValue) {
        if(oldValue != newValue) {
            if (newValue == true) {
                console.log(oldValue + ' --value changed code submitted-- ' + newValue)
                $scope.showModal.uniqueCode = false
                $scope.showModal.popupAlert = true
            }
        }
    });
    $scope.$watch("showModal.uniqueCode", function (newValue, oldValue) {
        if(oldValue != newValue) {
            if (newValue == true) {
                console.log(oldValue + ' --value changed unique code-- ' + newValue)
                $scope.open('templates/submitCodeModal.gsp', SubmitCodePopupCtrl);
            }
        }
    });
    $scope.$watch("showModal.privacyPolicy", function (newValue, oldValue) {
        if(oldValue != newValue) {
            if (newValue == true) {
                console.log(oldValue + ' --value changed privacy policy-- ' + newValue)
                $scope.open('templates/privacyPolicyModal.gsp', PrivacyPolicyPopupCtrl);
            }
        }
    });
    $scope.$watch("showModal.tAndC", function (newValue, oldValue) {
        if(oldValue != newValue) {
            if (newValue == true) {
                console.log(oldValue + ' --value changed t and c-- ' + newValue)
                $scope.open('templates/termsAndConditionModal.gsp', TermsAndConditionPopupCtrl);
            }
        }
    });
    $scope.$watch("showModal.popupAlert", function (newValue, oldValue) {
        if(oldValue != newValue) {
            if (newValue == true) {
                console.log(oldValue + ' --value changed popup alert-- ' + newValue)
                $scope.open('templates/popupAlert.gsp', AlertPopupCtrl);
            }
        }
    });
    $scope.$watch("showModal.submitCodeMsg", function (newValue, oldValue) {
        if(oldValue != newValue) {
            if (newValue == true) {
                console.log(oldValue + ' --value changed submit code msg-- ' + newValue)
                $scope.open('templates/submitCodeMsgModal.gsp', ShowSubmitCodeMsgCtrl);
            }
        }
    });
    /*$scope.shareOnFacebook = function() {
        console.log('fb url--- ')
        //shareOnFacebook(serverurl)
    }*/

}]);

var SubmitCodePopupCtrl = ['$scope', '$modalInstance', '$http', 'CodeSubmitted', 'ShowModal', function ($scope, $modalInstance, $http, codeSubmitted, showModal) {
    $scope.codeSubmit = codeSubmitted
    $scope.showModal = showModal
    $scope.uCode = {}
    $scope.accepted = false
    $scope.submittedCode = false
    $scope.submitCode = function () {
        var promise = $http.post('/user/submitCode', $scope.uCode);
        promise.then(function (promise) {
            $scope.codeSubmit.codeSubmitted = promise.data.success
            $scope.codeSubmit.msg = promise.data.msg
            $scope.codeSubmit.requestMessage = promise.data.msg
            console.log(promise.data.msg + ' ---code submit msg-- ' + $scope.codeSubmit.requestMessage)
            if (promise.data.success) {
                $scope.codeSubmit.nric = promise.data.nric
                $scope.codeSubmit.coupon = promise.data.coupon
                $scope.codeSubmit.registerCouponId = promise.data.registerCouponId
                $scope.closeUniqueCodePopUp();
                //$scope.showModal.popupAlert = true
            }
        });
    };
    $scope.$watch("showModal.uniqueCode", function (newValue, oldValue) {
        if(oldValue != newValue) {
            if (newValue == false) {
                console.log(oldValue + ' --value changed unique code close-- ' + newValue)
                $modalInstance.close();
            }
        }
    });
    $scope.closeUniqueCodePopUp = function () {
        $scope.showModal.uniqueCode = false
    };
}];

var AlertPopupCtrl = ['$scope', '$modalInstance', '$http', 'ShowModal', function ($scope, $modalInstance, $http, showModal) {
    $scope.showModal = showModal
    $scope.$watch("showModal.popupAlert", function (newValue, oldValue) {
        if(oldValue != newValue) {
            if (newValue == false) {
                console.log(oldValue + ' --value changed popup alert close-- ' + newValue)
                $modalInstance.close();
            }
        }
    });
    $scope.closePopUpAlert = function () {
        $scope.showModal.popupAlert = false
        $scope.showModal.submitCodeMsg = true
    };
}];

var ShowSubmitCodeMsgCtrl = ['$scope', '$modalInstance', '$http', 'CodeSubmitted', 'ShowModal', function ($scope, $modalInstance, $http, codeSubmitted, showModal) {
    $scope.codeSubmit = codeSubmitted
    $scope.showModal = showModal
    $scope.$watch("showModal.submitCodeMsg", function (newValue, oldValue) {
        if(oldValue != newValue) {
            console.log(oldValue + ' --value changed submitcode msg close-- ' + newValue)
            if (newValue == false) {
                $modalInstance.close();
            }
        }
    });
    $scope.closeSubmitCodeMsgPopUp = function () {
        $scope.showModal.submitCodeMsg = false
    };
    $scope.shareOnFacebook = function(serverUrl) {
        console.log('inside share on fb function')
        shareOnFacebook(serverUrl)
    }
}];




var TermsAndConditionPopupCtrl = ['$scope', '$modalInstance', 'ShowModal', function ($scope, $modalInstance, showModal) {
    $scope.showModal = showModal
    $scope.$watch("showModal.tAndC", function (newValue, oldValue) {
        if(oldValue != newValue) {
            if (newValue == false) {
                console.log(oldValue + ' --value changed tAndC close-- ' + newValue)
                $modalInstance.close();
            }
        }
    });
    $scope.closeTermsAndConditionsPopUp = function () {
        $scope.showModal.tAndC = false
        $scope.showModal.uniqueCode = true
    }
}];

var PrivacyPolicyPopupCtrl = ['$scope', '$modalInstance', 'ShowModal', function ($scope, $modalInstance, showModal) {
    $scope.showModal = showModal
    $scope.$watch("showModal.privacyPolicy", function (newValue, oldValue) {
        if(oldValue != newValue) {
            if(newValue == false) {
                console.log(oldValue + ' --value changed privacy policy closed-- ' + newValue)
                $modalInstance.close();
            }
        }

    });
    $scope.closePrivacyPolicyPopUp = function () {
        $scope.showModal.uniqueCode = true
        $scope.showModal.privacyPolicy = false
    }
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