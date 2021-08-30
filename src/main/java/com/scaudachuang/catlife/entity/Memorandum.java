package com.scaudachuang.catlife.entity;

import lombok.Data;
import org.joda.time.DateTime;

import java.sql.Timestamp;

@Data
public class Memorandum {
    private int ownerId;
    private DateTime reDatetime;
   private String taskJSON;
}
