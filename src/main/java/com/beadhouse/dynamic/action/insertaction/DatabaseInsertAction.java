package main.java.com.beadhouse.dynamic.action.insertaction;

import java.util.Map;

public class DatabaseInsertAction extends InsertAction {
    private String schema;
    private String table;

    DatabaseInsertAction(String schema, String table) {
        this.schema = schema;
        this.table = table;
    }

    @Override
    protected void getSavingValue(Map<String, Object> value) {
        value.put("schema", schema);
        value.put("table", table);
    }
}
