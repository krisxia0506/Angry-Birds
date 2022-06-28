package com.hk.abgame.util;

import com.hk.abgame.bean.Player;

import java.sql.*;
import java.util.Iterator;

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

    /*加载驱动，连接*/
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

    //    释放资源
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
        int result = 0;
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


    public Player executeQuery(String sql, Object[] params, Class<Player> playerClass) {
        Player player = null;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                player = playerClass.newInstance();
                player.setLoginname(resultSet.getString("loginname"));
                player.setPassword(resultSet.getString("password"));
                player.setNickname(resultSet.getString("nickname"));
                player.setSex(resultSet.getInt("sex"));
            }
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return player;
    }
}