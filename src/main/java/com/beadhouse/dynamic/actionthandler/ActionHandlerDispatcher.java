package main.java.com.beadhouse.dynamic.actionthandler;

import main.java.com.beadhouse.business.redisclient.RedisClientConnector;
import main.java.com.beadhouse.dynamic.action.Action;
import main.java.com.beadhouse.dynamic.action.ActionType;

public class ActionHandlerDispatcher {
    public void register(ActionType action,
                         ActionHandler handler) {
        RedisClientConnector.getLocalRedis().set(action.name(), handler.getClass().getName());
    }
}
