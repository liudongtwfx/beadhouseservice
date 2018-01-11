package main.java.com.beadhouse.dynamic.action.updateaction;

import main.java.com.beadhouse.dynamic.action.AbstractActionImpl;
import main.java.com.beadhouse.dynamic.action.ActionType;

public abstract class UpdateAction extends AbstractActionImpl {
    public UpdateAction() {
        this.actionType = ActionType.UPDATE;
    }
}
