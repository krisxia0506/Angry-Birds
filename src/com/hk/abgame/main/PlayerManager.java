package com.hk.abgame.main;

import com.hk.abgame.bean.Game;
import com.hk.abgame.bean.Login;
import com.hk.abgame.bean.Player;
import com.hk.abgame.bean.Rank;
import com.hk.abgame.dao.GameDao;
import com.hk.abgame.dao.PlayerDao;
import com.hk.abgame.exception.SysException;
import com.hk.abgame.game.Bird;
import com.hk.abgame.game.PlayGame;
import com.hk.abgame.ui.Menu;
import com.hk.abgame.util.BirdHelper;
import com.hk.abgame.util.DateUtil;
import com.hk.abgame.util.InputHelper;

import java.util.Date;
import java.util.List;

import static com.hk.abgame.ui.Menu.getRankUi;
import static com.hk.abgame.util.InputHelper.pressAnyKey;
import static com.hk.abgame.util.ValidationUtil.CHKPASSIORD;

/**
 * Created on 2022-06-27 11:32
 * 玩家操作类
 *
 * @author Xia Jiayi
 */
public class PlayerManager {
    PlayerDao playerDao = new PlayerDao();
    PlayGame playGame = new PlayGame();
    GameDao gameDao = new GameDao();
    Player player;

    /**
     * 玩家登陆
     */
    public void playOp() {
        //显示登录界面，获取控制台输入的用户名和密码
        Login login = Menu.getLoginUi();
        //check
        player = playerDao.checkPlayer(login);
        if (player == null) {
            System.err.println("登录失败，请重新登录");
            playOp();
        }
        playOp(player, Menu.getPlayerUi());
    }

    /**
     * 排行榜
     */
    public void rankOp() {
        switch (getRankUi()) {
            case 1:
                List<Rank> list = gameDao.queryGameByTotalScore();
                if (list.size() == 0) {
                    System.out.println("暂无游戏记录");
                } else {
                    System.out.println("排行榜");
                    System.out.println("排名\t\t玩家昵称\t\t游戏次数\t\t得分");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(String.format("%-6s", (i + 1)) + "  " + String.format("%-13s", list.get(i).getNickname()) + "  " + String.format("%-8s", list.get(i).getGame_times()) + " " + list.get(i).getScore());
                    }
                }
                pressAnyKey();
                rankOp();
                break;
            case 2:
                list = gameDao.queryGameBySingleScore();
                if (list.size() == 0) {
                    System.out.println("暂无游戏记录");
                } else {
                    System.out.println("排行榜");
                    System.out.println("排名\t\t玩家昵称\t\t游戏次数\t\t单次最高得分");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(String.format("%-6s", (i + 1)) + "  " + String.format("%-13s", list.get(i).getNickname()) + "  " + String.format("%-12s", list.get(i).getGame_times()) + " " + list.get(i).getScore());
                    }
                }
                pressAnyKey();
                rankOp();
                break;
            case 3:
                list = gameDao.queryGameByAverageScore();
                if (list.size() == 0) {
                    System.out.println("暂无游戏记录");
                } else {
                    System.out.println("排行榜");
                    System.out.println("排名\t\t玩家昵称\t\t游戏次数\t\t平均得分");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(String.format("%-6s", (i + 1)) + "  " + String.format("%-13s", list.get(i).getNickname()) + "  " + String.format("%-10s", list.get(i).getGame_times()) + " " + list.get(i).getScore());
                    }
                }
                pressAnyKey();
                rankOp();
                break;
            case 0:
                playOp(player, Menu.getPlayerUi());
                return;
            default:
                System.err.println("输入错误，请重新输入");
                break;
        }

    }

    /**
     * 修改密码
     */
    public void changePasswordOp(Player player) throws SysException {
        System.out.println("请输入新密码，密码应包含数字和字母，长度不小于六位:");
        String password = InputHelper.getString(CHKPASSIORD, "应包含数字和字母，长度不小于六位");
        playerDao.updatePassword(player.getId(), password);
        System.out.println("修改成功,请重新登录");
        AbGame.main(null);

    }

    /**
     * @param player 玩家对象
     * @param c 玩家界面选择
     */
    public void playOp(Player player, int c) {
        switch (c) {
            case 1:
                boolean flag = true;
                while (flag) {
                    //显示选择小鸟界面，获取控制台输入的小鸟类型
                    List<Bird> birds = BirdHelper.getBirds();
                    //返回上级
                    if (birds == null) {
                        playOp(player, Menu.getPlayerUi());
                        flag = false;
                    } else {
                        //开始游戏
                        playGame.setBirds(birds);
                        playGame.setPlayer(player);
                        try {
                            playGame.play();
                        } catch (SysException e) {
                            System.err.println(e.getErrorCode()+":"+e.getErrorMsg());
                        }
                        System.out.println("游戏结束，是否继续游戏？（y/n）");
                        flag = InputHelper.getString().equals("y");
                    }
                }
                playOp(player, Menu.getPlayerUi());
            case 2:
                //查看自己游戏记录
                List<Game> gameList = gameDao.selectGame(player.getId());
                if (gameList == null) {
                    System.out.println("暂无游戏记录");
                    playOp(player, Menu.getPlayerUi());
                } else {
                    System.out.println("查询成功");
                    System.out.println("游戏记录如下：");
                    System.out.println("游戏编号\t\t\t游戏时间\t\t\t游戏得分");
                    for (Game game : gameList) {
                        Date date = DateUtil.stringToDate(game.getPlay_time(), "yyyy-MM-dd HH:mm:ss");
                        String playTime = DateUtil.getDateTime(date);
                        System.out.println(String.format("%-9s", game.getId()) + String.format("%-25s", playTime) + game.getPlay_score());
                    }
                    pressAnyKey();
                    playOp(player, Menu.getPlayerUi());
                }
                break;
            case 3:
                //排行榜
                rankOp();
                break;
            case 4:
                //修改密码
                try {
                    changePasswordOp(player);
                } catch (SysException e) {
                    System.err.println(e.getErrorCode() + ":" + e.getErrorMsg());
                }
                break;
            case 0:
                break;
            default:
                System.out.println("输入错误，请重新输入");
                break;
        }
    }

}
