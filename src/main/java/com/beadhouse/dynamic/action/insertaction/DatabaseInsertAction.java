package main.java.com.beadhouse.dynamic.action.insertaction;

import main.java.com.beadhouse.System.LogType;
import main.java.com.beadhouse.business.redisclient.RedisClientConnector;
import main.java.com.beadhouse.dynamic.action.Callback;
import main.java.com.beadhouse.dynamic.database.operate.DatabaseOperation;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DatabaseInsertAction extends InsertAction {
    private String schema;
    private String tableName;
    private String sql;
    private Map<String, Object> requestMap;
    private List<String> columsOrder;

    public DatabaseInsertAction(String schema, String tableName, final Map<String, Object> requestMap) {
        this.schema = schema;
        this.tableName = tableName;
        this.requestMap = requestMap;
    }

    @Override
    public String process(Callback callback) {
        DatabaseOperation operation = new DatabaseOperation(schema);
        try {
            setSQL();
            PreparedStatement preparedStatement = operation.getConnection().prepareStatement(sql);
            for (int i = 0; i < columsOrder.size(); i++) {
                preparedStatement.setString(i + 1, String.valueOf(requestMap.get(columsOrder.get(i))));
            }
            preparedStatement.execute();
            return "true";
        } catch (Exception e) {
            LogType.EXCETPION.getLOGGER().error(e);
            return e.toString();
        } finally {
            operation.close();
        }
    }

    /**
     * get sql example:insert into students (Name,Sex,Age) values(?,?,?)
     **/
    private void setSQL() {
        StringBuilder sb = new StringBuilder();
        List<String> colums = RedisClientConnector.getRedis().lrange(schema + "|" + tableName + "|columns", 0, -1);
        columsOrder = new ArrayList<>();
        sb.append("insert into ").append(tableName).append("(");
        for (String s : colums) {
            if (requestMap.containsKey(s)) {
                sb.append(s).append(",");
                columsOrder.add(s);
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(") values(");
        for (int i = 0; i < columsOrder.size(); i++) {
            sb.append("?,");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        sql = sb.toString();
        LogType.DEBUGINFO.getLOGGER().debug("insert sql:" + sb.toString());
    }
}
