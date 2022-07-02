package com.hk.abgame.main;

import com.hk.abgame.bean.Login;
import com.hk.abgame.game.Bird;
import com.hk.abgame.util.XMLFReader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2022-06-28 10:15
 * XML文件读取到内存中
 * @author Xia Jiayi
 */
public class DataInit {
    /**
     * 管理员登录信息
     */
    public static Login login = new Login();
    /**
     * 小鸟类型
     */
    public static List<Bird> birdTypes = new ArrayList<>();
    /**
     * 读取XML文件
     */
    public static Document document;
    static {
        SAXReader reader = new SAXReader();
        File file = new File("DataInit.xml");

        try {
            document = reader.read(file);
            login = XMLFReader.getAdminLogin(document);
            birdTypes = XMLFReader.getBirdType(document);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
