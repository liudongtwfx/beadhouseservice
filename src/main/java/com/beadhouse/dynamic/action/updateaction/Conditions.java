package main.java.com.beadhouse.dynamic.action.updateaction;

import java.io.Serializable;

public class Conditions implements Serializable {
    private final UpdateType updateType;

    public Conditions(UpdateType type) {
        updateType = type;
    }

    public UpdateType getUpdateType() {
        return updateType;
    }
}
