package com.hk.abgame.main;

import com.hk.abgame.ui.Menu;

/**
 * Created on 2022-06-27 11:32
 * 主类
 * @author Xia Jiayi
 */
public class AbGame {
    PlayerManager playerManager = new PlayerManager();
    AdminManager adminManager = new AdminManager();

    public static void main(String[] args) {
        AbGame abGame = new AbGame();
        abGame.start();
    }

    public void start() {
        boolean flag = true;
        while (flag) {
            int c = Menu.getHainUi();
            switch (c) {
                case 1:
                    playerManager.playOp();
                    break;
                case 2:
                    adminManager.adminOp();

                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    System.out.println("输入错误，请重新输入");
                    break;
            }
        }
    }
}
