package security;

import be.objectify.deadbolt.core.models.Subject;
import be.objectify.deadbolt.java.DeadboltHandler;
import be.objectify.deadbolt.java.DynamicResourceHandler;
import models.Reply;
import models.ReplyStatus;
import models.User;
import org.apache.commons.lang3.StringUtils;
import play.Logger;
import play.libs.F;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import service.UserService;

import java.util.Objects;
import java.util.Optional;

import static play.mvc.Results.ok;

/**
 * Created by Anton Chernov on 12/31/2015.
 */
public class SecurityHandler implements DeadboltHandler {
    private static final Logger.ALogger LOGGER = Logger.of(SecurityHandler.class);

    @Override
    public F.Promise<Optional<Result>> beforeAuthCheck(Http.Context context) {
        return F.Promise.promise(Optional::empty);
    }


    @Override
    public F.Promise<Optional<Subject>> getSubject(Http.Context context) {
        String username = context.session().get("username");
        String password = context.session().get("password");
        if (StringUtils.isNoneEmpty(username) && StringUtils.isNoneEmpty(password)) {
            User user = UserService.findByName(username);
            if (!StringUtils.equals(user.password, password)) return F.Promise.promise(Optional::empty);
            if (!Objects.isNull(user)) context.args.put("user", user);
            LOGGER.debug("User = {} Roles = {}", username, StringUtils.join(user.getRoles()));
            return F.Promise.promise(() -> Optional.ofNullable(user));
        } else
            return F.Promise.promise(Optional::empty);
    }


    @Override
    public F.Promise<Result> onAuthFailure(Http.Context context, String s) {
        LOGGER.debug("Authorization failed");
        return F.Promise.promise(() -> ok(Json.toJson(
                new Reply<>(ReplyStatus.ERROR, "Unauthorized"))));
    }



    @Override
    public F.Promise<Optional<DynamicResourceHandler>> getDynamicResourceHandler(Http.Context context) {
        return null;
    }
}
