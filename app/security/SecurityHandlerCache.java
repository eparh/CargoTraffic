package security;

import be.objectify.deadbolt.java.DeadboltHandler;
import be.objectify.deadbolt.java.cache.HandlerCache;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anton Chernov on 12/31/2015.
 */
public class SecurityHandlerCache implements HandlerCache {

    private final Map<String, DeadboltHandler> handlers = new HashMap<>();


    public SecurityHandlerCache() {
        handlers.put(HandlerKeys.DEFAULT.key, new SecurityHandler());
    }

    @Override
    public DeadboltHandler apply(final String key) {
        return handlers.get(key);
    }

    @Override
    public DeadboltHandler get() {
        return handlers.get(HandlerKeys.DEFAULT.key);
    }
}
