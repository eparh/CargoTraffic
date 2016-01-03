package controllers;

import be.objectify.deadbolt.java.actions.SubjectNotPresent;
import be.objectify.deadbolt.java.actions.SubjectPresent;
import models.Reply;
import models.ReplyStatus;
import models.User;
import org.apache.commons.codec.binary.StringUtils;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import service.UserService;

import java.util.Map;

/**
 * Created by Anton Chernov on 12/30/2015.
 */

public class LoginController extends Controller {
    private static final Logger.ALogger LOGGER = Logger.of(LoginController.class);

    @SubjectNotPresent
    public Result login() {
        final Map<String, String[]> values = request().body().asFormUrlEncoded();
        String username = values.get("user")[0];
        String password = values.get("password")[0];
        LOGGER.debug("API Try to login username = {}, password = {}", username, password);
        User user = UserService.findByName(username);
        if (user == null) {
            return ok(Json.toJson(
                            new Reply<>(ReplyStatus.ERROR, "User not found"))
            );
        }
        if (StringUtils.equals(user.password, password)) {
            session().clear();
            session("username", username);
            session("password", password);
            return ok(Json.toJson(
                            new Reply<>(ReplyStatus.SUCCESS, user.getRoles()))
            );
        } else {
            return ok(Json.toJson(
                            new Reply<>(ReplyStatus.ERROR, "Wrong password"))
            );
        }
    }

    @SubjectPresent
    public Result roles() {
        User user = (User) Http.Context.current().args.get("user");
        LOGGER.debug("API Get roles for user = {}", user.toString());
        return ok(Json.toJson(new Reply<>(ReplyStatus.SUCCESS, user.getRoles())));
    }

    @SubjectPresent
    public Result logout() {
        LOGGER.debug("API Logout user = {}", Http.Context.current().args.get("user").toString());
        session().clear();
        return ok(Json.toJson(
                new Reply<>(ReplyStatus.SUCCESS, "Logout"))
        );
    }
}
