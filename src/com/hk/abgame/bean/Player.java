package com.hk.abgame.bean;

import lombok.Data;

/**
 * Created on 2022-06-28 14:28
 *
 * @author Xia Jiayi
 */
@Data
public class Player {
    private Integer id;
    private String loginname;
    private String password;
    private String nickname;
    private Integer sex;
    private Integer age;

}
