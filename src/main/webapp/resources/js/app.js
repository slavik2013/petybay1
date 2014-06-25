/**
 * Created by home on 21.04.14.
 */
var petybayApp = angular.module('petybayApp', [
    'ngRoute',
    'petybayControllers'
]);

petybayApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
            when('/pets', {
                templateUrl: 'partials/pet-list.html',
                controller: 'PetListCtrl'
            }).
            when('/pet/:petId', {
                templateUrl: 'partials/pet-detail.html',
                controller: 'PetDetailCtrl'
            }).
            when('/main',{
                templateUrl: 'partials/main.html'
            }).
            otherwise({
                redirectTo: '/main'
            });
    }]);