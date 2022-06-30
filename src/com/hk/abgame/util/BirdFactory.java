package com.hk.abgame.util;

import com.hk.abgame.game.Bird;
import com.hk.abgame.game.BlackBird;
import com.hk.abgame.game.BlueBird;
import com.hk.abgame.game.RedBird;

/**
 * Created on 2022-06-30 9:54
 * 用于创建小鸟对象的工厂类
 *
 * @author Xia Jiayi
 */
public class BirdFactory {
    public static Bird createBird(String color) {
        Bird bird = null;
        if ("黑色".equals(color)) {
            bird = new BlackBird();
        } else if ("蓝色".equals(color)) {
            bird = new BlueBird();
        } else if ("红色".equals(color)) {
            bird = new RedBird();
        }
        return bird;
    }
}
