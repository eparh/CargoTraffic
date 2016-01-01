define(["knockout", "text!./companies.html"], function (ko, listTemplate) {
    function listViewModel() {
        var self = this;
        self.companies = ko.observableArray([]);

        $.getJSON("/api/companies", function (data) {
            self.companies(data);
        })

        return self;
    }

    return {viewModel: listViewModel, template: listTemplate};
});

