define(['jquery', 'knockout', 'router', 'bootstrap', 'knockout-projections'], function ($, ko, router) {
    ko.components.register('greeter', { require: 'app/components/greeter/greeter' });

    ko.components.register('login', { require: 'app/pages/login/login' });
    ko.components.register('companyList', { require: 'app/pages/companyList/companyList' });
    ko.components.register('home', { require: 'app/pages/home/home' });
    ko.components.register('settings', {
        template: { require: 'text!app/pages/settings/settings.html' }
    });
    ko.applyBindings({ route: router.currentRoute });
});