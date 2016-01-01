var require = {
    baseUrl: "/assets/",
    paths: {
        "bootstrap": "lib/bootstrap.min",
        "crossroads": "lib/crossroads/crossroads",
        "historyjs": "lib/native.history",
        "router": "app/boot/router",
        "jquery": "lib/jquery.min",
        "knockout": "lib/knockout",
        "text": "lib/text",
        "knockout-projections": "lib/knockout-projections.min",
        "signals": "lib/crossroads/signals"
    },
    shim: {
        "bootstrap": {
            deps: ["jquery"]
        }
    }
}