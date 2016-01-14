define(['app/utils/utils',"knockout", "text!./companies.html"], function (utils, ko, listTemplate) {
    "use strict";

    function companiesViewModel() {
        var self = this;
        self.companies = ko.observableArray([]);

        utils.ajax("api/companies", "GET",  {},
            function (reply) {
                switch (reply.status) {
                    case "SUCCESS":
                        self.companies(reply.data);
                        break;
                    case "ERROR":
                        break;
                    case "UNAUTHENTICATED":
                        utils.goTo("login");
                        break;
                    default:
                        break;
                }
            });

        return self;
    }

    return {viewModel: companiesViewModel, template: listTemplate};
});

