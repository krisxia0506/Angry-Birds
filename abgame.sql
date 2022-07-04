drop schema if exists abgame;
create schema abgame;
drop table if exists player;
create table player
(
    id         int auto_increment
        primary key,
    login_name varchar(20)                        not null,
    password   varchar(20)                        not null,
    nickname   varchar(20)                        not null,
    sex        int      default 1                 not null,
    age        int                                not null,
    creat_time datetime default CURRENT_TIMESTAMP null
);
drop table if exists game;
create table game
(
    id         int auto_increment
        primary key,
    pid        int      null,
    play_time  datetime null,
    play_score int      null
);



