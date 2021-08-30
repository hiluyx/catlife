package com.scaudachuang.catlife.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.joda.time.DateTime;

import java.sql.Timestamp;

@Data
public class Comments {
    @TableId
    private int id;
    private DateTime timeOfCom;
    private DateTime timeOfMom;
    private long ownerId;
    private long comOwnerId;
    private String content;
}
