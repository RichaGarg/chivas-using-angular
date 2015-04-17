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
    $scope.slides = [
        {
            image: 'http://localhost:8080/assets/image1.jpg'
        },
        {
            image: 'http://localhost:8080/assets/image2.jpg'
        },
        {
            image: 'http://localhost:8080/assets/image3.jpg'
        },
        {
            image: 'http://localhost:8080/assets/image4.jpg'
        },
        {
            image: 'http://localhost:8080/assets/image5.jpg'
        },
        {
            image: 'http://localhost:8080/assets/image6.jpg'
        },
        {
            image: 'http://localhost:8080/assets/image7.jpg'
        },
        {
            image: 'http://localhost:8080/assets/image8.jpg'
        },
        {
            image: 'http://localhost:8080/assets/image9.jpg'
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