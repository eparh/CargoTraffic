define(['app/utils/utils', "knockout", "text!./account.html"], function (utils, ko, accountTemplate) {
    "use strict";

    function accountViewModel() {
        var self = this;
        self.user = ko.observable({});

        utils.ajax("api/account", "GET",  {},
            function (reply) {
                switch (reply.status) {
                    case "SUCCESS":
                        self.user(reply.data);
                        break;
                    case "ERROR":
                        break;
                    case "UNAUTHENTICATED":
                        console.log("UNAUTHENTICATED");
                        utils.goTo("login");
                        break;
                    default:
                        break;
                }
            });

        return self;
    }

    return {viewModel: accountViewModel, template: accountTemplate};
});