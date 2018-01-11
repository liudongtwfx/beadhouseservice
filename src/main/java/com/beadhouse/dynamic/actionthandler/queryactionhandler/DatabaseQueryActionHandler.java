package main.java.com.beadhouse.dynamic.actionthandler.queryactionhandler;

import main.java.com.beadhouse.System.LogType;
import main.java.com.beadhouse.business.redisclient.RedisClientConnector;
import main.java.com.beadhouse.dynamic.action.Action;
import main.java.com.beadhouse.dynamic.action.queryaction.DatabaseQueryAction;
import main.java.com.beadhouse.dynamic.actionthandler.ActionHandler;
import main.java.com.beadhouse.dynamic.database.operate.DatabaseOperation;
import main.java.com.beadhouse.dynamic.datawrapper.Data;
import main.java.com.beadhouse.dynamic.datawrapper.SQLResultData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DatabaseQueryActionHandler implements ActionHandler {
    private String schema;
    private String tableName;
    private Map<String, String> queryParam;
    private List<String> selectFields;

    public DatabaseQueryActionHandler(String schema, String tableName, List<String> selectFields) {
        this(schema, tableName, new HashMap<>(), selectFields);
    }

    public DatabaseQueryActionHandler(String schema, String tableName) {
        this(schema, tableName, new HashMap<>(), new ArrayList<>());
    }

    public DatabaseQueryActionHandler(String schema, String tableName, Map<String, String> params) {
        this(schema, tableName, params, new ArrayList<>());
    }

    public DatabaseQueryActionHandler(String schema, String tableName, Map<String, String> queryParam, List<String> columns) {
        this.schema = schema;
        this.tableName = tableName;
        this.queryParam = queryParam;
        this.selectFields = columns;
    }

    public DatabaseQueryActionHandler(Action action, Map<String, String> queryParam) {
        if (action instanceof DatabaseQueryAction) {
            DatabaseQueryAction instanse = (DatabaseQueryAction) action;
            this.schema = instanse.getSchema();
            this.tableName = instanse.getTableName();
            this.selectFields = instanse.getSelectFields();
            this.queryParam = queryParam;
        } else {
            throw new IllegalStateException("Query Action类型转换错误");
        }
    }

    @Override
    public SQLResultData handle(Data in) {
        SQLResultData data = new SQLResultData(schema, tableName);
        data.wrapData(getQueryResult(), selectFields);
        return data;
    }

    private ResultSet getQueryResult() {
        DatabaseOperation operation = new DatabaseOperation(schema);
        try {
            String sql = getSQL();
            LogType.DEBUGINFO.getLOGGER().debug(sql);
            PreparedStatement statement = operation.getConnection().prepareStatement(sql);
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            LogType.EXCETPION.getLOGGER().error(e);
            e.printStackTrace();
            try {
                operation.getConnection().rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return null;
        }
    }

    private String getSQL() {
        List<String> columnNames = RedisClientConnector.getRedis().lrange(schema + "|" + tableName + "|columns", 0, -1);
        StringBuilder sb = new StringBuilder();
        sb.append("select ");
        if (selectFields == null || selectFields.size() == 0) {
            sb.append("*");
        } else {
            getSelectFieldSQL(sb);
        }
        sb.append(" from ").append(tableName);
        if (queryParam == null || queryParam.size() == 0) {
            return sb.toString();
        }
        sb.append(" where ");
        for (String col : columnNames) {
            if (queryParam.containsKey(col)) {
                sb.append(col).append("=").append(queryParam.get(col));
                sb.append(" and ");
            }
        }
        sb.delete(sb.length() - 5, sb.length());
        setOrderSQL(sb);
        return sb.toString();
    }

    private void setOrderSQL(StringBuilder sb) {
        if (queryParam.containsKey("sort")) {
            String sortParam = queryParam.get("sort");
            if (sortParam.equals("asc") || sortParam.equals("desc")) {
                sb.append(" order by ").append(queryParam.get("orderIndex ")).append(sortParam.toUpperCase());
            }
        }
        setPagenationSQL(sb);
    }

    private StringBuilder setPagenationSQL(StringBuilder sb) {
        if (!queryParam.containsKey("page") || !queryParam.containsKey("pagesize")) {
            return sb;
        }
        try {
            int page = Integer.parseInt(queryParam.get("page"));
            int size = Integer.parseInt(queryParam.get("pagesize"));
            int minIndex = (page - 1) * size;
            int maxIndex = page * size;
            sb.append(" limit ").append(minIndex).append(",").append(maxIndex);
        } catch (NumberFormatException e) {
            LogType.EXCETPION.getLOGGER().error(e);
            e.printStackTrace();
        }
        return sb;
    }

    public void setSelectFields(List<String> selectFields) {
        this.selectFields = selectFields;
    }

    private void getSelectFieldSQL(StringBuilder sb) {
        if (selectFields == null) {
            return;
        }
        selectFields.forEach(field -> sb.append(field).append(","));
        sb.deleteCharAt(sb.length() - 1);
    }
}

