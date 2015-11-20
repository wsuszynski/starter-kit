angular.module('app.main').factory('currentContextPath', function ($window) {
    'use strict';
    var contextPath = '';
    return {
        get: function () {
            var contextPathNotInitializedYet = contextPath ? false : true;
            var path;
            var splitPath;
            var parsedContextPath;

            if (contextPathNotInitializedYet) {
                contextPath = '/';
                path = $window.location.pathname;
                if (path && path !== contextPath) {
                    splitPath = path.split('/');
                    if (splitPath.length > 1) {
                        parsedContextPath = splitPath[1];
                        if (parsedContextPath) {
                            contextPath += parsedContextPath + '/';
                        }
                    }
                }
            }
            return contextPath;
        }
    };
});
