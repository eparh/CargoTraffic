package controllers;

import be.objectify.deadbolt.java.actions.SubjectNotPresent;
import be.objectify.deadbolt.java.actions.SubjectPresent;
import com.fasterxml.jackson.databind.JsonNode;
import models.Reply;
import models.ReplyStatus;
import models.User;
import org.mindrot.jbcrypt.BCrypt;
import play.Logger;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import security.TokenController;
import service.ServiceException;
import service.UserService;

/**
 * Created by Anton Chernov on 12/30/2015.
 */

public class LoginController extends Controller {
    private static final Logger.ALogger LOGGER = Logger.of(LoginController.class);

    @SubjectNotPresent
    @BodyParser.Of(BodyParser.TolerantJson.class)
    public Result login() throws ControllerException {
        JsonNode json = request().body().asJson();
        String username = json.findPath("user").textValue();
        String password = json.findPath("password").textValue();
        LOGGER.debug("API Try to login username = {}, password = {}", username, password);

        User user;
        try {
            user = UserService.findByName(username);
        } catch (ServiceException e) {
            LOGGER.error("error = {}", e);
            throw new ControllerException(e.getMessage(), e);
        }

        if (user == null) {
            return ok(Json.toJson(
                    new Reply<>(ReplyStatus.ERROR, "User not found"))
            );
        }
        if (BCrypt.checkpw(password, user.password)) {
            TokenController.removeToken(response());
            TokenController.setToken(user, request().host(), response());
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
        TokenController.removeToken(response());
        return ok(Json.toJson(
                new Reply<>(ReplyStatus.SUCCESS, "Logout"))
        );
    }
}
