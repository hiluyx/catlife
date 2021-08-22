package com.scaudachuang.catlife.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserSession implements Serializable {
    private long defineOnlineStatus; // 如果为0，说明未登录
    private int taskNum; // 当前用户的任务数
    private int taskHistory; // 历史任务数量
    private String nowTaskUUID; // 当前的任务UUID
}
