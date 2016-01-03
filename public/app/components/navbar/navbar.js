define(['app/utils/utils', "knockout", "text!./navbar.html"], function (utils, ko, navbarTemplate) {
    "use strict";

    function navbarViewModel() {
        var self = this;

        self.logout = function (root) {
            utils.ajax("api/logout", "POST", {},
                function (reply) {
                    if (reply.status === "SUCCESS") {
                        root.roles([]);
                        utils.goTo("login");
                    }
                })
        };

        return self;
    }

    return {viewModel: navbarViewModel, template: navbarTemplate};
});
