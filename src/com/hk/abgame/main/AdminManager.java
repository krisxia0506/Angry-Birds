package com.hk.abgame.main;

import com.hk.abgame.bean.Login;
import com.hk.abgame.ui.Menu;

/**
 * Created on 2022-06-27 11:32
 *
 * @author Xia Jiayi
 */
public class AdminManager {
    public boolean checkLogin(Login login) {
        if (DataInit.login.getLoginname().equals(login.getLoginname())
                && DataInit.login.getPassword().equals(login.getPassword())) {
            return true;
        }else {
            return false;
        }

    }
    /*
    * 管理员操作
    */
    public boolean adminOp() {



        boolean flag = false;
        for (int i = 1; i <= DataInit.login.getLogintimes(); i++) {
            Login login = Menu.getLoginUI();
            flag = checkLogin(login);
            if (flag) {
                adminOp2(Menu.getAdminUI());
                break;
            } else {
                System.out.println("登陆失败");
                if (DataInit.login.getLogintimes() - i>0)
                {
                    System.out.println("还有" + (DataInit.login.getLogintimes() - i) + "次机会");
                }else
                {
                    System.out.println("您的登陆次数已经用完");
                }
            }

        }
        return flag;
    }

    public void adminOp2(int c) {
        switch (c) {
            case 1://新增玩家
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
