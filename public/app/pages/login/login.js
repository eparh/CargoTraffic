define(['app/utils/utils', "knockout", "text!./login.html"], function (utils, ko, loginTemplate) {
    "use strict";

    function loginViewModel() {
        var self = this;
        self.user = ko.observable();
        self.password = ko.observable();
        self.error = ko.observable();
        self.login = function (root) {
            utils.ajax("api/login", "POST", {user: self.user(), password: self.password()},
                function (reply) {
                    switch (reply.status) {
                        case "SUCCESS":
                            self.error("");
                            root.roles(reply.data);
                            utils.goTo("account");
                            break;
                        case "ERROR":
                            self.error(reply.data);
                            break;
                        case "UNAUTHENTICATED":
                            break;
                        default:
                            break;
                    }
                })
        };
        return self;
    }

    return {viewModel: loginViewModel, template: loginTemplate};
})
;
