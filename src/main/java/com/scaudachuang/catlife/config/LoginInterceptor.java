package com.scaudachuang.catlife.config;

import com.scaudachuang.catlife.dao.RedisDao;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author hiluyx
 * @since 2021/7/11 21:17
 **/
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private RedisDao redisDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获得cookie
        StringBuffer requestURL = request.getRequestURL();
        Cookie[] cookies = request.getCookies();

        if (null != cookies) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if (!name.equals("define_online_status")) continue;
                String ownerId = cookie.getValue(); // ownerid
                String onlineKey = RedisDao.ONLINE_PREFIX + ownerId;
                if (redisDao.hasKey(onlineKey)) {
                    redisDao.expireOn1Hour(onlineKey); // 续上一小时
                    return true;
                }
            }
        }
        // 没有cookie信息，则重定向到登录界面
        response.sendRedirect(request.getContextPath() + "/wxLogin");
        return false;
    }
}
