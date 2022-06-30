package com.hk.abgame.main;

import com.hk.abgame.bean.Login;
import com.hk.abgame.bean.Player;
import com.hk.abgame.dao.PlayerDao;
import com.hk.abgame.game.Bird;
import com.hk.abgame.game.SysManger;
import com.hk.abgame.ui.Menu;
import com.hk.abgame.util.BirdFactory;
import com.hk.abgame.util.InputHelper;
import com.hk.abgame.util.XMLFReader;

import java.util.List;

import static com.hk.abgame.ui.Menu.getChooseBirdUi;
import static com.hk.abgame.ui.Menu.getSetBirdUi;

/**
 * Created on 2022-06-27 11:32
 *
 * @author Xia Jiayi
 */
public class AdminManager {
    PlayerDao playerDao = new PlayerDao();
    SysManger sysManger = new SysManger();

    /**
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

    /**
     * 管理员操作
     */
    public boolean adminOp() {
        boolean flag = false;
        for (int i = 1; i <= DataInit.login.getLogintimes(); i++) {
            Login login = Menu.getLoginUi();
            flag = checkLogin(login);
            if (flag) {
                System.out.println("登陆成功");
                adminOp2(Menu.getAdminUi());
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

    /**
     * 新增玩家
     */

    public void addPlayer(Player player) {
        //判断是否已存在该玩家
        if (playerDao.findPlayerByLoginname(player.getLoginname()) == null) {
            playerDao.addPlayer(player);
            System.out.println("新增玩家成功");
        } else {
            System.out.println("该玩家已存在");
        }
    }

    /**
     * 删除玩家
     */
    public void deletePlayer(Player player) {
        //判断是否存在该玩家
        if (playerDao.findPlayerByLoginname(player.getLoginname()) != null) {
            playerDao.deletePlayer(player);
            System.out.println("删除玩家成功");
        } else {
            System.out.println("该玩家不存在");
        }
    }

    /**
     * 修改玩家信息
     */
    public void updatePlayer() {
        System.out.println("请输入玩家id:");
        int id = InputHelper.getInt();

        //判断是否存在该玩家
        if (playerDao.findPlayerById(id) != null) {
            Player player = Menu.getPlayerDataUi();
            player.setId(id);
            playerDao.updatePlayer(player);
            System.out.println("修改玩家成功");
        } else {
            System.out.println("该玩家不存在");
            System.out.println("请重新输入id");
            updatePlayer();

        }
    }

    /**
     * 查询所有玩家信息
     */
    public void findPlayer() {
        List<Player> players = playerDao.findAllPlayer();
        if (players.size() == 0) {
            System.out.println("暂无玩家信息");
        } else {
            System.out.println("玩家id\t玩家用户名\t玩家密码\t玩家昵称\t玩家年龄\t玩家性别");
            for (Player player : players) {
                String sex;
                if (player.getSex() == 0) {
                    sex = "女";
                } else {
                    sex = "男";
                }
                System.out.println(player.getId() + "\t\t" + player.getLoginname() + "\t\t" + player.getPassword() + "\t\t" + player.getNickname() + "\t\t" + player.getAge() + "\t\t" + sex);
            }
        }
    }

    /**
     * 查询玩家信息
     */
    public void findPlayerById() {
        System.out.println("请输入玩家id:");
        int id = InputHelper.getInt();
        List<Player> player1 = playerDao.findPlayerById(id);
        if (player1 != null) {
            System.out.println(player1);
        } else {
            System.out.println("该玩家不存在");
        }
    }

    /**
     * 参数设置
     */
    public void setSystem() {
        switch (Menu.getSetSystemUi()) {
            case 1:
                //修改小鸟参数
                //得到小鸟id
                int birdid = getChooseBirdUi();
                if (birdid == 0) {
                    setSystem();
                }
                Bird bird = BirdFactory.createBird(sysManger.birdColor(birdid));
                if (bird != null) {
                    //得到小鸟参数
                    //修改小鸟参数
                    System.out.println("修改攻击值为:");
                    bird.setAttackValue(InputHelper.getInt());
                    System.out.println("修改命中率为:");
                    bird.setHitValue(InputHelper.getInt());
                    bird.setId(birdid);
                    //修改小鸟参数
                    boolean b = sysManger.setBird(bird);
                    if (b) {

                        System.out.println("修改成功");
                        DataInit.birdTypes = XMLFReader.getBirdType(DataInit.document);
                    } else {
                        System.out.println("修改失败");
                    }
                }
                else {
                    System.out.println("修改小鸟参数失败");
                }

                break;

            case 2:
                //修改管理员登录名
                System.out.println("请输入修改后的管理员登录名");
                String loginname = InputHelper.getString();
                boolean b = sysManger.setLoginname(loginname);
                if (b) {
                    System.out.println("修改成功,请重新登录");
                    DataInit.login = XMLFReader.getAdminLogin(DataInit.document);
                } else {
                    System.out.println("修改失败");
                }
                break;
            case 3:
                setSystem();
                break;
            case 4:
                break;
            case 0:
                adminOp2(Menu.getAdminUi());
                break;

            default:
                System.out.println("输入错误");
                setSystem();
                break;
        }
    }

    /**
     * 管理员菜单
     */
    public void adminOp2(int c) {
        switch (c) {
            //新增玩家
            case 1:
                boolean isRenew = true;
                while (isRenew) {
                    Player player = Menu.getPlayerDataUi();
                    addPlayer(player);
                    isRenew = false;
                    System.out.println("是否继续新增玩家？(y/n)");
                    String s = InputHelper.getString();
                    if ("Y".equals(s.toUpperCase())) {
                        isRenew = true;
                    }
                }
                adminOp2(Menu.getAdminUi());
                break;
            case 2:
                updatePlayer();
                adminOp2(Menu.getAdminUi());
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
                    if ("Y".equals(s.toUpperCase())) {
                        isRenew = true;
                    }
                }
                adminOp2(Menu.getAdminUi());
                break;
            case 4:
                isRenew = true;
                while (isRenew) {
                    findPlayer();
                    isRenew = false;
                    System.out.println("按任意键继续");
                    String s = InputHelper.getString();

                    adminOp2(Menu.getAdminUi());

                }
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                //参数修改
                setSystem();
                break;
            case 0:

                break;
            default:
                System.out.println("输入错误，请重新输入");
                break;
        }
    }
}
