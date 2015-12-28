package controllers;

import Service.UserController;
import models.User;
import org.mindrot.jbcrypt.BCrypt;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Result;
import views.html.main;

import java.util.List;
import java.util.Map;


public class Application extends BaseController {

    @Transactional
    public Result getUserList() {
        List<User> userList = UserController.getUserList();
        return ok(Json.toJson(userList));
    }


    @Transactional
    public Result login() {
        final Map<String, String[]> values = request().body().asFormUrlEncoded();
        String name = values.get("user")[0];
        String password = values.get("password")[0];
        User user = UserController.findByName(name);
        if(user == null) {
            return badRequest(Json.toJson(
                    new Reply<>(Status.ERROR, "User not found"))
            );
        }
        if(BCrypt.checkpw(password, user.password)) {
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

    public Result index() {
        return ok(main.render());
    }


}
