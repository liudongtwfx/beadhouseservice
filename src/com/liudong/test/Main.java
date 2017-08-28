package com.liudong.test;

import com.liudong.business.filehandle.FileHandler;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Created by liudong on 2017/4/26.
 */

public class Main {
    private static final SecureRandom RANDOM;
    private static final int HASHING_ROUNDS = 10;

    static {
        try {
            RANDOM = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException();
        }
    }

    public static String getBCryptPassword(String password) {
        if (password != null && password.length() > 0) {
            long start = System.currentTimeMillis();
            String salt = BCrypt.gensalt(HASHING_ROUNDS, RANDOM);
            System.out.println("gensalt " + String.valueOf(System.currentTimeMillis() - start));
            String s = BCrypt.hashpw(password, salt);
            System.out.println(RANDOM.getAlgorithm());
            return s;
        }
        return "";
    }

    public static void main(String[] args) throws ParseException, SQLException, ClassNotFoundException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/demo?useSSL=false";
        String user = "root";
        String password = "6820138";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, user, password);
        String sql = "INSERT INTO vipusers(username, password, emailAddress, telephoneNumber) VALUES (?,?,?,?)";
        try {
            PreparedStatement prep = conn.prepareStatement(sql);

            // 将连接的自动提交关闭，数据在传送到数据库的过程中相当耗时
            conn.setAutoCommit(false);
            long start = System.currentTimeMillis();

            for (int i = 0; i < 1; i++) {

                long start2 = System.currentTimeMillis();
                // 一次性执行插入10万条数据
                for (int j = 12; j < 1000; j++) {
                    System.out.println(j);
                    NumberFormat format = NumberFormat.getInstance();
                    format.setMinimumIntegerDigits(4);
                    prep.setString(1, FileHandler.getRandomName());
                    long start3 = System.currentTimeMillis();
                    prep.setString(2, "liu@6820138");
                    System.out.println(System.currentTimeMillis() - start3);
                    prep.setString(3, "liudongtwfx" + format.format(j).replaceAll(",", ""));
                    prep.setString(4, "1312194" + format.format(j).replaceAll(",", ""));

                    // 将预处理添加到批中
                    prep.addBatch();

                }
                //Thread.sleep(100000);
                // 预处理批量执行
                prep.executeBatch();
                prep.clearBatch();
                conn.commit();

                long end2 = System.currentTimeMillis();

                // 批量执行一次批量打印执行依次的时间
                System.out.print("inner" + i + ": ");
                System.out.println(end2 - start2);

            }

            long end = System.currentTimeMillis();
            System.out.print("total: ");
            System.out.println(end - start);

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            conn.close();
        }
    }
}
