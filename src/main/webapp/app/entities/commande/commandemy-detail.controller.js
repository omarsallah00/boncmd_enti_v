(function() {
    'use strict';

    angular
        .module('boncmd3App')
        .controller('CommandeMyDetailController', CommandeMyDetailController);

    CommandeMyDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Commande', 'Produit'];

    function CommandeMyDetailController($scope, $rootScope, $stateParams, previousState, entity, Commande, Produit) {
        var vm = this;

        vm.commande = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('boncmd3App:commandeUpdate', function(event, result) {
            vm.commande = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
