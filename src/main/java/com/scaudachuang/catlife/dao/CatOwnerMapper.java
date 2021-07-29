package com.scaudachuang.catlife.dao;

import com.scaudachuang.catlife.pojo.CorrelationInfoBar;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hiluyx
 * @since 2021/7/11 21:32
 **/
@Repository
public interface CatOwnerMapper {
    /*分页，通过id查找自己的粉丝*/
    List<CorrelationInfoBar> getUserFollowedInfoBar(
            int id,
            RowBounds rowBounds);
    /*黑名单同理*/
    List<CorrelationInfoBar> getUserBlackListedInfoBar(
            int id,
            RowBounds rowBounds
    );
}
