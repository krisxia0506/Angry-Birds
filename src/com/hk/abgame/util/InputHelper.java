package com.hk.abgame.util;

import java.util.Scanner;

import static com.hk.abgame.util.StringUtil.isRegex;

/**
 * Created on 2022-06-28 10:40
 * 用于输入的工具类
 * @author Xia Jiayi
 */
public class InputHelper {
    public static int getInt() {
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            System.out.println("输入错误，请输入数字");
            return getInt();
        }
    }

    public static String getString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
    public static String getString(String regex, String msg) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        while (!isRegex(s,regex)) {
            System.out.println("输入不符合规范,"+msg);
            System.out.println("请重新输入");
            s = InputHelper.getString();
        }
        return s;
    }
}
