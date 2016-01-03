define(['app/utils/utils', "knockout", "text!./login.html"], function (utils, ko, loginTemplate) {
    "use strict";

    function loginViewModel() {
        var self = this;
        self.user = ko.observable();
        self.password = ko.observable();
        self.error = ko.observable();
        self.login = function (root) {
            utils.ajax("api/login", "POST", {user: self.user, password: self.password},
                function (reply) {
                    if (reply.status === "SUCCESS") {
                        self.error("");
                        root.roles(reply.data);
                        utils.goTo("account");
                    } else
                        self.error(reply.data);
                })
        };
        return self;
    }

    return {viewModel: loginViewModel, template: loginTemplate};
})
;
