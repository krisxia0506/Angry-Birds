package com.hk.abgame.main;

import com.hk.abgame.bean.Login;
import com.hk.abgame.util.XMLFReader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import java.io.File;

/**
 * Created on 2022-06-28 10:15
 *
 * @author Xia Jiayi
 */
public class DataInit {
    public static Login login = new Login();
    static {
        SAXReader reader = new SAXReader();
        File file = new File("DataInit.xml");

        try {
            Document document = reader.read(file);
            login = XMLFReader.getAdminLogin(document);
        } catch (DocumentException e) {
            e.printStackTrace();
        }


    }
}
