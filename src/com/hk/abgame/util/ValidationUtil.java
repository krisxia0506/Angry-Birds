package com.hk.abgame.util;

/**
 * Created on 2022-07-01 9:47
 * 用于验证的工具类
 * @author Xia Jiayi
 */
public class ValidationUtil {

    //登录名:不能全部由数字组成，必须要有字母
    public static final String CHKLOGINNANE = "^(?![0-9]+$)[a-zA-Z0-9]{1,16}$";
    //密码:有数字和字母，长度不小于六位
    public static final String CHKPASSIORD = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9a-zA-Z]{6,16}$";
    //性别:男或者女male female
    public static final String CHKSEX = "^['男','女']$";
    //年龄:必须是正整数，1-99
    public static final String CHKAGE = "^[1-9][0-9]?$";
    //小鸟颜色
    public static final String CHKBIRD = "^['黑色','蓝色','红色','黄色','白色','绿色']{2}";

}
