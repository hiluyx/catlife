package com.scaudachuang.catlife.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author hiluyx
 * @since 2021/8/25 23:03
 **/
@Data
public class Correlation {
    private long nId;
    private long beNid;
    private Timestamp bfDatetime;
    boolean bf;
}
