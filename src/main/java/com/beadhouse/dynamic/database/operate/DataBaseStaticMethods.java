package main.java.com.beadhouse.dynamic.database.operate;

import main.java.com.beadhouse.business.redisclient.RedisClientConnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBaseStaticMethods {
    private final static String[] dataBase = {"demo", "test"};

    private static Map<String, List<String>> tableNameMap = new HashMap<>();

    static {
        update();
    }

    public static Map<String, List<String>> getTableNameMap() {
        return tableNameMap;
    }

    public static void update() {
        for (String schema : dataBase) {
            tableNameMap.put(schema, new DatabaseStructureQuery(schema).getAllTablesName());
        }
    }

    public static List<String> getTables(String schema) {
        return tableNameMap.get(schema);
    }

    public static List<String> getColumns(String schema, String table) {
        return RedisClientConnector.getRedis().lrange(schema + "|" + table + "|columns", 0, -1);
    }
}
