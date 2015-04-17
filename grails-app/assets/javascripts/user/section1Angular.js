var ageGatewayApp = angular.module('AgeGatewayApp', []);

ageGatewayApp.factory('AgeService', function() {
    return {'moreThan18' : false};
});

ageGatewayApp.controller('AgeGatewayCtrl', ['$scope', '$modal', '$http', 'AgeService', function ($scope, $modal, $http, AgeService) {
    $scope.section1 = AgeService;
    $scope.open = function () {
        var modalInstance = $modal.open({
            templateUrl: 'templates/ageGatewayModal.gsp',
            controller: AgePopupCtrl,
            backdrop: 'static',
            keyboard: false
        });
    };
    $scope.open();
    $scope.scrollDown = function() {
        console.log('inside scroll down');

    }
}]);

var AgePopupCtrl = ['$scope', '$modalInstance', 'AgeService', function ($scope, $modalInstance, AgeService) {
    $scope.section1 = AgeService;
    $scope.changeAgeState = function(value) {
        $scope.section1.moreThan18 = value;
    };
    $scope.stopFromFurtherNavigation = function() {
        $scope.changeAgeState(false);
        $modalInstance.dismiss('cancel');
    };

    $scope.purchaseBottle = function () {
        $scope.changeAgeState(true);
        $modalInstance.close();
    };
}];

ageGatewayApp.directive('ageGateway',function() {
    return {
        restrict: 'E',
        templateUrl: 'templates/section1.gsp'
    }
});