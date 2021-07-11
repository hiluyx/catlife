package com.scaudachuang.catlife.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Data
public class CatLifeRecord {

    private int ownerId;
    private String catClass;
    private int haveCatId;
    private Timestamp reDateTime;

    private String growth;
    private String diet;
    private String hygiene;

    public static Map<String, String> getMapFromString(String s) {
        if (s == null) return null;
        if (s.length() == 0) return null;
        String[] splits = s.split(";");
        final Map<String, String> attMap = new HashMap<>();
        for (String split : splits) {
            String[] split1 = split.split(":");
            if (split1.length > 2)
                throw new IndexOutOfBoundsException("split1.length > 2");
            attMap.put(split1[0],split1[1]);
        }
        return attMap;
    }
}
