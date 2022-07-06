package com.hk.abgame.dao;

import com.hk.abgame.bean.Login;
import com.hk.abgame.bean.Player;
import com.hk.abgame.exception.SysException;
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
     * 检查玩家登陆
     */
    public Player checkPlayer(Login login) {
        String sql = "select * from player where login_name = ? and password = ?";
        Object[] params = {login.getLogin_name(), login.getPassword()};
        List<Player> player = queryPlayerBySql(sql, params);
        if (player.size() > 0) {
            return player.get(0);
        }
        return null;
    }

    /**
     * 新增玩家
     * @param player 玩家对象
     */
    public void addPlayer(Player player) throws SysException {
        String sql = "insert into player(login_name,password,nickname,sex,age) values(?,?,?,?,?)";
        Object[] params = {player.getLogin_name(), player.getPassword(), player.getNickname(),
                player.getSex(), player.getAge()};
        dbUtil.executeUpdate(sql, params);
    }

    /**
     * 根据登陆名查找玩家
     * @param login_name 登陆名
     * @return 玩家对象
     */
    public Player findPlayerByLoginName(String login_name) {
        String sql = "select * from player where login_name = ?";
        Object[] params = {login_name};
        List<Player> list = queryPlayerBySql(sql, params);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 删除玩家
     * @param player 玩家对象
     */
    public void deletePlayer(Player player) throws SysException {
        String sql = "delete from player where id = ?";
        Object[] params = {player.getId()};
        dbUtil.executeUpdate(sql, params);
    }

    /**
     * 修改玩家
     * @param player 玩家
     */
    public void updatePlayer(Player player) throws SysException {
        String sql = "update player set login_name = ?, password = ?,nickname = ?,sex = ?,age = ? where id = ?;";
        Object[] params = {player.getLogin_name(), player.getPassword(), player.getNickname(),
                player.getSex(), player.getAge(), player.getId()};
        dbUtil.executeUpdate(sql, params);
    }

    /**
     * 根据ID查询某个玩家
     * @param id 玩家ID
     * @return 单个玩家对象
     */
    public Player findPlayerById(int id) {
        String sql = "select * from player where id = ?";
        Object[] params = {id};
        List<Player> list = queryPlayerBySql(sql, params);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 查询所有玩家
     * @return 玩家列表
     */
    public List<Player> findAllPlayer() {
        String sql = "select * from player";
        return queryPlayerBySql(sql, null);
    }

    /**
     * List<Map>--->List<Player>
     * @param sql sql语句
     * @param params 参数
     * @return 玩家列表
     */
    public List<Player> queryPlayerBySql(String sql, Object[] params) {
        //创建存储玩家类型的集合
        List<Player> players = new ArrayList<>();
        //调用DBUtil的query方法，返回一个List<Map>
        List<Map<String, String>> list = dbUtil.query(sql, params);
        //遍历list，将Map中的数据封装到Player中，添加到players集合中
        for (Map<String, String> m : list) {
            Player player = new Player();
            player.setId(Integer.parseInt(m.get("id")));
            player.setLogin_name(m.get("login_name"));
            player.setPassword(m.get("password"));
            player.setNickname(m.get("nickname"));
            player.setSex(Integer.valueOf(m.get("sex")));
            player.setAge(Integer.valueOf(m.get("age")));
            player.setCreat_time(m.get("creat_time"));
            players.add(player);
        }
        return players;
    }

    public void updatePassword(Integer id, String password) throws SysException {
        String sql = "update player set password = ? where id = ?";
        Object[] params = {password, id};
        dbUtil.executeUpdate(sql, params);
    }
}

