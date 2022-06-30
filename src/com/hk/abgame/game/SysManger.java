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
    public boolean setLoginname(String loginname) {
        //获取根节点
        Element root = DataInit.document.getRootElement();
        //获取admin节点
        Element e = root.element("admin");
        //获取loginname节点并对其内容进行修改
        e.element("loginname").setText(loginname);
        return XMLFWriter.writeData(DataInit.document);
    }

    /**
     * 修改小鸟参数
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

