package com.scaudachuang.catlife.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.joda.time.DateTime;

@Data
public class HaveCat {
    @TableField
    private Integer ownerId;
    @TableField
    private String catClass;
    @TableField
    private Integer haveCatId;
    private DateTime birthday;
    private String catName;
    private boolean isSterilization;
    private boolean isBear;
    private String drugAllergy;
    private String likes;
}
