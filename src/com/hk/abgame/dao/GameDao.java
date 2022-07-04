package com.hk.abgame.dao;

import com.hk.abgame.bean.Game;
import com.hk.abgame.util.DBUtil;

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


    /**
     * 管理员查询所有游戏记录
     */

    /**
     * 管理员分数统计
     */

    /**
     * List<Map>---->List<Game>
     */
}
