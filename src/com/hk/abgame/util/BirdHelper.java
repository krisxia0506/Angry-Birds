package com.hk.abgame.util;

import com.hk.abgame.exception.AppException;
import com.hk.abgame.game.Bird;

import java.util.ArrayList;
import java.util.List;

import static com.hk.abgame.main.DataInit.birdTypes;
import static com.hk.abgame.ui.Menu.getChooseBirdUi;

/**
 * Created on 2022-07-04 9:32
 * 获得小鸟数据的类
 *
 * @author Xia Jiayi
 */
public class BirdHelper {

    /**
     * 获得小鸟数据
     *
     * @return 小鸟数据
     */
    public static List<Bird> getBirds() {
        getChooseBirdUi();
        List<Bird> birds = new ArrayList<>(5);
        System.out.println("请选择5个小鸟");
        for (int i = 0; i < 5; i++) {
            int birdId = InputHelper.getInt();
            //用户输入0返回上级菜单
            if (birdId == 0) {
                return null;
            }
            Bird bird = null;
            try {
                bird = getBird(birdId);
            } catch (AppException e) {
                System.err.println( e.getErrorMsg());
                i--;
                continue;
            }
            birds.add(bird);
            if (4 - i > 0) {
                System.out.println("您选择了一只" + bird.getColor() + "的" + bird.getName() + "，还可以选择" + (4 - i) + "只小鸟，请选择下一只");
            }

        }
        return birds;
    }

    /**
     * 获得小鸟对象
     *
     * @param id 小鸟ID
     * @return 小鸟对象
     */
    public static Bird getBird(int id) throws AppException {
        for (Bird bird : birdTypes) {
            if (bird.getId() == id) {
                return bird;
            }
        }
        throw new AppException(1, "没有这只小鸟");
    }
}
