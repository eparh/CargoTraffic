define(['app/utils/utils', "knockout", "text!./account.html"], function (utils, ko, accountTemplate) {
    "use strict";

    function accountViewModel() {
        var self = this;
        self.user = ko.observable({});

        utils.ajax("api/account", "GET", {},
            function (reply) {
                if (reply.status === "SUCCESS") {
                    self.user(reply.data);
                } else {
                    utils.goTo("login");
                }
            });

        return self;
    }

    return {viewModel: accountViewModel, template: accountTemplate};
});