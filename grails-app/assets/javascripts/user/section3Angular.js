var fullDetailsApp = angular.module('FullDetailsApp', []);

fullDetailsApp.controller('FullDetailsCtrl', ['$scope', '$modal', '$http', function ($scope, $modal, $http) {
    $scope.open = function () {
        var modalInstance = $modal.open({
            templateUrl: 'templates/fullDetailModal.gsp',
            controller: FullDetailsPopupCtrl,
            backdrop: 'static',
            keyboard: false
        });
    };
    $scope.showContentDetailsPopUp = function () {
        $scope.open();
    }
}]);

var FullDetailsPopupCtrl = ['$scope', '$modalInstance', function ($scope, $modalInstance) {
    $scope.closeContentDetailsPopUp = function () {
        $modalInstance.close();
    }
}];

fullDetailsApp.directive('fullDetails', function () {
    return {
        restrict: 'E',
        templateUrl: 'templates/section3.gsp'
    }
});
