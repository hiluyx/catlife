package com.scaudachuang.catlife.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSession implements Serializable {
    private int taskNum;
    private int taskHistory;
    private String nowTaskUUID;
}
