package com.scaudachuang.catlife.web;

import com.scaudachuang.catlife.dao.CatOwnerMapper;
import com.scaudachuang.catlife.entity.Blacklist;
import com.scaudachuang.catlife.model.CorrelationInfoBar;
import com.scaudachuang.catlife.model.RequestMessage;
import com.scaudachuang.catlife.service.CatOwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户的私人信息
 * - 粉丝
 * - 黑名单
 * - 有哪些猫
 * - 猫日记
 */
@Controller
@RequestMapping("/catLife")
public class CatOwnerSelfInfoController {
    @Resource
    private CatOwnerService catOwnerService;

    /**
     * 粉丝
     * 黑名单
     */
    @RequestMapping("/correlationList")
    public RequestMessage<Map<String, Object>> getMyBlackList(@RequestParam("ownerId") long ownerId,
                                                              @RequestParam("bf") int bf,
                                                              @RequestParam("limit") int limit,
                                                              @RequestParam("page") int page,
                                                              HttpServletRequest request) {
        List<CorrelationInfoBar> correlationList = catOwnerService.getCorrelationList(page, limit, bf == 1, ownerId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("correlationList", correlationList);
        map.put("nums", correlationList.size());
        return RequestMessage.OK(map);
    }
}
