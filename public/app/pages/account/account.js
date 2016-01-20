define(['app/utils/utils', "knockout", "text!./account.html"], function (utils, ko, accountTemplate) {
    "use strict";

    function accountViewModel() {
        var self = this;
        self.account = ko.observable({});
        self.save = function() {
            utils.ajax("api/account", "PUT", JSON.stringify(self.account()),
                function (data) {
                    utils.goTo("account");
                },
                function () {
                    utils.goTo("error");
                });
        };

        utils.ajax("api/account", "GET", {},
            function (data) {
                self.account(data);
            },
            function () {
                utils.goTo("error");
            });

        return self;
    }

    return {viewModel: accountViewModel, template: accountTemplate};
});