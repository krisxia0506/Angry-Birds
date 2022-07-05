package com.hk.abgame.bean;

import lombok.Data;

/**
 * Created on 2022-07-04 9:17
 *
 * @author Xia Jiayi
 */
@Data
public class Game {
    private Integer id;
    private Integer pid;
    private String play_time;
    private Integer play_score;

    @Override
    public String toString() {
        return id + "\t\t"  + play_time + '\t' + play_score;
    }
}
