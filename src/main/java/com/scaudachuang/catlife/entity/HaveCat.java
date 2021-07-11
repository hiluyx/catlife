package com.scaudachuang.catlife.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class HaveCat {
    private int ownerId;
    private String catClass;
    private int haveCatId;
    private Timestamp birthday;
    private String catName;
    private boolean isSterilization;
    private boolean isBear;
    private String drugAllergy;
    private String likes;

    /*
    * 一对多级联
    * */
    List<CatLifeRecord> catLifeRecordList;
}
