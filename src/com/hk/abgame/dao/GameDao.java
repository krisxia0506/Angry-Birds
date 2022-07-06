package com.hk.abgame.dao;

import com.hk.abgame.bean.Game;
import com.hk.abgame.bean.Rank;
import com.hk.abgame.exception.SysException;
import com.hk.abgame.util.DBUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created on 2022-07-04 9:21
 * 操作game表的类
 *
 * @author Xia Jiayi
 */
public class GameDao {
    DBUtil dbUtil = new DBUtil();

    /**
     * 新增游戏
     */
    public void insertGame(Game game) throws SysException {
        String sql = "insert into game(pid,play_time,play_score) values(?,NOW(),?)";
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
    public List<Game> selectAllGame() {
        String sql = "select * from game";
        return getGameList(dbUtil.query(sql, null));
    }

    /**
     * 管理员分数统计
     */
    public List<Game> selectSumScore() {
        String sql = "select pid,sum(play_score) as play_score, count(pid) as times from game group by pid order by play_score desc";
        return getGameList(dbUtil.query(sql, null));
    }

    /**
     * 总分排行榜
     */
    public List<Rank> queryGameByTotalScore() {
        String sql = "select nickname,COUNT(*) as times,sum(play_score) as score " +
                "from rank group by pid order by sum(play_score) desc;";
        return getRankList(dbUtil.query(sql, null));
    }

    /**
     * 单次最高分
     */
    public List<Rank> queryGameBySingleScore() {
        String sql = "select nickname,max(play_score) as score,COUNT(*) as times  from rank group by pid order by max(play_score) desc;";
        return getRankList(dbUtil.query(sql, null));
    }

    /**
     * 平均分
     */
    public List<Rank> queryGameByAverageScore() {
        String sql = "select nickname,round(avg(play_score),0) as score,COUNT(*) as times  from rank group by pid order by avg(play_score) desc;";
        return getRankList(dbUtil.query(sql, null));
    }


    /**
     * List<Map>---->List<Game>
     */
    public List<Game> getGameList(List<Map<String, String>> list) {
        List<Game> gameList = new ArrayList<>();
        for (Map<String, String> map : list) {
            Game game = new Game();
            if (map.get("id") != null) {
                game.setId(Integer.parseInt(map.get("id")));
            }
            game.setPid(Integer.parseInt(map.get("pid")));
            game.setPlay_time(map.get("play_time"));
            game.setPlay_score(Integer.parseInt(map.get("play_score")));
            gameList.add(game);
        }
        return gameList;
    }

    /**
     * List<Map>---->List<Rank>
     */
    public List<Rank> getRankList(List<Map<String, String>> list) {
        List<Rank> rankList = new ArrayList<>();
        for (Map<String, String> map : list) {
            Rank rank = new Rank();
            rank.setNickname(map.get("nickname"));
            rank.setScore(Integer.parseInt(map.get("score")));
            rank.setGame_times(Integer.parseInt(map.get("times")));
            rankList.add(rank);
        }
        return rankList;

    }
}
