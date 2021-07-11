package com.scaudachuang.catlife.dao;

import com.scaudachuang.catlife.pojo.CorrelationInfoBar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface UserMapper {
    /*分页，通过id查找自己的粉丝*/
    List<CorrelationInfoBar> getUserFollowedInfoBar(int id, RowBounds rowBounds);
    /*黑名单同理*/
    List<CorrelationInfoBar> getUserBlackListedInfoBar(int id, RowBounds rowBounds);

}
