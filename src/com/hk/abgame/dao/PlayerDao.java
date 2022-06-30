package com.hk.abgame.dao;

import com.hk.abgame.bean.Player;
import com.hk.abgame.util.DBUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created on 2022-06-28 15:11
 * 玩家类的数据访问层
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
        List<Player> list = queryPlayerBySql(sql, params);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 删除玩家
     */
    public void deletePlayer(Player player) {
        String sql = "delete from player where id = ?";
        Object[] params = {player.getId()};
        dbUtil.executeUpdate(sql, params);
    }

    /**
     * 修改玩家
     */
    public void updatePlayer(Player player) {
        String sql = "update player set loginname = ?, password = ?,nickname = ?,sex = ?,age = ? where id = ?;";
        Object[] params = {player.getLoginname(), player.getPassword(), player.getNickname(),
                player.getSex(), player.getAge(), player.getId()};
        dbUtil.executeUpdate(sql, params);
    }

    /**
     * 根据ID查询某个玩家
     */
    public List<Player> findPlayerById(int id) {
        String sql = "select * from player where id = ?";
        Object[] params = {id};
        return queryPlayerBySql(sql, params);
    }

    /**
     * 查询所有玩家
     */
    public List<Player> findAllPlayer() {
        String sql = "select * from player";
        return queryPlayerBySql(sql, null);

    }

    /**
     * List<Map>--->List<Player>
     */
    public List<Player> queryPlayerBySql(String sql, Object[] params) {
        //创建存储玩家类型的集合
        List<Player> players = new ArrayList<Player>();
        //调用DBUtil的query方法，返回一个List<Map>
        List<Map<String, String>> list = dbUtil.query(sql, params);
        //遍历list，将Map中的数据封装到Player中，添加到players集合中
        for (Map<String, String> m : list) {
            Player player = new Player();
            player.setId(Integer.parseInt(m.get("id")));
            player.setLoginname(m.get("loginname"));
            player.setPassword(m.get("password"));
            player.setNickname(m.get("nickname"));
            player.setSex(Integer.valueOf(m.get("sex")));
            player.setAge(Integer.valueOf(m.get("age")));
            players.add(player);
        }
        return players;
    }
}

