package com.scaudachuang.catlife.service;

import com.scaudachuang.catlife.dao.CatOwnerMapper;
import com.scaudachuang.catlife.dao.CorrelationMapper;
import com.scaudachuang.catlife.entity.CatOwner;
import com.scaudachuang.catlife.entity.Correlation;
import com.scaudachuang.catlife.model.CorrelationInfoBar;
import com.scaudachuang.catlife.model.wx.WxUserDecryptedInfo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class CatOwnerService {
    @Resource
    private CatOwnerMapper catOwnerMapper;
    @Resource
    private CorrelationMapper correlationMapper;

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
        int i = catOwnerMapper.replaceInsertOwner(catOwner);
        if (i <= 0)
            return null;
        return catOwnerMapper.getByOpenId(openId);
    }

    public List<CorrelationInfoBar> getCorrelationList(int page, int limit, boolean bf, long ownerId) {
        RowBounds rowBounds = new RowBounds(page, limit);
        List<CorrelationInfoBar> userCorrelationInfoBar = catOwnerMapper.getUserCorrelationInfoBar(ownerId, bf, rowBounds);

        return userCorrelationInfoBar;
    }

    public boolean newCorrelation(long nId, long beNid, boolean bf) {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Correlation correlation = new Correlation();
        correlation.setBf(bf);
        correlation.setBeNid(beNid);
        correlation.setNId(nId);
        correlation.setBfDatetime(timestamp);
        int i = correlationMapper.insert(correlation);
        return i > 0;
    }
}
