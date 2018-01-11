package main.java.com.beadhouse.dynamic.actionthandler;

import com.google.gson.Gson;
import main.java.com.beadhouse.business.redisclient.RedisClientConnector;
import main.java.com.beadhouse.dynamic.action.Action;
import main.java.com.beadhouse.dynamic.action.ActionType;
import main.java.com.beadhouse.dynamic.action.returnaction.ReturnAction;
import main.java.com.beadhouse.dynamic.action.validateaction.ValidateAction;
import main.java.com.beadhouse.dynamic.actionthandler.queryactionhandler.DatabaseQueryActionHandler;
import main.java.com.beadhouse.dynamic.actionthandler.validateactionhandler.ValidateActionHandlerImpl;
import main.java.com.beadhouse.dynamic.datawrapper.Data;
import main.java.com.beadhouse.dynamic.datawrapper.ExceptionOrFailedData;

import java.util.HashMap;
import java.util.Map;

public class EventHandler {
    private final Map<String, String> inputParams;

    public EventHandler(Map<String, Object> requestParams) {
        this.inputParams = new HashMap<>();
        requestParams.forEach((k, v) -> inputParams.put(k, (String) v));
    }

    public ReturnAction eventHandle(String uuid) {
        Action action = getEventByUUID(uuid);
        Data in = null;
        while (action.getActionType() != ActionType.RETURN) {
            Data out = getHandler(action).handle(in);
            in = out;
        }
        return (ReturnAction) action;
    }

    private ActionHandler getHandler(Action action) {
        switch (action.getActionType()) {
            case VALIDATE:
                return new ValidateActionHandlerImpl((ValidateAction) action);
            case QUERY:
                return new DatabaseQueryActionHandler(action, inputParams);
        }
        return null;
    }

    private static Action getEventByUUID(String uuid) {
        String actionInfo = RedisClientConnector.getLocalRedis().get("dynamic:action:" + uuid);
        if (actionInfo != null) {
            Gson gson = new Gson();
            return gson.fromJson(actionInfo, Action.class);
        }
        return new ReturnAction(new ExceptionOrFailedData("调用内部服务错误"));
    }
}
