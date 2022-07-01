package com.hk.abgame.util;

/**
 * Created on 2022-07-01 10:03
 *
 * @author Xia Jiayi
 */
public class StringUtil {
    /**
     * 判断字符串是否为空
     * @param str 要验证的字符串
     * @return 是否为空
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 判断字符串是否复合正则表达式
     * @param str 要验证的字符串
     * @param regex 正则表达式
     * @return 是否复合正则表达式
     */
    public static boolean isRegex(String str, String regex) {
        if (isEmpty(str)) {
            return false;
        }
        return str.matches(regex);
    }
}
