package com.scaudachuang.catlife.service;

import com.scaudachuang.catlife.dao.CatOwnerMapper;
import com.scaudachuang.catlife.entity.CatOwner;
import com.scaudachuang.catlife.model.wx.WxUserDecryptedInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CatOwnerService {
    @Resource
    private CatOwnerMapper catOwnerMapper;

    /**
     * 存在则更新获取
     * 不存在就插入
     * @param openId 微信的用户id
     * @param wxUserDecryptedInfo 微信的用户信息
     * @return 用户再服务器的表数据
     */
    public CatOwner existThenGetOtherwiseInsert(String openId,
                                                WxUserDecryptedInfo wxUserDecryptedInfo,
                                                String sessionKey) {
        CatOwner catOwner = new CatOwner();
        catOwner.setOpenId(openId);
        catOwner.setAvatar(wxUserDecryptedInfo.getAvatarUrl());
        catOwner.setNickname(wxUserDecryptedInfo.getNickName());
        catOwner.setSessionKey(sessionKey);
        catOwnerMapper.replaceInsertOwner(catOwner);
        return catOwnerMapper.getByOpenId(openId);
    }
}
