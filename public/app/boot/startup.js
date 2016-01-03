define(['jquery', 'knockout', 'router', 'bootstrap', 'knockout-projections'], function ($, ko, router) {
    "use strict";

    ko.components.register('navbar', {require: 'app/components/navbar/navbar'});

    ko.components.register('login', {require: 'app/pages/login/login'});
    ko.components.register('account', {require: 'app/pages/account/account'});
    ko.components.register('companies', {require: 'app/pages/companies/companies'});
    ko.components.register('home', {require: 'app/pages/home/home'});


    var roles = ko.observableArray([]);


    if (roles().length === 0)

        $.ajax({
            url: "api/roles",
            method: "POST",
            data: {}
        }).done(function (reply) {

            if (reply.status === "SUCCESS") {
                roles(reply.data);
            }

            if ((window.location.pathname === "/")||(window.location.pathname === "/"))
                if (roles().length === 0) {
                    router.currentRoute("login");
                } else {
                    router.currentRoute("account");
                }

            ko.applyBindings({
                route: router.currentRoute,
                roles: roles
            });
        });


});