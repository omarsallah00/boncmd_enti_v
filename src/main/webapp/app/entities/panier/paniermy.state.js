(function() {
    'use strict';

    angular
        .module('boncmd3App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('paniermy', {
            parent: 'entity',
            url: '/paniermy?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'boncmd3App.panier.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/panier/paniersmy.html',
                    controller: 'PanierMyController',
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
                    $translatePartialLoader.addPart('panier');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('paniermy-detail', {
            parent: 'entity',
            url: '/paniermy/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'boncmd3App.panier.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/panier/paniermy-detail.html',
                    controller: 'PanierMyDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('panier');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Panier', function($stateParams, Panier) {
                    return Panier.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'paniermy',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('paniermy-detail.edit', {
            parent: 'paniermy-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/panier/paniermy-dialog.html',
                    controller: 'PanierMyDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Panier', function(Panier) {
                            return Panier.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('paniermy.new', {
            parent: 'paniermy',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/panier/paniermy-dialog.html',
                    controller: 'PanierMyDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                quantite: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('paniermy', null, { reload: 'paniermy' });
                }, function() {
                    $state.go('paniermy');
                });
            }]
        })
        .state('paniermy.edit', {
            parent: 'paniermy',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/panier/paniermy-dialog.html',
                    controller: 'PanierMyDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Panier', function(Panier) {
                            return Panier.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('paniermy', null, { reload: 'paniermy' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('paniermy.delete', {
            parent: 'paniermy',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/panier/paniermy-delete-dialog.html',
                    controller: 'PanierMyDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Panier', function(Panier) {
                            return Panier.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('paniermy', null, { reload: 'paniermy' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
