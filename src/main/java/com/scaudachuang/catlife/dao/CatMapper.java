package com.scaudachuang.catlife.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scaudachuang.catlife.entity.Cat;
import com.scaudachuang.catlife.entity.HaveCat;
import com.scaudachuang.catlife.model.SimpleHaveCatInfoBar;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CatMapper extends BaseMapper<HaveCat> {
    Cat getCat(String cl);

    Set<String> catRepos();

    List<SimpleHaveCatInfoBar> getAllOwnerSimpleHaveCatByIdAndCatClass(
            @Param("ownerId") long ownerId,
            @Param("catClass") String catClass,
            RowBounds rowBounds
    );

    HaveCat getOneHaveCat(
            @Param("ownerId") long ownerId,
            @Param("catClass") String catClass,
            @Param("haveCatId") int haveCatId
    );

    int getNewNumberOfMyHaveCat(@Param("ownerId") long ownerId,
                                @Param("catClass") String catClass);

}
