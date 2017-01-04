(function() {
    'use strict';

    angular
        .module('boncmd3App')
        .controller('PanierMyDeleteController',PanierMyDeleteController);

    PanierMyDeleteController.$inject = ['$uibModalInstance', 'entity', 'Panier'];

    function PanierMyDeleteController($uibModalInstance, entity, Panier) {
        var vm = this;

        vm.panier = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Panier.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
