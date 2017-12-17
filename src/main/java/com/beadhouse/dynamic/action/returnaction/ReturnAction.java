package main.java.com.beadhouse.dynamic.action.returnaction;

import main.java.com.beadhouse.dynamic.action.ActionImpl;
import main.java.com.beadhouse.dynamic.action.ActionType;
import main.java.com.beadhouse.dynamic.datawrapper.Data;

import java.util.Map;

public class ReturnAction extends ActionImpl {
    private Data data;

    public ReturnAction(Data data) {
        this.data = data;
        this.actionType = ActionType.RETURN;
        setEnd();
    }

    @Override
    protected void getSavingValue(Map<String, Object> value) {
        value.put("returningValue", data.wrapDataToString());
    }
}
