define(["knockout", "text!./account.html"], function (ko, profileTemplate) {
    "use strict";

    function profileViewModel() {
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
                    link.href = "companies";
                    document.body.appendChild(link);
                    link.click();
                } else
                    self.error(reply.data);
            })
        }
        return self;
    }

    return {viewModel: profileViewModel, template: profileTemplate};
});