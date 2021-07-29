package com.scaudachuang.catlife.config;

import com.scaudachuang.catlife.dao.RedisDao;
import com.sun.org.apache.bcel.internal.Const;
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
        Cookie[] cookies = request.getCookies();
        // 没有cookie信息，则重定向到登录界面
        if (null == cookies) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if (!name.equals("define_online_status")) continue;
            String value = cookie.getValue();

        }
        return false;
    }
}
