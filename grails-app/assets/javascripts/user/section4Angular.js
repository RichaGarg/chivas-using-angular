var islandDetailApp = angular.module('IslandDetailApp', []);

islandDetailApp.controller('IslandDetailsCtrl', ['$scope', '$modal', '$http', function ($scope, $modal, $http) {
    $scope.open = function () {
        var modalInstance = $modal.open({
            templateUrl: 'templates/islandDetailModal.gsp',
            controller: IslandDetailPopupCtrl,
            backdrop: 'static',
            keyboard: false
        });
    };
    $scope.showIslandDetailsPopUp = function () {
        $scope.open();
    }
    var serverUrl = 'http://www.example.com'
    $scope.slides = [
        {
            image: serverUrl + '/assets/image1.jpg'
        },
        {
            image: serverUrl + '/assets/image2.jpg'
        },
        {
            image: serverUrl + '/assets/image3.jpg'
        },
        {
            image: serverUrl + '/assets/image4.jpg'
        },
        {
            image: serverUrl + '/assets/image5.jpg'
        },
        {
            image: serverUrl + '/assets/image6.jpg'
        },
        {
            image: serverUrl + '/assets/image7.jpg'
        },
        {
            image: serverUrl + '/assets/image8.jpg'
        },
        {
            image: serverUrl + '/assets/image9.jpg'
        }
    ];
}]);

var IslandDetailPopupCtrl = ['$scope', '$modalInstance', function ($scope, $modalInstance) {
    $scope.closeIslandDetailsPopUp = function () {
        $modalInstance.close();
    }
}];

islandDetailApp.directive('islandDetail', function () {
    return {
        restrict: 'E',
        templateUrl: 'templates/section4.gsp'
    }
});