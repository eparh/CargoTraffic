$(function () {
    var ViewModel = {
        users: ko.observableArray()
    };
    ko.applyBindings(ViewModel);
    $("#loadUsersButton").bind("click", function () {
        $.getJSON("/list", function (data) {
            ViewModel.users(data);
        })
    })
});
