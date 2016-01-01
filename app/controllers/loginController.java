package controllers;

import service.UserService;
import models.User;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;

import java.util.Map;

/**
 * Created by Anton Chernov on 12/30/2015.
 */
public class LoginController extends BaseController {
    private static final Logger.ALogger LOGGER = Logger.of(LoginController.class);


    public Result login() {
        final Map<String, String[]> values = request().body().asFormUrlEncoded();
        String username = values.get("user")[0];
        String password = values.get("password")[0];
        LOGGER.debug("Try to login username = {}, password = {}", username, password);
        User user = UserService.findByName(username);
        if (user == null) {
            return badRequest(Json.toJson(
                            new Reply<>(Status.ERROR, "User not found"))
            );
        }
        if (password.equals(password)) {
            session().clear();
            session("username", username);
            session("password", password);
            return ok(Json.toJson(
                            new Reply<>(Status.SUCCESS, "Right password"))
            );
        } else {
            return badRequest(Json.toJson(
                            new Reply<>(Status.ERROR, "Wrong password"))
            );
        }
    }
}
