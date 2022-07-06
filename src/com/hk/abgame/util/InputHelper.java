package com.hk.abgame.util;

import java.io.IOException;
import java.util.Scanner;

import static com.hk.abgame.util.StringUtil.isRegex;

/**
 * Created on 2022-06-28 10:40
 * 用于输入的工具类
 *
 * @author Xia Jiayi
 */
public class InputHelper {
    public static int getInt() {
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            System.err.println("输入错误，请输入数字");
            return getInt();
        }
    }

    public static String getString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    /**
     * 重载getString，提供验证功能
     *
     * @param regex 正则表达式
     * @param msg   提示信息
     * @return 输入的字符串
     */
    public static String getString(String regex, String msg) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        while (!isRegex(s, regex)) {
            System.out.println("输入不符合规范," + msg);
            System.out.println("请重新输入");
            s = InputHelper.getString();
        }
        return s;
    }

    /**
     * 重载getInt，提供验证功能
     *
     * @param min 最小值
     * @param max 最大值
     * @return 输入的数字
     */
    public static int getInt(int min, int max) {
        int i = getInt();
        while (i < min || i > max) {
            System.out.println("输入不符合规范，请重新输入");
            i = getInt();
        }
        return i;
    }

    /**
     * 按任意键继续
     */
    public static void pressAnyKey() {
        try {
            System.out.println("按任意键继续");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
