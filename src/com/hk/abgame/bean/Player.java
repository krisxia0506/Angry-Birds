package com.hk.abgame.bean;

import lombok.Data;

/**
 * Created on 2022-06-28 14:28
 * 玩家类
 * @author Xia Jiayi
 */
@Data
public class Player {
    private Integer id;
    private String login_name;
    private String password;
    private String nickname;
    private Integer sex;
    private Integer age;
    private String creat_time;

}
