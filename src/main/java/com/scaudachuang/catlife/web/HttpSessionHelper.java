package com.scaudachuang.catlife.web;

import com.scaudachuang.catlife.session.UserSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author hiluyx
 * @since 2021/8/22 17:29
 **/
public class HttpSessionHelper {
    public static final String USER_SESSION_ATTR_STRING = "usr_session";

    public static UserSession getSessionValue(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object attribute = session.getAttribute(USER_SESSION_ATTR_STRING);
        return (UserSession) attribute;
    }
}
