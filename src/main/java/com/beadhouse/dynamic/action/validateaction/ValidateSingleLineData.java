package main.java.com.beadhouse.dynamic.action.validateaction;

import main.java.com.beadhouse.dynamic.datawrapper.ColumnInfo;

import java.util.HashMap;
import java.util.Map;

public class ValidateSingleLineData implements ValidateData {
    private ColumnInfo comparingInfo;
    private ColumnInfo expectedInfo;

    public ValidateSingleLineData(ColumnInfo comparingInfo, ColumnInfo expextedInfo) {
        this.comparingInfo = comparingInfo;
        this.expectedInfo = expextedInfo;
    }

    @Override
    public Map<String, Object> getDataMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("comparing_table", comparingInfo.getTable());
        map.put("comparing_schema", comparingInfo.getScheme());
        map.put("comparing_column", comparingInfo.getColumnName());
        map.put("expect_schema", expectedInfo.getScheme());
        map.put("expect_table", expectedInfo.getTable());
        map.put("expect_column", expectedInfo.getColumnName());
        return map;
    }
}
