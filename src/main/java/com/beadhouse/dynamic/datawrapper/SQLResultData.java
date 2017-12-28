package main.java.com.beadhouse.dynamic.datawrapper;

import main.java.com.beadhouse.business.redisclient.RedisClientConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLResultData implements Data {
    private DataSourceType type;
    private String schema;
    private String tableName;
    private List<String> columns;
    private List<List<String>> resultValues;
    private int idIndex = -1;
    private boolean singleResult = false;

    public SQLResultData(String schema, String tableName) {
        this.schema = schema;
        this.tableName = tableName;
        setSource();
    }

    @Override
    public void setSource() {
        this.type = DataSourceType.MYSQL;
    }

    @Override
    public String wrapDataToString() {
        return toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        columns.forEach(v -> sb.append(v).append(" "));
        sb.append("\n");
        resultValues.forEach((row) -> {
            row.forEach(v -> sb.append(v).append(" "));
            sb.append("\n");
        });
        return sb.toString();
    }

    public void wrapData(ResultSet resultSet, List<String> columns) {
        setColumns(columns);
        resultValues = new ArrayList<>();
        try {
            while (resultSet.next()) {
                List<String> row = new ArrayList<>();
                for (String column : this.columns) {
                    String ans = resultSet.getString(column);
                    row.add(ans);
                }
                resultValues.add(row);
            }
            singleResult = resultValues.size() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setColumns(List<String> columns) {
        if (columns == null || columns.size() == 0) {
            this.columns = RedisClientConnector.getRedis().lrange(schema + "|" + tableName + "|columns", 0, -1);
            this.idIndex = this.columns.indexOf("id");
        } else {
            this.columns = columns;
        }
    }

    public List<List<String>> getResultValues() {
        return resultValues;
    }

    public List<String> getColumns() {
        return columns;
    }

    public int getIdIndex() {
        return idIndex;
    }

    public boolean isSingleResult() {
        return singleResult;
    }
}
