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

    public String getLoginname() {
        return loginname;
    }
    public Login(){
        super();
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLogintimes() {
        return logintimes;
    }

    public void setLogintimes(int logintimes) {
        this.logintimes = logintimes;
    }
    public String toString(){
        return "loginname:"+loginname+" password:"+password+" logintimes:"+logintimes;
    }
}
