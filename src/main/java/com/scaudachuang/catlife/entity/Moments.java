package com.scaudachuang.catlife.entity;

import lombok.Data;
import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.util.List;

@Data
public class Moments {
    private DateTime timeOfMom;
    private int ownerId;
    private String content;
    private int likes;
    private String carryPhoto;
    private int comTime;
}
