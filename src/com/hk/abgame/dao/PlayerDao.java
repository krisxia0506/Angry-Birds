package com.hk.abgame.dao;

import com.hk.abgame.bean.Player;
import com.hk.abgame.util.DBUtil;

/**
 * Created on 2022-06-28 15:11
 *
 * @author Xia Jiayi
 */
public class PlayerDao {
    DBUtil dbUtil = new DBUtil();

    /**
     * 新增玩家
     */
    public void addPlayer(Player player) {
        String sql = "insert into player(loginname,password,nickname,sex,age) values(?,?,?,?,?)";
        Object[] params = {player.getLoginname(), player.getPassword(), player.getNickname(),
                player.getSex(), player.getAge()};
        dbUtil.executeUpdate(sql, params);
    }

    /**
     * 根据登陆名查找玩家
     */
    public Player findPlayerByLoginname(String loginname) {
        String sql = "select * from player where loginname = ?";
        Object[] params = {loginname};
        return dbUtil.executeQuery(sql, params, Player.class);

    }
    /**
     * 删除玩家
     */
    public void deletePlayer(Player player) {
        String sql = "delete from player where loginname = ?";
        Object[] params = {player.getLoginname()};
        dbUtil.executeUpdate(sql, params);
    }
    /**
     * 修改玩家
     */
public void updatePlayer(Player player) {
    String sql = "update player set loginname = ?, password = ?,nickname = ?,sex = ?,age = ? where id = ?;";
    Object[] params = {player.getLoginname(), player.getPassword(), player.getNickname(),
            player.getSex(), player.getAge(),player.getId()};
    dbUtil.executeUpdate(sql, params);
}


    public Player findPlayerById(int id) {
        String sql = "select * from player where id = ?";
        Object[] params = {id};
        return dbUtil.executeQuery(sql, params, Player.class);
    }
}

