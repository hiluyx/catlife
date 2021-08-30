package com.scaudachuang.catlife.entity;

import lombok.Data;
import org.joda.time.DateTime;

import java.sql.Timestamp;

@Data
public class Comments {
    private DateTime timeOfCom;
    private DateTime timeOfMom;
    private int ownerId;
    private String contentJSON;
}
