package com.hk.abgame.util;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created on 2022-06-30 10:35
 * 向XML文件中写入数据
 *
 * @author Xia Jiayi
 */
public class XMLFWriter {

    public static boolean writeData(Document doc) {
        boolean b;
        //定义一个输出格式
        OutputFormat format = OutputFormat.createCompactFormat();
        //换行
        format.setNewlines(true);
        //缩进
        format.setIndent(true);
        format.setIndent("\t");
        //编码
        format.setEncoding("UTF-8");
        //创建xml文件
        try {
            XMLWriter xw = new XMLWriter(Files.newOutputStream(Paths.get("DataInit.xml")), format);
            //写文件
            xw.write(doc);
            xw.flush();
            xw.close();
            b = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return b;
    }
}
