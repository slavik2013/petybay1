/**
 * Created by home on 21.04.14.
 */
var petybayControllers = angular.module('petybayControllers', []);

petybayControllers.controller('PetListCtrl', ['$scope', '$http',
    function ($scope, $http) {
        $http.get('api/pet').success(function(data) {
            $scope.pets = data;
        });
    }]);

petybayControllers.controller('PetDetailCtrl', ['$scope', '$routeParams', '$http',
    function($scope, $routeParams, $http) {
        $http.get('api/pet/' + $routeParams.petId).success(function(data) {
            $scope.pet = data;
        });
    }]);