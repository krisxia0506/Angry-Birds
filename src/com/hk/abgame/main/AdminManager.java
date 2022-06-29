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

    //删除玩家
    public void deletePlayer(Player player) {
        //判断是否存在该玩家
        if (playerDao.findPlayerByLoginname(player.getLoginname()) != null) {
            playerDao.deletePlayer(player);
            System.out.println("删除玩家成功");
        } else {
            System.out.println("该玩家不存在");
        }
    }
    //修改玩家信息
    public void updatePlayer() {
        System.out.println("请输入玩家id:");
        int id = InputHelper.getInt();

        //判断是否存在该玩家
        if (playerDao.findPlayerById(id)!=null) {
            Player player = Menu.getPlayerDataUI();
            player.setId(id);
            playerDao.updatePlayer(player);
            System.out.println("修改玩家成功");
        } else {
            System.out.println("该玩家不存在");
            System.out.println("请重新输入id");
            updatePlayer();

        }
    }
//查询玩家信息
    public void findPlayer() {
        System.out.println("请输入玩家id:");
        int id = InputHelper.getInt();
        //判断是否存在该玩家
        if (playerDao.findPlayerById(id)!=null) {
            Player player = playerDao.findPlayerById(id);
            System.out.println(player);
        } else {
            System.out.println("该玩家不存在");
            findPlayer();
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
                adminOp2(Menu.getAdminUI());
                break;
            case 2:
                updatePlayer();
                adminOp2(Menu.getAdminUI());
                break;
            case 3:
                isRenew = true;
                while (isRenew) {
                    Player player = new Player();
                    System.out.println("*******************************************************");
                    System.out.println("请输入玩家名:");
                    String playername = InputHelper.getString();
                    player.setLoginname(playername);
                    deletePlayer(player);
                    isRenew = false;
                    System.out.println("是否继续删除玩家？(y/n)");
                    String s = InputHelper.getString();
                    if (s.toUpperCase().equals("Y")) {
                        isRenew = true;
                    }
                }
                adminOp2(Menu.getAdminUI());
                break;
            case 4:
                isRenew = true;
                while (isRenew){
                    findPlayer();
                    isRenew = false;
                    System.out.println("是否继续查询其它玩家？(y/n)");
                    String s = InputHelper.getString();
                    if (s.toUpperCase().equals("Y")) {
                        isRenew = true;
                    }else {
                        adminOp2(Menu.getAdminUI());
                    }
                }
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
