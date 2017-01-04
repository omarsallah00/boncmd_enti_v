(function() {
    'use strict';

    angular
        .module('boncmd3App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('commandemy', {
            parent: 'entity',
            url: '/commandemy?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'boncmd3App.commande.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/commande/commandesmy.html',
                    controller: 'CommandeMyController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('commande');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('commandemy-detail', {
            parent: 'entity',
            url: '/commandemy/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'boncmd3App.commande.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/commande/commandemy-detail.html',
                    controller: 'CommandeMyDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('commande');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Commande', function($stateParams, Commande) {
                    return Commande.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'commandemy',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('commandemy-detail.edit', {
            parent: 'commandemy-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/commande/commandemy-dialog.html',
                    controller: 'CommandeMyDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Commande', function(Commande) {
                            return Commande.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('commandemy.new', {
            parent: 'commandemy',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/commande/commandemy-dialog.html',
                    controller: 'CommandeMyDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                referenceCommande: null,
                                dateCommande: null,
                                emailContactAdministratif: null,
                                telContactAdministratif: null,
                                emailContactTechnique: null,
                                telContactTechnique: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('commandemy', null, { reload: 'commandemy' });
                }, function() {
                    $state.go('commandemy');
                });
            }]
        })
        .state('commandemy.edit', {
            parent: 'commandemy',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/commande/commandemy-dialog.html',
                    controller: 'CommandeMyDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Commande', function(Commande) {
                            return Commande.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('commandemy', null, { reload: 'commandemy' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('commandemy.delete', {
            parent: 'commandemy',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/commande/commandemy-delete-dialog.html',
                    controller: 'CommandeMyDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Commande', function(Commande) {
                            return Commande.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('commandemy', null, { reload: 'commandemy' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
