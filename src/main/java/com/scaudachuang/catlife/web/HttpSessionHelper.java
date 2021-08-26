package com.scaudachuang.catlife.web;

import com.scaudachuang.catlife.session.UserSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;

/**
 * @author hiluyx
 * @since 2021/8/22 17:29
 **/
public class HttpSessionHelper {
    public static final String USER_SESSION_ATTR_STRING = "usr_session";

    public static UserSession getUserSessionValue(HttpServletRequest request) throws Exception{
        return HttpSessionHelper.getSessionVal(UserSession.class, request, USER_SESSION_ATTR_STRING);
    }

    public static <M> M getSessionVal(Class<M> mClass,
                                      HttpServletRequest request,
                                      String sessionKey)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        HttpSession session = request.getSession();
        Object attribute = session.getAttribute(sessionKey);
        if (attribute == null) {
            M m = mClass.getConstructor().newInstance();
            session.setAttribute(sessionKey, m);
            return m;
        } else {
            return (M) attribute;
        }
    }

    public static void setUserSessionId(HttpServletRequest request, long defineOnlineStatus) throws Exception {
        UserSession userSessionValue = getUserSessionValue(request);
        userSessionValue.setDefineOnlineStatus(defineOnlineStatus);
    }
}
