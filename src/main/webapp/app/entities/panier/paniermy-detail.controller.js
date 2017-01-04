(function() {
    'use strict';

    angular
        .module('boncmd3App')
        .controller('PanierMyDetailController', PanierMyDetailController);

    PanierMyDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Panier', 'Commande'];

    function PanierMyDetailController($scope, $rootScope, $stateParams, previousState, entity, Panier, Commande) {
        var vm = this;

        vm.panier = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('boncmd3App:panierUpdate', function(event, result) {
            vm.panier = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
