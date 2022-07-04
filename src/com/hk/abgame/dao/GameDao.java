package com.hk.abgame.dao;

import com.hk.abgame.bean.Game;
import com.hk.abgame.util.DBUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created on 2022-07-04 9:21
 * 操作game表的类
 * @author Xia Jiayi
 */
public class GameDao {
    DBUtil dbUtil = new DBUtil();
    /**
     * 新增游戏
     */
    public void insertGame(Game game) {
        String sql= "insert into game(pid,play_time,play_score) values(?,NOW(),?)";
        Object[] params = {game.getPid(), game.getPlay_score()};
        dbUtil.executeUpdate(sql, params);

    }

    /**
     * 玩家查询自己游戏记录
     */
    public List<Game> selectGame(int pid) {
        String sql = "select * from game where pid=?";
        Object[] params = {pid};
        return getGameList(dbUtil.query(sql, params));
    }


    /**
     * 管理员查询所有游戏记录
     */

    /**
     * 管理员分数统计
     *
     */

    /**
     * List<Map>---->List<Game>
     */
    public List<Game> getGameList(List<Map<String, String>> list) {
        List<Game> gameList = new ArrayList<>();
        for (Map<String, String> map : list) {
            Game game = new Game();
            game.setId(Integer.parseInt(map.get("id")));
            game.setPid(Integer.parseInt(map.get("pid")));
            game.setPlay_time(map.get("play_time"));
            game.setPlay_score(Integer.parseInt(map.get("play_score")));
            gameList.add(game);
        }
        return gameList;
    }
}
