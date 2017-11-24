package main.java.com.beadhouse.dynamic.action.queryaction;

import main.java.com.beadhouse.System.LogType;
import main.java.com.beadhouse.business.redisclient.RedisClientConnector;
import main.java.com.beadhouse.dynamic.action.Action;
import main.java.com.beadhouse.dynamic.action.Callback;
import main.java.com.beadhouse.dynamic.database.operate.DatabaseOperation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class DatabaseQueryAction implements Action {
    private String schema;
    private String tableName;
    private Map<String, String> queryParam;

    public DatabaseQueryAction(String schema, String tableName) {
        this(schema, tableName, new HashMap<>());
    }

    public DatabaseQueryAction(String schema, String tableName, Map<String, String> queryParam) {
        this.schema = schema;
        this.tableName = tableName;
        this.queryParam = queryParam;
    }

    @Override
    public String process(Callback callback) {
        String res = getQueryResult().toString();
        if (callback != null) {
            callback.call();
        }
        return res;
    }


    public ResultSet getQueryResult() {
        if (queryParam == null || queryParam.size() == 0) {
            return null;
        }
        DatabaseOperation operation = new DatabaseOperation(schema);
        try {
            String sql = getSQL();
            PreparedStatement statement = operation.getConnection().prepareStatement(sql);
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            LogType.EXCETPION.getLOGGER().error(e);
            e.printStackTrace();
            return null;
        }
    }

    private String getSQL() {
        List<String> columnNames = RedisClientConnector.getRedis().lrange(schema + "|" + tableName + "|columns", 0, -1);
        StringBuilder sb = new StringBuilder();
        sb.append("select * from ").append(tableName).append(" where ");
        for (String col : columnNames) {
            if (queryParam.containsKey(col)) {
                sb.append(col).append("=").append(queryParam.get(col));
                sb.append(" and ");
            }
        }
        sb.delete(sb.length() - 6, sb.length() - 1);
        return setOrderSQL(sb).toString();
    }

    private StringBuilder setOrderSQL(StringBuilder sb) {
        if (queryParam.containsKey("sort") && queryParam.get("sort").equals("true")) {
            return sb;
        }
        sb.append(" order by ").append(queryParam.get("orderIndex"));
        return setPagenationSQL(sb);
    }

    private StringBuilder setPagenationSQL(StringBuilder sb) {
        if (!queryParam.containsKey("page") || !queryParam.containsKey("pagesize")) {
            return sb;
        }
        try {
            int page = Integer.parseInt(queryParam.get("page"));
            int size = Integer.parseInt(queryParam.get("size"));
            int minIndex = (page - 1) * size;
            int maxIndex = page * size;
            sb.append(" limit ").append(minIndex).append(",").append(maxIndex);
        } catch (NumberFormatException e) {
            LogType.EXCETPION.getLOGGER().error(e);
            e.printStackTrace();
        }
        return sb;
    }
}
