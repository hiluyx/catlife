package com.scaudachuang.catlife.web;

import com.scaudachuang.catlife.entity.CatOwner;
import com.scaudachuang.catlife.model.RequestMessage;
import com.scaudachuang.catlife.model.wx.LoginParams;
import com.scaudachuang.catlife.model.wx.WxSessionResponse;
import com.scaudachuang.catlife.model.wx.WxUserDecryptedInfo;
import com.scaudachuang.catlife.service.CatOwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.ConnectException;

/**
 * @author hiluyx
 * @since 2021/7/11 21:12
 **/
@Controller
public class LoginController {

    @Resource
    private WxCodedInfoServerHelper wxCodedInfoServerHelper;

    @Resource
    private CatOwnerService catOwnerService;

    @RequestMapping(path = "/wxLogin", method = RequestMethod.POST)
    public RequestMessage<CatOwner> wxLogin(@RequestParam(value = "login_params") LoginParams params,
                                            HttpServletRequest request, HttpServletResponse response) throws ConnectException {
        /* 登录参数 */
        final String code = params.getCode();
        final String encryptedData = params.getEncryptedData();
        final String iv = params.getIv();
        /* 与wx server建立session */
        WxSessionResponse wxSessionResponse = wxCodedInfoServerHelper.code2Session(code);
        if (wxSessionResponse.getErrCode() != 0) {
            return RequestMessage.ERROR(wxSessionResponse.getErrCode(), wxSessionResponse.getErrMsg(), null);
        }
        String openId = wxSessionResponse.getOpenId();
        String sessionKey = wxSessionResponse.getSessionKey();
        try {
            /* 解压用户信息 */
            WxUserDecryptedInfo wxUserDecryptedInfo = WxCodedInfoServerHelper.decryptUserInfo(encryptedData, sessionKey, iv);
            /* 获取数据库用户数据 */
            CatOwner catOwner = catOwnerService.existThenGetOtherwiseInsert(openId, wxUserDecryptedInfo, sessionKey);
            /* 登录cookie */
            Cookie cookie = new Cookie("define_online_status", String.valueOf(catOwner.getOwnerId()));
            response.addCookie(cookie);
            return RequestMessage.OK(catOwner);
        } catch (Exception e) {
            return RequestMessage.ERROR(500, "decrypt uer info fail", null);
        }
    }
}
