package main.java.com.beadhouse.test;

import com.mysql.cj.x.core.MysqlxSession;
import com.mysql.cj.xdevapi.CreateTableStatementImpl;
import org.junit.Test;

import java.sql.*;
import java.util.Properties;

public class MysqlTest {
    @Test
    public void getMetadata() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "6820138");
        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
        Connection connection = DriverManager.getConnection(url, properties);
        DatabaseMetaData data = connection.getMetaData();
        ResultSet resultset = data.getTables("", "test", "", new String[]{"TABLE"});
        while (resultset.next()) {
            String tableName = resultset.getString("TABLE_NAME");
            String tableType = resultset.getString("TABLE_TYPE");
            System.out.println(tableName + " " + tableType);
        }
        connection.close();
    }

    @Test
    public void testJdbc() {
    }
}
