define(['app/utils/utils',"knockout", "text!./companies.html"], function (utils, ko, listTemplate) {
    "use strict";

    function companiesViewModel() {
        var self = this;
        self.companies = ko.observableArray([]);

        utils.ajax("api/companies", "GET", {},
            function (reply) {
                if (reply.status === "SUCCESS") {
                    self.companies(reply.data);
                } else {
                    utils.goTo("login");
                }
            });

        return self;
    }

    return {viewModel: companiesViewModel, template: listTemplate};
});

