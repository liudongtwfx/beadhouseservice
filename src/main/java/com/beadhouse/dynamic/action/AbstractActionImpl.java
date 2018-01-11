package main.java.com.beadhouse.dynamic.action;

import com.google.gson.Gson;
import main.java.com.beadhouse.business.neo4jbusiness.AddActionToNeo4j;
import main.java.com.beadhouse.business.redisclient.RedisClientConnector;

import java.util.*;

public abstract class AbstractActionImpl implements Action {
    protected boolean start = false;
    protected boolean end = false;
    protected String uuid;
    protected ActionType actionType;
    protected ConnectionType connectionType = ConnectionType.NULL;
    protected Action preAction;
    protected Map<String, Action> switchActions;
    protected Action nextAction;

    public boolean isStart() {
        return start;
    }

    public boolean isEnd() {
        return end;
    }

    @Override
    public final void setStart() {
        this.start = true;
    }

    @Override
    public final void setEnd() {
        this.end = true;
    }

    public void setUUID(String uuid) {
        this.uuid = uuid;
    }

    public String getUUID() {
        if (uuid == null || uuid.length() == 0) {
            uuid = String.valueOf(UUID.randomUUID());
        }
        return uuid;
    }

    protected final void setActionType(ActionType type) {
        actionType = type;
    }

    @Override
    public void setNextType(ConnectionType type) {
        this.connectionType = type;
    }

    @Override
    public Action getPreAction() {
        if (start) {
            return null;
        }
        return preAction;
    }

    @Override
    public void setPreAction(Action preAction) {
        this.preAction = preAction;
    }

    public Action getNextActions() {
        return nextAction;
    }

    @Override
    public Map<String, Action> getSwitchType() {
        return null;
    }

    private final Map<String, Object> getCommonValue() {
        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("actionId", getUUID());
        valueMap.put("name", actionType.getChineseName());
        valueMap.put("actionType", actionType.toString());
        valueMap.put("isStart", String.valueOf(start));
        valueMap.put("isEnd", String.valueOf(end));
        valueMap.put("connectionType", connectionType.toString());
        if (connectionType == ConnectionType.NEXT) {
            valueMap.put("next", nextAction.getUUID());
        } else if (connectionType == ConnectionType.SWITCH && switchActions.size() != 0) {
            switchActions.forEach((k, v) -> valueMap.put("switch_" + k, v.getUUID()));
        }
        getSavingValue(valueMap);
        return valueMap;
    }

    protected abstract void getSavingValue(Map<String, Object> value);

    protected final String getKey() {
        if (uuid == null || uuid.length() == 0) {
            uuid = String.valueOf(UUID.randomUUID());
        }
        return uuid;
    }

    @Override
    public void save() {
        Map<String, String> valuesToRedis = new HashMap<>();
        Map<String, Object> values = getCommonValue();
        values.forEach((k, v) -> valuesToRedis.put(k, String.valueOf(v)));
        RedisClientConnector.getRedis().hmset("actionId:" + getKey(), valuesToRedis);
        new AddActionToNeo4j().addAction(this, saveValuesToJSON(values));
    }

    @Override
    public void putAction(String value, Action action) {
        if (switchActions == null) {
            throw new IllegalStateException("switchActionsMap is null");
        }
        switchActions.put(value, action);
    }

    private String saveValuesToJSON(Map<String, Object> values) {

        Gson gson = new Gson();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{ ");
        values.forEach((k, v) -> {
            stringBuilder.append(k).append(":");
            if (v instanceof Collection) {
                stringBuilder.append("\"").append(gson.toJson(v).replaceAll("\"", "__")).append("\"");
            } else {
                stringBuilder.append(gson.toJson(v));
            }
            stringBuilder.append(",");
        });
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(" }");
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    @Override
    public ActionType getActionType() {
        return actionType;
    }
}
