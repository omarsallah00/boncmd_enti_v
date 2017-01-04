(function() {
    'use strict';

    angular
        .module('boncmd3App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('categoriemy', {
            parent: 'entity',
            url: '/categoriemy?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'boncmd3App.categorie.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/categorie/categoriesmy.html',
                    controller: 'CategorieMyController',
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
                    $translatePartialLoader.addPart('categorie');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('categoriemy-detail', {
            parent: 'entity',
            url: '/categoriemy/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'boncmd3App.categorie.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/categorie/categoriemy-detail.html',
                    controller: 'CategorieMyDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('categorie');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Categorie', function($stateParams, Categorie) {
                    return Categorie.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'categoriemy',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('categoriemy-detail.edit', {
            parent: 'categoriemy-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/categorie/categoriemy-dialog.html',
                    controller: 'CategorieMyDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Categorie', function(Categorie) {
                            return Categorie.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('categoriemy.new', {
            parent: 'categoriemy',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/categorie/categoriemy-dialog.html',
                    controller: 'CategorieMyDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                referenceCategorie: null,
                                description: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('categoriemy', null, { reload: 'categoriemy' });
                }, function() {
                    $state.go('categoriemy');
                });
            }]
        })
        .state('categoriemy.edit', {
            parent: 'categoriemy',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/categorie/categoriemy-dialog.html',
                    controller: 'CategorieMyDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Categorie', function(Categorie) {
                            return Categorie.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('categoriemy', null, { reload: 'categoriemy' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('categoriemy.delete', {
            parent: 'categoriemy',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/categorie/categoriemy-delete-dialog.html',
                    controller: 'CategorieMyDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Categorie', function(Categorie) {
                            return Categorie.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('categoriemy', null, { reload: 'categoriemy' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
