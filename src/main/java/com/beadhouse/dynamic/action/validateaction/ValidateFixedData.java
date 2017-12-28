package main.java.com.beadhouse.dynamic.action.validateaction;

import main.java.com.beadhouse.dynamic.datawrapper.ColumnInfo;

import java.util.HashMap;
import java.util.Map;

public class ValidateFixedData implements ValidateData {
    private static final ValidateDataType DATA_TYPE = ValidateDataType.FIXED;
    private String comparingType;            //type
    private ColumnInfo exactValueInfo;       //left
    private String expectedValue;            //right

    public ValidateFixedData(ColumnInfo exactValueInfo, String comparingType, String expectedValue) {
        this.comparingType = comparingType;
        this.expectedValue = expectedValue;
        this.exactValueInfo = exactValueInfo;
    }

    @Override
    public Map<String, Object> getDataMap() {
        Map<String, Object> value = new HashMap<>();
        value.put("comparingType", comparingType);
        value.put("exactcolumn_schema", exactValueInfo.getScheme());
        value.put("exactcolumn_table", exactValueInfo.getTable());
        value.put("exactcolumn_columnName", exactValueInfo.getColumnName());
        value.put("expectedValue", expectedValue);
        return value;
    }

    public static ValidateDataType getDataType() {
        return DATA_TYPE;
    }
}
