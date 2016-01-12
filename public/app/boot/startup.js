define(['app/utils/utils', 'knockout', 'router', 'bootstrap', 'knockout-projections'], function (util, ko, router) {
    "use strict";

    ko.components.register('navbar', {require: 'app/components/navbar/navbar'});

    ko.components.register('login', {require: 'app/pages/login/login'});
    ko.components.register('account', {require: 'app/pages/account/account'});
    ko.components.register('companies', {require: 'app/pages/companies/companies'});
    ko.components.register('home', {require: 'app/pages/home/home'});


    var roles = ko.observableArray([]);


    if (roles().length === 0)
        util.ajax("api/roles", "GET", {}, function (reply) {

            if (reply.status === "SUCCESS") {
                roles(reply.data);
            }

            if (roles().length === 0) {
                util.goTo("login");
            } else {
                util.goTo("account");
            }

            ko.applyBindings({
                route: router.currentRoute,
                roles: roles
            });
        });


});