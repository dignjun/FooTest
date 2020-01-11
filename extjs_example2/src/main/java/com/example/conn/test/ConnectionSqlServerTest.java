package com.example.conn.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 测试SqlServer数据库的连接与image数据类型的存储(无blob类型, 二进制数据使用image类型存储)
 */
public class ConnectionSqlServerTest {

    public static void main(String[] args) {

        Connection con = null;          // 创建一个数据库连接
        PreparedStatement pre = null;   // 创建预编译语句对象，一般都是用这个而不用Statement
        ResultSet result = null;        // 创建一个结果集对象
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");       // 加载Oracle驱动程序
            System.out.println("开始尝试连接数据库！");
            String url = "jdbc:sqlserver://172.16.1.159:1433;DatabaseName=zgxthomebankdb";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
            String user = "zgxtuser";       // 用户名,系统默认的账户名
            String password = "zgxtuser";   // 你安装时选设置的密码
            con = DriverManager.getConnection(url, user, password);// 获取连接
            System.out.println("连接成功！");

            String insertSql = "insert into AUDIT_VAL_HIS (VALUE_ID, VALUE_DATA) values (?,?)";
            pre = con.prepareStatement(insertSql);
            pre.setString(1, "abc");
            pre.setBytes(2, "abc".getBytes());
            pre.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null)
                    result.close();
                if (pre != null)
                    pre.close();
                if (con != null)
                    con.close();
                System.out.println("数据库连接已关闭!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
