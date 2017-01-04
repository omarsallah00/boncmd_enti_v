(function() {
    'use strict';

    angular
        .module('boncmd3App')
        .controller('ProduitMyDeleteController',ProduitMyDeleteController);

    ProduitMyDeleteController.$inject = ['$uibModalInstance', 'entity', 'Produit'];

    function ProduitMyDeleteController($uibModalInstance, entity, Produit) {
        var vm = this;

        vm.produit = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Produit.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
