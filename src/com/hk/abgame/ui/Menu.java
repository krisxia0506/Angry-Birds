package com.hk.abgame.ui;


import com.hk.abgame.bean.Login;
import com.hk.abgame.bean.Player;
import com.hk.abgame.dao.PlayerDao;
import com.hk.abgame.game.Bird;
import com.hk.abgame.main.DataInit;
import com.hk.abgame.util.InputHelper;

import java.util.Scanner;

import static com.hk.abgame.util.ValidationUtil.*;

/**
 * Created on 2022-06-27 11:32
 * 很多菜单
 * @author Xia Jiaer
 */
public class Menu {
    static PlayerDao playerDao = new PlayerDao();
    /**
     * 开始菜单
     */
    public static int getHainUi() {
        System.out.println("*****************************");
        System.out.println();
        System.out.println("\t愤怒的小鸟1");
        System.out.println();
        System.out.println("\t选择登录方式:");
        System.out.println();
        System.out.println("\t1.玩家登陆︰2.管理员登陆,0.退出");
        System.out.println();
        System.out.println("\t请输入:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    /**
     * 玩家UI
     */
    public static int getPlayerUi() {
        System.out.println("**********************************");
        System.out.println("\t愤怒的小鸟");
        System.out.println();
        System.out.println("\t1.开始游戏");
        System.out.println();
        System.out.println("\t2.查看排行榜");
        System.out.println();
        System.out.println("\t0.返回上级菜单");
        System.out.println();
        System.out.println("*****************************************************");
        return InputHelper.getInt();
    }

    /**
     * 管理员UI
     */
    public static int getAdminUi() {
        System.out.println("****************************************************");
        System.out.println();
        System.out.println("\t系统管理");
        System.out.println();
        System.out.println("\t 1.新增玩家\t 5.查询游戏");
        System.out.println();
        System.out.println("\t 2.修改玩家\t 6.分数统计");
        System.out.println();
        System.out.println("\t 3.删除玩家\t 7.参数设置");
        System.out.println();
        System.out.println("\t 4.查看玩家\t 0.返回上级菜单");
        System.out.println("*******************************************************");

        while (true) {
            int i = InputHelper.getInt();
            if (i >= 0 && i <= 7) {
                return i;
            }
        }
    }

    /**
     * 登陆UI
     * @return 登陆信息
     */
    public static Login getLoginUi() {
        Login login = new Login();
        System.out.println("*******************************************************");
        System.out.println("请输入用户名:");
        String loginname = InputHelper.getString();
        login.setLoginname(loginname);
        System.out.println("请输入密码:");
        String password = InputHelper.getString();
        login.setPassword(password);
        return login;
    }

    /**
     * 新增玩家，修改玩家界面
     * @return 玩家信息
     */
    public static Player getPlayerDataUi() {
        Player player = new Player();
        System.out.println("*******************************************************");
        //玩家名
        System.out.println("请输入玩家名:");
        String playername = InputHelper.getString(CHKLOGINNANE,"不能全部由数字组成，必须要有字母");
        while (playerDao.findPlayerByLoginname(playername) != null) {
            System.out.println("该玩家已存在,请重新输入");
            playername = InputHelper.getString();
        }
        player.setLogin_name(playername);
        //密码
        System.out.println("请输入玩家密码，密码应包含数字和字母，长度不小于六位:");
        //方法重载
        String password = InputHelper.getString(CHKPASSIORD,"应包含数字和字母，长度不小于六位");
        player.setPassword(password);
        System.out.println("请输入玩家昵称:");
        String nickname = InputHelper.getString();
        player.setNickname(nickname);
        //年龄
        System.out.println("请输入玩家年龄:");
        int age = Integer.parseInt(InputHelper.getString(CHKAGE,"年龄应在0-120之间"));
        player.setAge(age);
        //性别
        System.out.println("请输入玩家性别（男或女）:");
        String s = InputHelper.getString(CHKSEX,"请输入男或女");
        String nan = "男";
        //存成1和0，方便查询
        player.setSex(nan.equals(s) ? 1 : 0);
        return player;
    }

    /**
     * 修改参数界面
     */
    public static int getSetSystemUi() {
        System.out.println("*******************************************************");
        System.out.println("1.修改小鸟参数");
        System.out.println("2.管理员的登录名");
        System.out.println("3.管理员的密码");
        System.out.println("4.管理员的最大登录次数");
        System.out.println("0.返回上级;");
        System.out.println("*******************************************************");
        System.out.println("请选择");
        return InputHelper.getInt();
    }

    /**
     * 选择小鸟界面
     */
    public static int getChooseBirdUi() {
        System.out.println("*******************************************************");
        for (Bird bird : DataInit.birdTypes) {
            System.out.println(bird.getId() + "." + bird.getColor() + bird.getName() + "攻击值" + bird.getAttackValue() + "命中率" + bird.getHitValue());
        }
        System.out.println("0.返回上级;");
        System.out.println("*******************************************************");
        System.out.println("请选择小鸟");
        return InputHelper.getInt();
    }
}
