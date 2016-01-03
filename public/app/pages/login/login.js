define(['jquery',"knockout", "text!./login.html"], function ($, ko, loginTemplate) {
    "use strict";

    function loginViewModel() {
        var self = this;
        self.user = ko.observable();
        self.password = ko.observable();
        self.error = ko.observable();
        self.login = function (root) {
            $.ajax({
                url: "api/login",
                method: "POST",
                data: {user: self.user, password: self.password}
            }).done(function (reply) {
                if (reply.status === "SUCCESS") {
                    self.error("");
                    root.roles(reply.data);
                    var link = document.createElement('a');
                    link.href = "account";
                    document.body.appendChild(link);
                    link.click();
                } else
                    self.error(reply.data);
            })
        };
        return self;
    }

    return {viewModel: loginViewModel, template: loginTemplate};
});