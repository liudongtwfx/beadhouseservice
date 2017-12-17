package main.java.com.beadhouse.dynamic.database.operate;

import main.java.com.beadhouse.System.LogType;
import org.hswebframework.ezorm.rdb.RDBDatabase;
import org.hswebframework.ezorm.rdb.executor.AbstractJdbcSqlExecutor;
import org.hswebframework.ezorm.rdb.executor.SqlExecutor;
import org.hswebframework.ezorm.rdb.meta.RDBDatabaseMetaData;
import org.hswebframework.ezorm.rdb.render.dialect.MysqlRDBDatabaseMetaData;
import org.hswebframework.ezorm.rdb.simple.SimpleDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseOperation implements AutoCloseable {
    private final RDBDatabaseMetaData metaData;
    private final RDBDatabase database;
    private SqlExecutor executor;
    private Connection connection;

    public DatabaseOperation(String schema) {
        try {
            setup(schema);
        } catch (Exception e) {
            LogType.EXCETPION.getLOGGER().error(e);
        }
        metaData = new MysqlRDBDatabaseMetaData();
        database = new SimpleDatabase(metaData, executor);
    }

    private void setup(String schema) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "6820138");
        String url = "jdbc:mysql://localhost:3306/" + schema + "?serverTimezone=UTC";
        connection = DriverManager.getConnection(url, properties);
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

    @Override
    public final void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LogType.EXCETPION.getLOGGER().error("can't close connection");
            e.printStackTrace();
        }
    }

    public RDBDatabaseMetaData getMetaData() {
        return metaData;
    }

    public RDBDatabase getDatabase() {
        return database;
    }

    public SqlExecutor getExecutor() {
        return executor;
    }

    public Connection getConnection() {
        return connection;
    }
}
