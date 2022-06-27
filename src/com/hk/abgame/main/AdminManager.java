package com.hk.abgame.main;

import com.hk.abgame.ui.Menu;

/**
 * Created on 2022-06-27 11:32
 *
 * @author Xia Jiayi
 */
public class AdminManager {
    public void adminOp() {
        Menu.getLoginUI();
        int c = Menu.getidminuI();
        switch (c) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 0:
                break;
            default:
                System.out.println("输入错误，请重新输入");
                break;
        }
    }
}
