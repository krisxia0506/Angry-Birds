package com.hk.abgame.util;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created on 2022-07-06 9:08
 * 用于日期的工具类
 * @author Xia Jiayi
 */
public class DateUtil {
    /**
     * 字符串转换成日期
     */
    public static Date stringToDate(String str, String format) {
        DateFormat df = new java.text.SimpleDateFormat(format);
        Date date = null;
        try {
            date = df.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 把日期转换成特定格式的字符串
     */
    public static String getDateTime(Date date) {
        //创建日期对象
        Calendar calendar = Calendar.getInstance();
        //设置传入的日期
        calendar.setTime(date);
        //获取年
        int year = calendar.get(Calendar.YEAR);
        //获取月
        int month = calendar.get(Calendar.MONTH) + 1;
        //获取日
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //获取时
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        //获取分
        int minute = calendar.get(Calendar.MINUTE);
        //获取秒
        int second = calendar.get(Calendar.SECOND);
        return year + "年" + month + "月" + day + "日 " + hour + ":" + minute + ":" + second;
    }
}
