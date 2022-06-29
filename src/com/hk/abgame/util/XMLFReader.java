package com.hk.abgame.util;
import com.hk.abgame.bean.Login;
import org.dom4j.Document;
import org.dom4j.Element;

/**
 * Created on 2022-06-28 10:41
 *
 * @author Xia Jiayi
 */
public class XMLFReader {
    /**
     * 读取XML文件，并返回Login对象
     */
    public static Login getAdminLogin (Document document) {
        //获取根节点
        Element root = document.getRootElement();
        //获取admin-login节点
        String loginname = root.element("admin").elementText("loginname");
        //获取password节点
        String password = root.element("admin").elementText("password");
        //获取logintimes节点
        String logintimes = root.element("admin").elementText("logintimes");
        //创建Login对象
        Login admin = new Login();
        admin.setLoginname(loginname);
        admin.setPassword(password);
        admin.setLogintimes(Integer.parseInt(logintimes));
        return admin;
    }
}
