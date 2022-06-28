package com.hk.abgame.main;

import com.hk.abgame.bean.Login;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import javax.swing.text.Document;
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
            Document document = (Document) reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }


    }
}
