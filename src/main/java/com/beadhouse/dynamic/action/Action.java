package main.java.com.beadhouse.dynamic.action;


import java.util.Map;

public interface Action {

    boolean isStart();

    boolean isEnd();

    void setStart();

    void setEnd();

    void setUUID(String uuid);

    String getUUID();

    void save();

    void setNextType(ConnectionType type);

    Action getPreAction();

    void setPreAction(Action preAction);

    Map<String, Action> getSwitchType();

    void putAction(String value, Action action);

    ActionType getActionType();
}
