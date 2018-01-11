package main.java.com.beadhouse.dynamic.action.updateaction;

import com.google.gson.Gson;

import java.util.Map;

public class DatabaseUpdateAction extends UpdateAction {
    private String schema;
    private String table;
    private String column;
    private Conditions conditions;

    public DatabaseUpdateAction(String schema, String table,
                                String column, Conditions conditions) {
        this.schema = schema;
        this.table = table;
        this.column = column;
        this.conditions = conditions;
    }

    @Override
    protected void getSavingValue(Map<String, Object> value) {
        value.put("schema", schema);
        value.put("table", table);
        value.put("column", column);
        value.put("conditions", new Gson().toJson(conditions));
    }
}
