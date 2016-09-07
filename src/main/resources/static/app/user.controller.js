(function () {
    'use strict';

    angular
        .module('app')
        .controller('UserController', UserController);

    UserController.$inject = ['$http'];

    function UserController($http) {
        var vm = this;

        vm.bookings = [];
        vm.getAll = getAll;
        
        vm.deleteBooking = deleteBooking;

        init();

        function init(){
            getAll();
        }

        function getAll(){
            var url = "/bookings/all";
            var bookingsPromise = $http.get(url);
            bookingsPromise.then(function(response){
                vm.bookings = response.data;
            });
        }

        

        function deleteBooking(id){
            var url = "/showrole"+ id +"/delete/" ;
            $http.post(url).then(function(response){
                vm.bookings = response.data;
            });
        }
    }
})();