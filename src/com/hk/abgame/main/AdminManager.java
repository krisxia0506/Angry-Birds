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

/**
 * Created on 2022-06-27 11:32
 * 管理员管理类
 * @author Xia Jiayi
 */
public class AdminManager {
    PlayerDao playerDao = new PlayerDao();
    SysManger sysManger = new SysManger();

    /**
     * 验证用户名和密码
     * @param login 登录信息
     * @return 是否验证成功
     */
    public boolean checkLogin(Login login) {
        return DataInit.login.getLogin_name().equals(login.getLogin_name())
                && DataInit.login.getPassword().equals(login.getPassword());

    }

    /**
     * 管理员登陆
     */
    public void adminOp() {
        boolean flag;
        for (int i = 1; i <= DataInit.login.getLogin_times(); i++) {
            Login login = Menu.getLoginUi();
            flag = checkLogin(login);
            if (flag) {
                System.out.println("登陆成功");
                adminOp2(Menu.getAdminUi());
                break;
            } else {
                System.err.println("登陆失败");
                if (DataInit.login.getLogin_times() - i > 0) {
                    System.out.println("还有" + (DataInit.login.getLogin_times() - i) + "次机会");
                } else {
                    System.err.println("您的登陆次数已经用完");
                }
            }
        }
    }


    /**
     * 删除玩家
     * 如果玩家名字不存在，则不能删除
     */
    public void deletePlayer() {
        Player player = new Player();
        System.out.println("***********************所有玩家**************************");
        findPlayer();
        System.out.println("*******************************************************");
        System.out.println("请输入要删除玩家ID:");
        int id = InputHelper.getInt();
        player.setId(id);
        //判断是否存在该玩家
        while (playerDao.findPlayerById(player.getId()) == null) {
            System.err.println("该玩家不存在,请重新输入");
            System.out.println("***********************所有玩家**************************");
            findPlayer();
            System.out.println("*******************************************************");
            player.setId(InputHelper.getInt());
        }
        playerDao.deletePlayer(player);
        System.out.println("删除玩家成功");
        System.out.println("***********************所有玩家**************************");
        findPlayer();
        System.out.println("*******************************************************");
    }

    /**
     * 修改玩家信息
     * 如果玩家名字不存在，则不能修改
     */
    public void updatePlayer() {
        System.out.println("************************所有玩家************************");
        findPlayer();
        System.out.println("*******************************************************");
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
            System.out.println(String.format("%-15s","[玩家ID]")+String.format("%-15s","[玩家用户名]")+String.format("%-15s","[玩家密码]")+String.format("%-15s","[玩家昵称]")+String.format("%-15s","[玩家年龄]")+String.format("%-15s","[玩家性别]")+String.format("%-15s","[玩家注册时间]"));
            for (Player player : players) {
                String sex;
                if (player.getSex() == 0) {
                    sex = "女";
                } else {
                    sex = "男";
                }
                System.out.println(String.format("%-15s","["+ player.getId() +"]") + String.format("%-15s"," ["+ player.getLogin_name() +"]") + String.format("%-15s","  \t["+ player.getPassword() +"]") + String.format("%-15s","   \t["+ player.getNickname() +"]") + String.format("%-15s","\t\t\t["+ player.getAge() +"]") + "\t" + String.format("%-15s","["+ sex +"]") + String.format("%-15s","["+ player.getCreat_time() +"]"));
                //System.out.println(player.getId() + "\t\t" + player.getLogin_name() + "\t\t" + player.getPassword() + "\t\t" + player.getNickname() + "\t\t" + player.getAge() + "\t\t" + sex+"\t\t" + player.getCreat_time());
            }
        }
    }

    /**
     * 查询玩家信息
     */
    public void findPlayerById() {
        System.out.println("请输入玩家id:");
        int id = InputHelper.getInt();
        Player player1 = playerDao.findPlayerById(id);
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
                int birdId = getChooseBirdUi();
                if (birdId == 0) {
                    setSystem();
                }

                Bird bird = BirdFactory.createBird(sysManger.birdColor(birdId));
                if (bird != null) {
                    //得到小鸟参数
                    //修改小鸟参数
                    System.out.println("修改攻击值为:");
                    bird.setAttackValue(InputHelper.getInt());
                    System.out.println("修改命中率为:");
                    bird.setHitValue(InputHelper.getInt());
                    System.out.println("修改颜色为：");
                    bird.setColor(InputHelper.getString());
                    bird.setId(birdId);
                    //修改小鸟参数
                    boolean b = sysManger.setBird(bird);
                    if (b)
                    {
                        System.out.println("修改成功");
                        DataInit.birdTypes = XMLFReader.getBirdType(DataInit.document);
                        setSystem();
                    } else {
                        System.err.println("修改失败");
                    }
                } else {
                    System.err.println("修改小鸟参数失败");
                }

                break;

            case 2:
                //修改管理员登录名
                System.out.println("请输入修改后的管理员登录名");
                String login_name = InputHelper.getString();
                boolean b = sysManger.setLoginName(login_name);
                if (b) {
                    System.out.println("修改成功,请重新登录");
                    DataInit.login = XMLFReader.getAdminLogin(DataInit.document);
                } else {
                    System.out.println("修改失败");
                }
                break;
            case 3:
                //修改管理员密码
                System.out.println("请输入修改后的管理员密码");
                String password = InputHelper.getString();
                b = sysManger.setPassword(password);
                if (b) {
                    System.out.println("修改成功,请重新登录");
                    DataInit.login = XMLFReader.getAdminLogin(DataInit.document);
                } else {
                    System.out.println("修改失败");
                }
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
                    playerDao.addPlayer(player);
                    System.out.println("新增玩家成功");
                    isRenew = false;
                    System.out.println("是否继续新增玩家？(y/n)");
                    String s = InputHelper.getString();
                    if ("Y".equalsIgnoreCase(s)) {
                        isRenew = true;
                    }
                }
                adminOp2(Menu.getAdminUi());
                break;
            case 2:
                isRenew = true;
                while (isRenew) {
                    updatePlayer();
                    isRenew = false;
                    System.out.println("是否继续修改其它玩家？(y/n)");
                    String s = InputHelper.getString();
                    if ("Y".equalsIgnoreCase(s)) {
                        isRenew = true;
                    }
                }
                adminOp2(Menu.getAdminUi());
                break;
            case 3:
                isRenew = true;
                while (isRenew) {
                    deletePlayer();
                    isRenew = false;
                    System.out.println("是否继续删除玩家？(y/n)");
                    String s = InputHelper.getString();
                    if ("Y".equalsIgnoreCase(s)) {
                        isRenew = true;
                    }
                }
                adminOp2(Menu.getAdminUi());
                break;
            case 4:
                isRenew = true;
                while (isRenew) {
                    System.out.println("***********************所有玩家**************************");
                    findPlayer();
                    System.out.println("*******************************************************");
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
