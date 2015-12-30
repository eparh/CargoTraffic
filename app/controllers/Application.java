package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.main;


public class Application extends Controller {



    public Result index(String path) {
        return ok(main.render());
    }


}
