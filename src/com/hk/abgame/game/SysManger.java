package com.hk.abgame.game;

import com.hk.abgame.main.DataInit;
import com.hk.abgame.util.XMLFWriter;
import org.dom4j.Element;

import java.util.List;

/**
 * Created on 2022-06-30 11:10
 *
 * @author Xia Jiayi
 */
public class SysManger {
    /**
     * 修改管理员登录名
     * @param login_name 登录名
     * @return 是否修改成功
     */
    public boolean setLoginName(String login_name) {
        //获取根节点
        Element root = DataInit.document.getRootElement();
        //获取admin节点
        Element e = root.element("admin");
        //获取login_name节点并对其内容进行修改
        e.element("login_name").setText(login_name);
        return XMLFWriter.writeData(DataInit.document);
    }
    /**
     * 修改管理员密码
     * @param password 密码
     * @return 是否修改成功
     */
    public boolean setPassword(String password) {
        //获取根节点
        Element root = DataInit.document.getRootElement();
        //获取admin节点
        Element e = root.element("admin");
        //获取password节点并对其内容进行修改
        e.element("password").setText(password);
        return XMLFWriter.writeData(DataInit.document);
    }

    /**
     * 修改小鸟参数
     * @param bird 小鸟
     * @return 是否修改成功
     */
    public boolean setBird(Bird bird) {

        //获取根节点
        Element root = DataInit.document.getRootElement();
        //获取birds节点
        Element eb = root.element("birds");
        //获取bird节点
        List<Element> list = eb.elements("bird");
        for (Element e : list) {
            //传入的id值如果与xml中的id值一致，则进行修改
            if (e.attributeValue("id").equals(String.valueOf(bird.getId()))) {
                //设置攻击值
                e.element("attack").setText(bird.getAttackValue() + "");
                //设置命中率
                e.element("hit").setText(bird.getHitValue() + "");
                break;
            }
        }
        return XMLFWriter.writeData(DataInit.document);
    }

    /**
     * 根据小鸟id取得color
     * @param id 小鸟id
     * @return color 小鸟颜色
     */
    public String birdColor(int id) {
        //获取根节点
        Element root = DataInit.document.getRootElement();
        //获取birds节点
        Element eb = root.element("birds");
        //获取bird节点
        List<Element> list = eb.elements("bird");
        for (Element e : list) {
            //传入的id值如果与xml中的id值一致，则进行修改
            if (e.attributeValue("id").equals(String.valueOf(id))) {
                return e.elementText("color");
            }
        }
        return null;
    }
}

