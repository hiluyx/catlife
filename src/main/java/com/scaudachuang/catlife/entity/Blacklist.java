package com.scaudachuang.catlife.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Blacklist {

    private int nId;
    private int beNId;
    private Timestamp fDatetime;
}
