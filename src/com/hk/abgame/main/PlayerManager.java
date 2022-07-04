package com.hk.abgame.main;

import com.hk.abgame.bean.Game;
import com.hk.abgame.bean.Login;
import com.hk.abgame.bean.Player;
import com.hk.abgame.dao.GameDao;
import com.hk.abgame.dao.PlayerDao;
import com.hk.abgame.game.Bird;
import com.hk.abgame.game.PlayGame;
import com.hk.abgame.ui.Menu;
import com.hk.abgame.util.BirdHelper;
import com.hk.abgame.util.InputHelper;

import java.util.List;

import static com.hk.abgame.ui.Menu.getRankUi;

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

    /**
     * 玩家登陆
     */
    public void playOp(){
        //显示登录界面，获取控制台输入的用户名和密码
        Login login = Menu.getLoginUi();
        //check
        Player player = playerDao.checkPlayer(login);
        if (player == null) {
            System.out.println("登录失败，请重新登录");
            playOp();
        }
        playOp(player,Menu.getPlayerUi());
    }


    public void playOp(Player player,int c) {
        switch (c) {
            case 1:
                boolean flag = true;
                while (flag) {
                    //显示选择小鸟界面，获取控制台输入的小鸟类型
                    List<Bird> birds = BirdHelper.getBirds();
                    //返回上级
                    if (birds == null) {
                        playOp(player,Menu.getPlayerUi());
                        flag = false;
                    } else {
                        //开始游戏
                        playGame.setBirds(birds);
                        playGame.setPlayer(player);
                        playGame.play();
                        flag = false;
                        System.out.println("游戏结束，是否继续游戏？（y/n）");
                        flag = InputHelper.getString().equals("y");
                    }
                }
                playOp(player,Menu.getPlayerUi());
            case 2:
                //查看自己游戏记录
                List<Game> gameList = gameDao.selectGame(player.getId());
                if (gameList == null) {
                    System.out.println("暂无游戏记录");
                    playOp(player,Menu.getPlayerUi());
                } else {
                    System.out.println("查询成功");
                    System.out.println("游戏记录如下：");
                    System.out.println("游戏编号\t\t\t游戏时间\t\t\t游戏得分");
                    for (Game game : gameList) {
                        System.out.println("   "+game.toString());
                    }
                    System.out.println("按任意键继续");
                    String s = InputHelper.getString();
                    playOp(player,Menu.getPlayerUi());
                }
                break;
            case 3:
                //排行榜
                getRankUi();
                break;
            case 0:
                break;
            default:
                System.out.println("输入错误，请重新输入");
                break;
        }
    }

}
