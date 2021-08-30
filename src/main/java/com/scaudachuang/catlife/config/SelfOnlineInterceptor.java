package com.scaudachuang.catlife.config;

import com.scaudachuang.catlife.dao.RedisDao;
import com.scaudachuang.catlife.model.session.UserSession;
import com.scaudachuang.catlife.utils.HttpSessionHelper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hiluyx
 * @since 2021/7/11 21:17
 **/
public class SelfOnlineInterceptor implements HandlerInterceptor {

    @Resource
    private RedisDao redisDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获得session
        UserSession sessionValue = HttpSessionHelper.getUserSessionValue(request);
        if (sessionValue.getDefineOnlineStatus() == 0) { // 用户没有登录
            response.setStatus(404);
            return false;
        }

        /*
         * 这里没必要再查mysql验证 DefineOnlineStatus 是否正确？
         */
        return true;
    }
}
