package com.scaudachuang.catlife.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.util.List;

@Data
public class Moments {
    @TableId
    private int id;
    private DateTime timeOfMom;
    private int ownerId;
    private String content;
    private int likes;
    private String carryPhoto;
    private int comTime;
}
