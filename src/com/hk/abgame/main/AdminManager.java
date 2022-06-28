package com.hk.abgame.main;

import com.hk.abgame.bean.Login;
import com.hk.abgame.bean.Player;
import com.hk.abgame.dao.PlayerDao;
import com.hk.abgame.ui.Menu;
import com.hk.abgame.util.InputHelper;

/**
 * Created on 2022-06-27 11:32
 *
 * @author Xia Jiayi
 */
public class AdminManager {
    PlayerDao playerDao = new PlayerDao();

    /*
     * 验证用户名和密码
     */
    public boolean checkLogin(Login login) {
        if (DataInit.login.getLoginname().equals(login.getLoginname())
                && DataInit.login.getPassword().equals(login.getPassword())) {
            return true;
        } else {
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
                if (DataInit.login.getLogintimes() - i > 0) {
                    System.out.println("还有" + (DataInit.login.getLogintimes() - i) + "次机会");
                } else {
                    System.out.println("您的登陆次数已经用完");
                }
            }

        }
        return flag;
    }

    //新增玩家
    public void addPlayer(Player player) {
        //判断是否已存在该玩家
        if (playerDao.findPlayerByLoginname(player.getLoginname()) == null) {
            playerDao.addPlayer(player);
            System.out.println("新增玩家成功");
        } else {
            System.out.println("该玩家已存在");
        }
    }

    /*管理员菜单*/
    public void adminOp2(int c) {
        switch (c) {
            case 1://新增玩家
                boolean isRenew = true;
                while (isRenew) {
                    Player player = Menu.getPlayerDataUI();
                    addPlayer(player);
                    isRenew = false;
                    System.out.println("是否继续新增玩家？(y/n)");
                    String s = InputHelper.getString();
                    if (s.toUpperCase().equals("Y")) {
                        isRenew = true;
                    }
                }

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
