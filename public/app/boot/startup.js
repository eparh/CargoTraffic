define(['app/utils/utils', 'knockout', 'router', 'bootstrap', 'knockout-projections'], function (util, ko, router) {
    "use strict";

    ko.components.register('navbar', {require: 'app/components/navbar/navbar'});

    ko.components.register('login', {require: 'app/pages/login/login'});
    ko.components.register('account', {require: 'app/pages/account/account'});
    ko.components.register('companies', {require: 'app/pages/companies/companies'});
    ko.components.register('home', {require: 'app/pages/home/home'});
    ko.components.register('error', {require: 'app/pages/error/error'});


    var roles = ko.observableArray([]);


    util.ajax("api/roles", "GET", {},
        function (data) {
            roles(data);
            if (window.location.pathname === "/")
                util.goTo("account");

        }, function () {
            if (window.location.pathname === "/")
                util.goTo("login");
        }, function () {
            ko.applyBindings({
                route: router.currentRoute,
                roles: roles
            });
        });


});