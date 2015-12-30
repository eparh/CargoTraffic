define(["knockout", "text!./companyList.html"], function (ko, listTemplate) {
    function listViewModel() {
        var self = this;
        self.companyList = ko.observableArray([]);

        $.getJSON("/api/companyList", function (data) {
            self.companyList(data);
        })

        return self;
    }

    return {viewModel: listViewModel, template: listTemplate};
});

