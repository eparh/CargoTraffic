define(["knockout", "text!./navbar.html"], function (ko, navbarTemplate) {
    "use strict";

    function navbarViewModel() {
        var self = this;

        self.index = function () {
            window.location.hash = "";
        };
        self.logout = function (root) {
            $.ajax({
                url: "api/logout",
                method: "POST",
                data: {}
            }).done(function (reply) {
                if (reply.status === "SUCCESS") {
                    root.roles([]);
                    var link = document.createElement('a');
                    link.href = "login";
                    document.body.appendChild(link);
                    link.click();
                }
            })
        };

        return self;
    }

    return {viewModel: navbarViewModel, template: navbarTemplate};
});
