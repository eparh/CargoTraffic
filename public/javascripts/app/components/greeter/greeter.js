define(["knockout", "text!./greeter.html"], function (ko, greeterTemplate) {

    function greeterViewModel(params) {
        var self = this;
        self.greeting = ko.observable(params.name);
        self.date = ko.observable(new Date());
        return self;
    }
    return { viewModel: greeterViewModel, template: greeterTemplate };
});
