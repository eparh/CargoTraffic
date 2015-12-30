package controllers;

import Service.UserService;
import models.User;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Result;

import java.util.Map;

/**
 * Created by Anton Chernov on 12/30/2015.
 */
public class LoginController extends BaseController {


    @Transactional
    public Result login() {
        final Map<String, String[]> values = request().body().asFormUrlEncoded();
        String name = values.get("user")[0];
        String password = values.get("password")[0];
        User user = UserService.findByName(name);
        if(user == null) {
            return badRequest(Json.toJson(
                    new Reply<>(Status.ERROR, "User not found"))
            );
        }
        if(password.equals(password)) {
            session().clear();
            session("name", name);

            return ok(Json.toJson(
                    new Reply<>(Status.SUCCESS, user))
            );
        } else {
            return badRequest(Json.toJson(
                    new Reply<>(Status.ERROR, "Wrong password"))
            );
        }
    }
}
