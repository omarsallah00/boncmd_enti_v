(function() {
    'use strict';

    angular
        .module('boncmd3App')
        .controller('PanierMyDialogController', PanierMyDialogController);

    PanierMyDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Panier', 'Commande'];

    function PanierMyDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Panier, Commande) {
        var vm = this;

        vm.panier = entity;
        vm.clear = clear;
        vm.save = save;
        vm.commandes = Commande.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.panier.id !== null) {
                Panier.update(vm.panier, onSaveSuccess, onSaveError);
            } else {
                Panier.save(vm.panier, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('boncmd3App:panierUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
