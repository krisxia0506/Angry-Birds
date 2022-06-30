package com.hk.abgame.util;

import java.util.Scanner;

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
}
