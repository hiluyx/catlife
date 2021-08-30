package com.scaudachuang.catlife.web;

import com.scaudachuang.catlife.dao.RedisDao;
import com.scaudachuang.catlife.model.RequestMessage;
import com.scaudachuang.catlife.model.TopHotDetection;
import com.scaudachuang.catlife.utils.LimitProcessing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hiluyx
 * @since 2021/8/26 21:50
 **/
@RestController
@RequestMapping("/public")
public class CatIdentifyController {
    @Resource
    private RedisDao redisDao;

    @GetMapping("/topIdentity")
    @LimitProcessing(name = "topHot", ratePerSec = 1)
    public RequestMessage<Map<String, Object>> getTopDetect(@RequestParam(value = "top", required = false) int top) {
        if (top <= 0)
            top = 10;
        List<TopHotDetection> topHotZSetN = redisDao.getTopHotZSetN(top);
        HashMap<String, Object> map = new HashMap<>();
        map.put("tops", topHotZSetN);
        map.put("nums", topHotZSetN.size());
        return RequestMessage.OK(map);
    }
}
