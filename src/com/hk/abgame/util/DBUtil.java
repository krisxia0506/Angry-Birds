package com.hk.abgame.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created on 2022-06-28 14:32
 * 操作数据库的工具类
 *
 * @author Xia Jiayi
 */
public class DBUtil {
    private final String url = "jdbc:mysql://localhost:3306/abgame?useSSL=false&serverTimezone=UTC";
    private final String username = "root";
    private final String password = "123456";

    /**
     * 加载驱动，连接
     */
    public Connection getConnection() {
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    /**
     * 关闭连接
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
     *
     * @param args the command line arguments
     * @return 影响的行数
     */

    public int executeUpdate(String sql, Object[] args) {
        int result;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection, preparedStatement, null);
        }
        return result;
    }

    /**
     * 查询方法
     */
    public List<Map<String, String>> query(String sql, Object[] params) {
        List<Map<String, String>> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();
            //获取结果集结构
            ResultSetMetaData metaData = resultSet.getMetaData();
            while (resultSet.next()) {
                Map<String, String> map = new java.util.HashMap<>();
                //遍历结果集
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    //map.put(metaData.getColumnLabel(i), resultSet.getString(i));
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