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
    /*读取管理员信息*/
    public static Login getAdminLogin (Document document) {
        //获取根节点
        Element root = document.getRootElement();
        //获取admin-login节点
        String loginname = root.element("admin").attributeValue("loginname");
        //获取password节点
        String password = root.element("admin").attributeValue("password");
        //获取logintimes节点
        String logintimes = root.element("admin").attributeValue("logintimes");
        //创建Login对象
        Login admin = new Login();
        admin.setLoginname(loginname);
        admin.setPassword(password);
        admin.setLogintimes(Integer.parseInt(logintimes));
        return admin;
    }
}
