package main.java.com.beadhouse.dynamic.database.operate;

import main.java.com.beadhouse.System.LogType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseStructureQuery {
    private String schema;

    public DatabaseStructureQuery() {
        this("test");
    }

    public DatabaseStructureQuery(String schema) {
        this.schema = schema;
    }

    public List<String> getAllTablesName() {
        DatabaseOperation operation = new DatabaseOperation(schema);
        List<String> res = new ArrayList<>();
        try {

            ResultSet resultSet = operation.getConnection().
                    getMetaData().getTables("", schema, "", new String[]{"TABLE"});
            while (resultSet.next()) {
                res.add(resultSet.getString("TABLE_NAME"));
            }
        } catch (Exception e) {
            LogType.EXCETPION.getLOGGER().error(e);
            e.printStackTrace();
        } finally {
            operation.close();
        }
        res.forEach(System.out::println);
        return res;
    }

    public Map<String, ResultSet> getColumnInfo(String tableName) {
        DatabaseOperation operation = new DatabaseOperation(schema);
        Map<String, ResultSet> resultSetMap = new HashMap<>();
        try {
            resultSetMap.put("columns", operation.getConnection().
                    getMetaData().getColumns("", schema, tableName, ""));
            resultSetMap.put("primarykey", operation.getConnection().
                    getMetaData().getPrimaryKeys("", schema, tableName));
        } catch (Exception e) {
            LogType.EXCETPION.getLOGGER().error(e);
        } finally {
            operation.close();
        }
        return resultSetMap;
    }
}
