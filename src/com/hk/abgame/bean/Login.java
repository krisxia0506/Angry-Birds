package com.hk.abgame.bean;

import lombok.Data;

/**
 * Created on 2022-06-28 9:41
 * 管理员类
 * @author Xia Jiayi
 */
@Data
public class Login {
    private String login_name;
    private String password;
    private int login_times;

    public String toString() {
        return "login_name:" + login_name + " password:" + password + " login_times:" + login_times;
    }
}
