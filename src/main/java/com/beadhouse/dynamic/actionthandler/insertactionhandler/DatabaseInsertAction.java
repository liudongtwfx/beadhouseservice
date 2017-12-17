package main.java.com.beadhouse.dynamic.actionthandler.insertactionhandler;

import main.java.com.beadhouse.System.LogType;
import main.java.com.beadhouse.business.redisclient.RedisClientConnector;
import main.java.com.beadhouse.dynamic.action.Callback;
import main.java.com.beadhouse.dynamic.action.insertaction.InsertAction;
import main.java.com.beadhouse.dynamic.actionthandler.ActionHandler;
import main.java.com.beadhouse.dynamic.database.operate.DatabaseOperation;
import main.java.com.beadhouse.dynamic.datawrapper.Data;
import main.java.com.beadhouse.dynamic.datawrapper.ExceptionOrFailedData;
import main.java.com.beadhouse.dynamic.datawrapper.SQLResultData;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DatabaseInsertAction implements ActionHandler {
    private String schema;
    private String tableName;
    private String sql;
    private Map<String, Object> requestMap;
    private List<String> columsOrder;
    private List<String> values;

    public DatabaseInsertAction(String schema, String tableName, final Map<String, Object> requestMap) {
        this.schema = schema;
        this.tableName = tableName;
        this.requestMap = requestMap;
    }

    @Override
    public Data handle() {
        DatabaseOperation operation = new DatabaseOperation(schema);
        try {
            setSQL();
            PreparedStatement preparedStatement = operation.getConnection().prepareStatement(sql);
            for (int i = 0; i < columsOrder.size(); i++) {
                LogType.DEBUGINFO.getLOGGER().debug(values.get(i));
                preparedStatement.setString(i + 1, values.get(i));
            }
            preparedStatement.execute();
            return new SQLResultData(schema, tableName);
        } catch (Exception e) {
            LogType.EXCETPION.getLOGGER().error(e);
            return new ExceptionOrFailedData(e.toString());
        } finally {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
        values = new ArrayList<>();
        sb.append("insert into ").append(tableName).append("(");
        for (String s : colums) {
            if (requestMap.containsKey(s)) {
                sb.append(s).append(",");
                columsOrder.add(s);
                values.add(((String[]) requestMap.get(s))[0]);
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
