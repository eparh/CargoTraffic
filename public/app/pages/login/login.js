define(["knockout", "text!./login.html", "app/models/user", "router"], function (ko, loginTemplate, User, router) {

    function loginViewModel() {
        var self = this;
        self.user = ko.observable();
        self.password = ko.observable();
        self.login = function () {
            $.ajax({
                url: "api/login",
                method: "POST",
                data: {user: self.user, password: self.password}
            }).done(function (reply) {
                if (reply.status === "SUCCESS") {
                    window.location.href = "companies";
                    // $("body").trigger("authorized", new User(data.id, data.name));
                }
            })
        }
        return self;
    }

    return {viewModel: loginViewModel, template: loginTemplate};
});
