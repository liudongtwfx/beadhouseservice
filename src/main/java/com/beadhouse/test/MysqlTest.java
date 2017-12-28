package main.java.com.beadhouse.test;

import main.java.com.beadhouse.business.redisclient.RedisClientConnector;
import main.java.com.beadhouse.dynamic.actionthandler.insertactionhandler.DatabaseInsertAction;
import main.java.com.beadhouse.dynamic.datawrapper.Data;
import main.java.com.beadhouse.dynamic.datawrapper.SuccessData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MysqlTest {
    @Test
    public void getMetadata() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "6820138");
        String url = "jdbc:mysql://localhost:3306/demo?serverTimezone=UTC";
        Connection connection = DriverManager.getConnection(url, properties);
        DatabaseMetaData data = connection.getMetaData();

        ResultSet resultset = data.getTables("", "test", "", new String[]{"TABLE"});
        while (resultset.next()) {
            String tableName = resultset.getString("TABLE_NAME");
            String tableType = resultset.getString("TABLE_TYPE");
            ResultSet columnData = data.getColumns("", "test", tableName, "");
            while (columnData.next()) {
                String column_name = columnData.getString("COLUMN_NAME");
                String column_type = JDBCType.valueOf(Integer.valueOf(columnData.getString("DATA_TYPE"))).toString();
                System.out.println(column_name + " " + column_type + " ");
                RedisClientConnector.getLocalRedis().lpush("demo|" + tableName + "|columns", column_name);
            }
            ResultSet primaryKeyResultSet = data.getPrimaryKeys("", "test", tableName);
            while (primaryKeyResultSet.next()) {
                String primaryKeyColumnName = primaryKeyResultSet.getString("COLUMN_NAME");
                System.out.println("primary key : " + primaryKeyColumnName);
            }

            ResultSet indexKey = data.getIndexInfo("", "test", tableName, true, true);
            while (primaryKeyResultSet.next()) {
                String primaryKeyColumnName = indexKey.getString("COLUMN_NAME");
                System.out.println("index : " + primaryKeyColumnName);
            }
            System.out.println(tableName + " " + tableType + "\n");
        }
        connection.close();
    }

    @Test
    public void testJdbc() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "6820138");
        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
        Connection connection = DriverManager.getConnection(url, properties);
        DatabaseMetaData data = connection.getMetaData();
    }

    @Test
    public void DatabaseInsertActionTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 5);
        map.put("studentname", "liudon");
        map.put("studentid", "2015140432");
        DatabaseInsertAction action = new DatabaseInsertAction("test", "student", map);
        Data callinfo = action.handle(new SuccessData("success"));
        System.out.println(callinfo);
    }

    @Test
    public void testHtmlBuilder() throws Exception {
        File input = new File("tmp/input.html");
        System.out.println(input.getAbsolutePath());
        Document doc = Jsoup.parse(input, "UTF-8");
        doc.title("I am liudong");
        Element body = doc.body();
        if (body.childNodeSize() != 0) {
            for (Node node : body.children()) {
                node.remove();
            }
        }
        body.appendElement("div").attr("id", "liudong").attr("class", "me fine")
                .appendElement("a").attr("href", "http://www.baidu.com").appendText("My name is liudong");
        System.out.println(doc.toString());
        OutputStream stream = new FileOutputStream(input);
        stream.write(doc.toString().getBytes());
    }

    @Test
    public void queryTest() {
        System.out.println(System.currentTimeMillis());
        System.out.println(RedisClientConnector.getLocalRedis().info("Memory"));
    }
}
