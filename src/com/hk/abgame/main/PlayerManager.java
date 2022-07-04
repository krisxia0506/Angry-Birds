package com.hk.abgame.main;

import com.hk.abgame.bean.Login;
import com.hk.abgame.bean.Player;
import com.hk.abgame.dao.PlayerDao;
import com.hk.abgame.game.Bird;
import com.hk.abgame.game.PlayGame;
import com.hk.abgame.ui.Menu;
import com.hk.abgame.util.BirdHelper;
import com.hk.abgame.util.InputHelper;

import java.util.List;

/**
 * Created on 2022-06-27 11:32
 * 玩家操作类
 * @author Xia Jiayi
 */
public class PlayerManager {
    PlayerDao playerDao = new PlayerDao();
    PlayGame playGame = new PlayGame();
    /**
     * 检查玩家登录
     */
    public Player checkLogin(Login login) {
        return playerDao.queryPlayerByLoginName(login);
    }


    public void playOp() {
        //显示登录界面，获取控制台输入的用户名和密码
        Login login = Menu.getLoginUi();
        //check
        Player player = checkLogin(login);
        if (player==null){
            System.out.println("登录失败，请重新登录");
            return;
        }
        int c = Menu.getPlayerUi();
        switch (c) {
            case 1:
                boolean flag = true;
                while (flag) {
                    //显示选择小鸟界面，获取控制台输入的小鸟类型
                    List<Bird> birds = BirdHelper.getBirds();

                    //返回上级
                    if (birds==null){

                        return;
                    }else {
                        //开始游戏
                        playGame.setBirds(birds);
                        playGame.setPlayer(player);
                        playGame.play();
                        flag = false;
                        System.out.println("游戏结束，是否继续游戏？（y/n）");
                        flag = InputHelper.getString().equals("y");
                    }
                }

                break;
            case 2:
                break;
            case 0:
                break;
            default:
                System.out.println("输入错误，请重新输入");
                break;
        }
    }

}
