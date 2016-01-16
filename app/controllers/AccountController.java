package controllers;

import be.objectify.deadbolt.java.actions.SubjectPresent;
import models.User;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

/**
 * Created by Anton Chernov on 1/3/2016.
 */
@SubjectPresent
public class AccountController extends Controller {
    private static final Logger.ALogger LOGGER = Logger.of(AccountController.class);

    public Result account() {
        User user = (User) Http.Context.current().args.get("user");
        LOGGER.debug("API Return account data for user = {}", user.toString());
        return ok(Json.toJson(user));
    }
}
