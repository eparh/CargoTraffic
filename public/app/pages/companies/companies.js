define(["knockout", "text!./companies.html"], function (ko, listTemplate) {
    "use strict";

    function companiesViewModel() {
        var self = this;
        self.companies = ko.observableArray([]);

        $.ajax({
            url: "api/companies",
            method: "GET",
            data: {}
        }).done(function (reply) {
            if (reply.status === "SUCCESS") {
                self.companies(reply.data);
            } else {
                var link = document.createElement('a');
                link.href = "login";
                document.body.appendChild(link);
                link.click();
            }
        });

        return self;
    }

    return {viewModel: companiesViewModel, template: listTemplate};
});

