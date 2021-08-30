package com.scaudachuang.catlife.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.joda.time.DateTime;

import java.sql.Timestamp;

@Data
public class Memorandum {
    @TableId
    private int id;
    private int ownerId;
    private DateTime reDatetime;
    private String taskJSON;
}
