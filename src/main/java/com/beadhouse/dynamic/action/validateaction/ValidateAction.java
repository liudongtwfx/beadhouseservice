package main.java.com.beadhouse.dynamic.action.validateaction;

import main.java.com.beadhouse.dynamic.action.ActionImpl;
import main.java.com.beadhouse.dynamic.action.ActionType;
import main.java.com.beadhouse.dynamic.action.ConnectionType;

import java.util.HashMap;
import java.util.Map;

public class ValidateAction extends ActionImpl {
    private final ValidateType validateType;
    private String comparingType; //type
    private String exactValue; //left
    private String expectedValue; //right
    private ValidateDataType dataType;

    public ValidateAction(ValidateType type) {
        this.validateType = type;
        setActionType(ActionType.VALIDATE);
        switchActions = new HashMap<>();
        connectionType = ConnectionType.SWITCH;
    }

    public void setComparingType(String comparingType) {
        this.comparingType = comparingType;
    }

    public void setExactValue(String exactValue) {
        this.exactValue = exactValue;
    }

    public void setExpectedType(String expectedValue) {
        this.expectedValue = expectedValue;
    }

    public void setDataType(ValidateDataType dataType) {
        this.dataType = dataType;
    }

    @Override
    protected void getSavingValue(Map<String, Object> value) {
        value.put("validateType", validateType.toString());
        value.put("comparingType", comparingType);
        value.put("expectedValue", expectedValue);
        value.put("validateDataType", dataType);
    }
}
