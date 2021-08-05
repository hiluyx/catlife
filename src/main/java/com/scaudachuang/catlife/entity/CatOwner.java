package com.scaudachuang.catlife.entity;

import lombok.Data;

import java.util.List;

@Data
public class CatOwner {

    private int ownerId;
    private String openId;
    private String sessionKey;
    private String nickname;
    private String avatar;

    /*
    List<HaveCat> haveCatList;

    List<Memorandum> memorandumList;

    List<Moments> momentsList;

    List<Comments> commentsList;*/
}
