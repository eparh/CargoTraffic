define(['app/utils/utils', "knockout", "text!./login.html"], function (utils, ko, loginTemplate) {
    "use strict";

    function loginViewModel() {
        var self = this;
        self.user = ko.observable();
        self.password = ko.observable();
        self.error = ko.observable();
        self.login = function (root) {
            utils.ajax("api/login", "POST", JSON.stringify({user: self.user(), password: self.password()}),

                function (data) {
                    self.error("");
                    root.roles(data);
                    utils.goTo("account");
                },
                function (data) {
                    self.error("Invalid login or password");
                })

        };
        return self;
    }

    return {viewModel: loginViewModel, template: loginTemplate};
});
