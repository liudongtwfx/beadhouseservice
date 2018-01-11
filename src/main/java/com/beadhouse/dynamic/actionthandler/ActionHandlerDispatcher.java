package main.java.com.beadhouse.dynamic.actionthandler;

import main.java.com.beadhouse.business.redisclient.RedisClientConnector;
import main.java.com.beadhouse.dynamic.action.Action;
import main.java.com.beadhouse.dynamic.action.ActionType;

import java.util.HashMap;
import java.util.Map;

public class ActionHandlerDispatcher {
    private static final Map<Action, ActionHandler> actionHandler = new HashMap<>();

    public static void register(ActionType action,
                                ActionHandler handler) {
        RedisClientConnector.getLocalRedis().set(action.name(), handler.getClass().getName());
    }
}
