package com.tt.db;

import sun.dc.pr.PRError;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 连接数据库的工具类
 *
 * @author amoslv
 * @date 2018/07/25
 */
public class DBHelper {

    /**
     * 创建表的名字
     */
    private static final String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8";

    private static final String name = "com.mysql.jdbc.Driver";

    /**
     * 连接数据库账号
     */
    private static final String user = "root";

    /**
     * 连接数据库密码
     */
    private static final String password = "lv1031";

    private Connection connection = null;

    public PreparedStatement preparedStatement = null;

    public DBHelper(String sql) {
        try {
            Class.forName(name);
            connection = DriverManager.getConnection(url, user, password);
            preparedStatement = connection.prepareStatement(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            this.connection.close();
            this.preparedStatement.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
