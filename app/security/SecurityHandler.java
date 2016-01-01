package security;

import play.db.jpa.Transactional;
import service.UserService;
import be.objectify.deadbolt.core.models.Subject;
import be.objectify.deadbolt.java.DeadboltHandler;
import be.objectify.deadbolt.java.DynamicResourceHandler;
import models.User;
import org.apache.commons.lang3.StringUtils;
import play.Logger;
import play.libs.F;
import play.mvc.Http;
import play.mvc.Result;

import java.util.Objects;
import java.util.Optional;

import static play.mvc.Results.forbidden;

/**
 * Created by Anton Chernov on 12/31/2015.
 */
public class SecurityHandler implements DeadboltHandler {
    private static final Logger.ALogger LOGGER = Logger.of(SecurityHandler.class);

    @Override
    public F.Promise<Optional<Result>> beforeAuthCheck(Http.Context context) {
        return F.Promise.promise(Optional::empty);
    }

    @Transactional
    @Override
    public F.Promise<Optional<Subject>> getSubject(Http.Context context) {
        String username = context.session().get("username");
        if (StringUtils.isEmpty(username)) return null;

        User user = UserService.findByName(username);
        if (Objects.isNull(user)) return null;
        context.args.put("user", user);
        LOGGER.debug("User = {} Roles = {}", username, StringUtils.join(user.getRoles()));
        return F.Promise.promise(() -> Optional.ofNullable(user));
    }


    @Override
    public F.Promise<Result> onAuthFailure(Http.Context context, String s) {
        LOGGER.debug("Authorization failed");
        return F.Promise.promise(() -> forbidden());
    }

    @Override
    public F.Promise<Optional<DynamicResourceHandler>> getDynamicResourceHandler(Http.Context context) {
        return null;
    }
}
