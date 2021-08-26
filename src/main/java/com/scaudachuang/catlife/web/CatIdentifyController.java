package com.scaudachuang.catlife.web;

import com.scaudachuang.catlife.dao.RedisDao;
import com.scaudachuang.catlife.model.TopHotDetection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hiluyx
 * @since 2021/8/26 21:50
 **/
@Controller
@RequestMapping("/public")
public class CatIdentifyController {
    @Resource
    private RedisDao redisDao;

    @GetMapping("/topIdentity")
    public List<TopHotDetection> getTopDetect(@RequestParam(value = "top", required = false) int top) {
        if (top <= 0) top = 10;
        List<TopHotDetection> topHotZSetN = redisDao.getTopHotZSetN(top);
        return topHotZSetN;
    }
}
