package com.hk.abgame.game;

import com.hk.abgame.bean.Game;
import com.hk.abgame.bean.Player;
import com.hk.abgame.dao.GameDao;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2022-07-04 9:55
 * 游戏类
 * @author Xia Jiayi
 */
public class PlayGame {
    //GameDao
    GameDao gameDao = new GameDao();

    //创建储存五只小鸟的集合
    @Getter
    @Setter
    private List<Bird> birds = new ArrayList<>();

    //创建玩家对象
    @Getter
    @Setter
    private Player player = null;

    /**
     * 游戏过程
     */
    public void play() {
        System.out.println("模拟游戏画面");
        int score = 0;
        for (int i =0;i<birds.size();i++){
            System.out.println("**********************************************************");
            Bird b = birds.get(i);
            if (b!=null){
                System.out.println("第"+(i+1)+"只小鸟："+b.getColor()+"的"+b.getName());
                System.out.println(b.getColor()+"的"+b.getName()+"发起攻击");
                //累加分数
                score += attack(b);
            }
        }
        //打印分数
        System.out.println(player.getLogin_name()+"游戏结束，您的得分是："+score);
        //保存分数
        Game game = new Game();
        game.setPid(player.getId());
        game.setPlay_score(score);
        gameDao.insertGame(game);
    }

    /**
     * 小鸟攻击分数算法
     * @param bird 小鸟对象
     * @return 攻击分数
     */
    public int attack(Bird bird) {
        bird.display();
        bird.attack();
        int score = 0;
        //判断是否攻击成功
        if (this.isAttackSuccess(bird)) {
            System.out.println(bird.getName()+"攻击成功");
            score = bird.getAttackValue();
        } else {
            System.out.println("攻击失败");
        }
        return score;
    }

    /**
     * 命中率算法
     * @param bird 小鸟对象
     * @return 命中率
     */
    public boolean isAttackSuccess(Bird bird) {
        int random = (int) (Math.random() * 100);
        return random < bird.hitValue;
    }
}
