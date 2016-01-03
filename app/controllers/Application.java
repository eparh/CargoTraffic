package controllers;

import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.main;


public class Application extends Controller {
    private static final Logger.ALogger LOGGER = Logger.of(Application.class);

    public Result index(String path) {
        LOGGER.debug("Return index page path = {}", path);
        return ok(main.render());
    }

}
