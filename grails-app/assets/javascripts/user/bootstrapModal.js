var bootstrapModalApp = angular.module('BootstrapModalApp', []);

bootstrapModalApp.controller('BootstrapModalCtrl', ['$scope', '$modal', '$http', function ($scope, $modal, $http) {
    $scope.open = function (templateUrl, modalCtrl) {
        var modalInstance = $modal.open({
            templateUrl: templateUrl,
            controller: modalCtrl,
            backdrop: 'static',
            keyboard: false
        });
    };
}]);

var SubmitCodePopupCtrl = ['$scope', '$modalInstance', function ($scope, $modalInstance) {
    $scope.submitCode = function (value) {
        console.log('submit code---')
    };
    $scope.closeUniqueCodePopUp = function () {
        $modalInstance.close();
    };
}];

