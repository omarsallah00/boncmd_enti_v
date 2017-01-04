(function() {
    'use strict';

    angular
        .module('boncmd3App')
        .controller('CategorieMyDeleteController',CategorieMyDeleteController);

    CategorieMyDeleteController.$inject = ['$uibModalInstance', 'entity', 'Categorie'];

    function CategorieMyDeleteController($uibModalInstance, entity, Categorie) {
        var vm = this;

        vm.categorie = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Categorie.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
