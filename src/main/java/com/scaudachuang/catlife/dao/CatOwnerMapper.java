package com.scaudachuang.catlife.dao;

import com.scaudachuang.catlife.entity.CatOwner;
import com.scaudachuang.catlife.model.CorrelationInfoBar;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hiluyx
 * @since 2021/7/11 21:32
 **/
@Repository
public interface CatOwnerMapper {

    CatOwner getByOpenId(@Param("openId") String openId);

    CatOwner getSelf(@Param("ownerId") long ownerId);

    int replaceInsertOwner(@Param("catOwner") CatOwner catOwner);
}
