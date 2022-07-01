package com.hk.abgame.util;

import com.hk.abgame.game.*;

/**
 * Created on 2022-06-30 9:54
 * 用于创建小鸟对象的工厂类
 * @author Xia Jiayi
 */
public class BirdFactory {
    /**
     * 根据颜色，创建小鸟对象
     * @param color 小鸟颜色
     * @return 小鸟对象
     */
    public static Bird createBird(String color) {
        Bird bird = null;
        if ("黑色".equals(color)) {
            bird = new BlackBird();
        } else if ("蓝色".equals(color)) {
            bird = new BlueBird();
        } else if ("红色".equals(color)) {
            bird = new RedBird();
        } else if ("黄色".equals(color)) {
            bird = new YellowBird();
        } else if ("白色".equals(color)) {
            bird = new WhiteBird();
        } else if ("绿色".equals(color)) {
            bird = new GreenBird();
        }
        return bird;
    }
}
