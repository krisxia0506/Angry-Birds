package com.hk.abgame.util;

import com.hk.abgame.game.*;

/**
 * Created on 2022-06-30 9:54
 * 用于创建小鸟对象的工厂类
 *
 * @author Xia Jiayi
 */
public class BirdFactory {
    /**
     * 根据颜色，创建小鸟对象
     *
     * @param color 小鸟颜色
     * @return 小鸟对象
     */
    public static Bird createBird(String color) {
        Bird bird = null;
        switch (color) {
            case "黑色":
                bird = new BlackBird();
                break;
            case "白色":
                bird = new WhiteBird();
                break;
            case "红色":
                bird = new RedBird();
                break;
            case "蓝色":
                bird = new BlueBird();
                break;
            case "绿色":
                bird = new GreenBird();
                break;
            case "黄色":
                bird = new YellowBird();
                break;
            default:
                break;
        }
        return bird;
    }


}
