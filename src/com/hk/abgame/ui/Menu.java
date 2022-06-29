package com.hk.abgame.ui;


import com.hk.abgame.bean.Login;
import com.hk.abgame.bean.Player;
import com.hk.abgame.util.InputHelper;

import java.util.Scanner;

/**
 * Created on 2022-06-27 11:32
 *
 * @author Xia Jiaer
 */
public class Menu {
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
        return InputHelper.getInt();
    }
    /**
     * 登陆UI
     */
    public static Login getLoginUi(){
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
     */
    public static Player getPlayerDataUi(){
        Player player = new Player();
        System.out.println("*******************************************************");
        System.out.println("请输入玩家名:");
        String playername = InputHelper.getString();
        player.setLoginname(playername);
        System.out.println("请输入玩家密码:");
        String password = InputHelper.getString();
        player.setPassword(password);
        System.out.println("请输入玩家昵称:");
        String nickname = InputHelper.getString();
        player.setNickname(nickname);
        System.out.println("请输入玩家年龄:");
        String age = InputHelper.getString();
        for (int i =age.length(); --i>=0;) {
            while (!Character.isDigit(age.charAt(i))) {
                System.out.println("年龄只能是数字，请重新输入！");
                age = InputHelper.getString();
            }

        }
        player.setAge(Integer.valueOf(age));

        System.out.println("请输入玩家性别（男或女）:");
        String s = InputHelper.getString();
        String nan = "男";
        String nv = "女";
        while (!nan.equals(s) && !nv.equals(s)) {
            System.out.println("输入错误，请重新输入");
            s = InputHelper.getString();
        }
        if (nan.equals(s)){
            player.setSex(1);
        }else {
            player.setSex(0);
        }
        return player;

    }



}
