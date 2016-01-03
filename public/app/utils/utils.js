define(['jquery'], function ($) {
    "use strict";

    function ajax(url, method, data, callback) {
        $.ajax({
            url: url,
            method: method,
            data: data
        }).done(callback);
    }

    function goTo(page) {
        var link = document.createElement('a');
        link.href = page;
        document.body.appendChild(link);
        link.click();
    }

    return {
        ajax: ajax,
        goTo: goTo
    };
});
