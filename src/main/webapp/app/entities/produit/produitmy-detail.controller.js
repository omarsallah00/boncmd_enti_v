(function() {
    'use strict';

    angular
        .module('boncmd3App')
        .controller('ProduitMyDetailController', ProduitMyDetailController);

    ProduitMyDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Produit', 'Categorie'];

    function ProduitMyDetailController($scope, $rootScope, $stateParams, previousState, entity, Produit, Categorie) {
        var vm = this;

        vm.produit = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('boncmd3App:produitUpdate', function(event, result) {
            vm.produit = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
