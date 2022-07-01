package com.hk.abgame.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created on 2022-06-28 14:32
 * 操作数据库的工具类
 * @author Xia Jiayi
 */
public class DBUtil {

    /**
     * 加载驱动，连接
     * @return 返回连接对象
     */
    public Connection getConnection() {
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/abgame?useSSL=false&serverTimezone=UTC";
            String username = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    /**
     * 关闭连接
     * @param connection 连接对象
     * @param preparedStatement 预编译对象
     * @param resultSet 结果集对象
     */
    public void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {

        try {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 执行插入操作
     * @param sql  sql语句
     * @param args the command line arguments
     */
    public void executeUpdate(String sql, Object[] args) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection, preparedStatement, null);
        }
    }

    /**
     * 查询方法
     * @param sql sql语句
     * @param args the command line arguments
     * @return 结果集
     */
    public List<Map<String, String>> query(String sql, Object[] args) {
        List<Map<String, String>> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    preparedStatement.setObject(i + 1, args[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();
            //获取结果集结构
            ResultSetMetaData metaData = resultSet.getMetaData();
            while (resultSet.next()) {
                Map<String, String> map = new java.util.HashMap<>();
                //遍历结果集
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    map.put(metaData.getColumnName(i), resultSet.getString(i));
                }
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return list;
    }
}