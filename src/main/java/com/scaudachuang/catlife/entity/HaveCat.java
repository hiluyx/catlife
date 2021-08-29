package com.scaudachuang.catlife.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class HaveCat {
    @TableField
    private Integer ownerId;
    @TableField
    private String catClass;
    @TableField
    private Integer haveCatId;
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
