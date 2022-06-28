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
    public int addPlayer(Player player) {
        String sql = "insert into player(loginname,password,nickname,sex,age) values(?,?,?,?,?)";
        Object[] params = {player.getLoginname(), player.getPassword(), player.getNickname(),
                player.getSex(), player.getAge()};
        return dbUtil.executeUpdate(sql, params);
    }

    /**
     * 根据登陆名查找玩家
     */
    public Player findPlayerByLoginname(String loginname) {
        String sql = "select * from player where loginname = ?";
        Object[] params = {loginname};
        return dbUtil.executeQuery(sql, params, Player.class);

    }
}

