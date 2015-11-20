angular.module('app', ['ngRoute',  'app.main', 'ui.bootstrap', 'app.component1', 'app.component2', 'app.books', 'flash'])
    .config(function ($locationProvider) {
        'use strict';
        $locationProvider.html5Mode(false);
    });
