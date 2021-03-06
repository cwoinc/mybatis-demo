package com.wantdo.mybatisdemo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class EmbedDB {

    private static String driver = "org.apache.derby.jdbc.AutoloadedDriver";
    //    private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static String protocol = "jdbc:derby:db3;create=true"; // 在工程目录下创建数据库
    // private static String protocol = "jdbc:derby:db/db3;create=true";    //在工程目录下db目录中创建数据库
    // private static String protocol = "jdbc:derby:D:/mydbs/db3;create=true";    //在D:/mydbs/目录下创建数据库

    public static void main(String[] args) {
        try {
            Class.forName(driver).newInstance();
            System.out.println("Loaded the appropriate driver");
            Connection conn = DriverManager.getConnection(protocol);
            String table_name = "t" + System.currentTimeMillis();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("create table " + table_name + "(id int not null generated by default as identity,stuname varchar(20),email varchar(30))");

            for (String str : "one,two,three,four,five".split(",")) {
                String sql = "insert into " + table_name + "(stuname,email) values('" + str + "','" + str + "@test.com')";
                System.out.println(sql);
                stmt.addBatch(sql);
            }
            stmt.executeBatch();
            System.out.println("insert over");
            conn.commit();

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}