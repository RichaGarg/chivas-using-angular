var dropApp = angular.module('DropApp', []);

dropApp.controller('YoutubeVideoCtrl', ['$scope', '$modal', '$http', function ($scope, $modal, $http) {
    $scope.open = function (templateUrlPath, modalCtrl) {
        var modalInstance = $modal.open({
            templateUrl: templateUrlPath,
            controller: modalCtrl,
            backdrop: 'static',
            keyboard: false
        });
    };
    $scope.showVideoPopUp = function () {
        $scope.open('templates/youtubeModal.gsp', YoutubeVideoPopupCtrl);
    }
    $scope.showEstablishmentDetailsPopUp = function () {
        $scope.open('templates/establishmentModal.gsp', EstablishmentDetailPopupCtrl);
    }
}]);

var YoutubeVideoPopupCtrl = ['$scope', '$modalInstance', function ($scope, $modalInstance) {
    $scope.closeVideoPopUp = function () {
        $modalInstance.close();
    }
}];

var EstablishmentDetailPopupCtrl = ['$scope', '$modalInstance', function ($scope, $modalInstance) {
    $scope.closeEstablishmentDetailsPopUp = function () {
        $modalInstance.close();
    }
}];

dropApp.directive('dropYt', function () {
    return {
        restrict: 'E',
        templateUrl: 'templates/section5.gsp'
    }
});