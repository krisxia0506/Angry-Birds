package com.hk.abgame.game;

/**
 * Created on 2022-07-01 14:18
 *
 * @author Xia Jiayi
 */
public class GreenBird extends Bird{
    @Override
    public void display() {
        System.out.println("我是" + this.color +"的" +  this.name + "我的攻击力是" + attackValue + ",我的命中率是" + hitValue);
    }

    @Override
    public void attack() {
        System.out.println("我是" + this.color +"的" + this.name + "，开始攻击");
    }
}
