package com.hk.abgame.util;

import com.hk.abgame.bean.Login;
import com.hk.abgame.game.Bird;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

import static com.hk.abgame.util.BirdFactory.createBird;

/**
 * Created on 2022-06-28 10:41
 *
 * @author Xia Jiayi
 */
public class XMLFReader {
    /**
     * 读取XML文件，并返回Login对象
     */
    public static Login getAdminLogin(Document document) {
        //获取根节点
        Element root = document.getRootElement();
        //获取admin-login节点
        String login_name = root.element("admin").elementText("login_name");
        //获取password节点
        String password = root.element("admin").elementText("password");
        //获取login_times节点
        String login_times = root.element("admin").elementText("login_times");
        //创建Login对象
        Login admin = new Login();
        admin.setLogin_name(login_name);
        admin.setPassword(password);
        admin.setLogin_times(Integer.parseInt(login_times));
        return admin;
    }

    /**
     * 读取小鸟信息
     */
    public static List<Bird> getBirdType(Document document) {
        //获取根节点
        Element root = document.getRootElement();
        //获取birds节点
        Element birds = root.element("birds");
        //获取bird节点
        List<Element> birdList = birds.elements("bird");
        //将List<Element>转换为List<Bird>
        List<Bird> list = new ArrayList<>();
        //遍历birdList
        for (Element e : birdList) {
            //获取id节点
            int id = Integer.parseInt(e.attributeValue("id"));
            //获取name节点
            String name = e.elementText("name");
            //获取attack节点
            int attack = Integer.parseInt(e.elementText("attack"));
            //获取hit节点
            int hit = Integer.parseInt(e.elementText("hit"));
            //获取color节点
            String color = e.elementText("color");
            //创建Bird对象
            Bird bird = createBird(color);
            bird.setId(id);
            bird.setName(name);
            bird.setAttackValue(attack);
            bird.setHitValue(hit);
            bird.setColor(color);
            //将Bird对象添加到List<Bird>中
            list.add(bird);

        }

        return list;
    }

}
