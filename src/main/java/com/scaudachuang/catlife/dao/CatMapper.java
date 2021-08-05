package com.scaudachuang.catlife.dao;

import com.scaudachuang.catlife.entity.Cat;
import com.scaudachuang.catlife.entity.HaveCat;
import com.scaudachuang.catlife.model.SimpleHaveCatInfoBar;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatMapper {
    Cat getCat(String cl);
    List<SimpleHaveCatInfoBar> getAllOwnerSimpleHaveCatById(int ownerId, RowBounds rowBounds);
    List<SimpleHaveCatInfoBar> getAllOwnerSimpleHaveCatByIdAndCatClass(
            @Param("ownerId") int ownerId,
            @Param("catClass") String catClass,
            RowBounds rowBounds
    );

    HaveCat getOneHaveCat(
            @Param("ownerId") int ownerId,
            @Param("catClass") String catClass,
            @Param("haveCatId") int haveCatId,
            RowBounds rowBounds
    );
}
