package main.java.com.beadhouse.dynamic.action.queryaction;

import main.java.com.beadhouse.dynamic.action.ActionImpl;

import java.util.List;
import java.util.Map;

public class DatabaseQueryAction extends ActionImpl {
    private String schema;
    private String tableName;
    private List<String> selectFields;

    public DatabaseQueryAction(String schema, String tableName) {
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setSelectFields(List<String> selectFields) {
        this.selectFields = selectFields;
    }

    @Override
    protected void getSavingValue(Map<String, Object> value) {
        value.put("schema", schema);
        value.put("table", tableName);
        value.put("selectFields", selectFields);
    }
}
