(function() {
    'use strict';

    angular
        .module('boncmd3App')
        .controller('CommandeMyDeleteController',CommandeMyDeleteController);

    CommandeMyDeleteController.$inject = ['$uibModalInstance', 'entity', 'Commande'];

    function CommandeMyDeleteController($uibModalInstance, entity, Commande) {
        var vm = this;

        vm.commande = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Commande.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
