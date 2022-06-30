package com.hk.abgame.bean;

import lombok.Data;

/**
 * Created on 2022-06-28 9:41
 *
 * @author Xia Jiayi
 */
@Data
public class Login {
    private String loginname;
    private String password;
    private int logintimes;

    public String toString() {
        return "loginname:" + loginname + " password:" + password + " logintimes:" + logintimes;
    }
}
