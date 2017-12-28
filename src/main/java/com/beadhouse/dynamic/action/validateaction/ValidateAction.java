package main.java.com.beadhouse.dynamic.action.validateaction;

import main.java.com.beadhouse.dynamic.action.ActionImpl;
import main.java.com.beadhouse.dynamic.action.ActionType;
import main.java.com.beadhouse.dynamic.action.ConnectionType;

import java.util.HashMap;
import java.util.Map;

public class ValidateAction extends ActionImpl {
    private final ValidateType validateType;
    private ValidateDataType validateDataType;
    private ValidateData validateData;

    public ValidateAction(ValidateType type, ValidateData data) {
        this.validateType = type;
        setActionType(ActionType.VALIDATE);
        switchActions = new HashMap<>();
        connectionType = ConnectionType.SWITCH;
        this.validateData = data;
    }

    public void setDataType(ValidateDataType dataType) {
        this.validateDataType = dataType;
    }

    @Override
    protected void getSavingValue(Map<String, Object> value) {
        value.put("validateType", validateType.toString());
        value.put("validateDataType", validateDataType);
        value.putAll(validateData.getDataMap());
    }

    public ValidateData getValidateData() {
        return validateData;
    }

    public ValidateType getValidateType() {
        return validateType;
    }

    public ValidateDataType getValidateDataType() {
        return validateDataType;
    }
}
