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
                    var link = document.createElement('a');
                    link.href = "login";
                    document.body.appendChild(link);
                    link.click();
                } else {
                    var link = document.createElement('a');
                    link.href = "account";
                    document.body.appendChild(link);
                    link.click();
                }

            ko.applyBindings({
                route: router.currentRoute,
                roles: roles
            });
        });


});