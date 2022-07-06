package com.hk.abgame.game;

import lombok.Data;

/**
 * Created on 2022-06-30 9:30
 * 小鸟类
 * @author Xia Jiayi
 */
@Data
public abstract class Bird {
    protected int id;
    protected String name;
    protected int attackValue;
    protected int hitValue;
    protected String color;

    /**
     * 鸟的样子
     */
    public abstract void display();

    /**
     * 鸟的攻击力
     */
    public abstract void attack();

}
