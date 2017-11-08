package main.java.com.beadhouse.dynamic.database.databasemetadata;

import main.java.com.beadhouse.System.LogType;
import org.hswebframework.ezorm.rdb.RDBDatabase;
import org.hswebframework.ezorm.rdb.executor.AbstractJdbcSqlExecutor;
import org.hswebframework.ezorm.rdb.executor.SqlExecutor;
import org.hswebframework.ezorm.rdb.meta.RDBColumnMetaData;
import org.hswebframework.ezorm.rdb.meta.RDBDatabaseMetaData;
import org.hswebframework.ezorm.rdb.meta.builder.TableBuilder;
import org.hswebframework.ezorm.rdb.render.dialect.MysqlRDBDatabaseMetaData;
import org.hswebframework.ezorm.rdb.simple.SimpleDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public final class CreateTableImpl {
    private RDBDatabaseMetaData metaData;
    private RDBDatabase database;
    private SqlExecutor executor;
    private Table table;

    public CreateTableImpl() {
        try {
            setup();
        } catch (Exception e) {
            LogType.EXCETPION.getLOGGER().error(e);
        }
        metaData = new MysqlRDBDatabaseMetaData();
        database = new SimpleDatabase(metaData, executor);
        table = new Table();
    }

    private void setup() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "6820138");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?serverTimezone=UTC", properties);
        executor = new AbstractJdbcSqlExecutor() {
            @Override
            public Connection getConnection() {
                return connection;
            }

            @Override
            public void releaseConnection(Connection connection) throws SQLException {

            }
        };
    }

    public boolean createTableByTable() {
        try {
            TableBuilder builder = database.createOrAlter(table.getTableName());
            for (RDBColumnMetaData column : table.getColumns()) {
                builder.addColumn().name(column.getName());
            }
            return true;
        } catch (Exception e) {
            LogType.EXCETPION.getLOGGER().error(e);
            return false;
        }
    }

    public Table getTable() {
        return table;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String tableName = table.getTableName();
        sb.append("table name is: ").append(tableName != null ? tableName : "null").append("\n");
        sb.append("column size: ").append(table.getColumns().size()).append("\n");
        for (RDBColumnMetaData column : table.getColumns()) {
            sb.append("name").append(column.getFullName()).append(", type:").append(column.getJdbcType());
        }
        return sb.toString();
    }
}
