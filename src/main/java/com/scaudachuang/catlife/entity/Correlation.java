package com.scaudachuang.catlife.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author hiluyx
 * @since 2021/8/25 23:03
 **/
@Data
public class Correlation {
    @TableField("nId")
    private Long nId;
    @TableId("beNid")
    private Long beNid;
    @TableField("bfDatetime")
    private Timestamp bfDatetime;
    private boolean bf;
}
