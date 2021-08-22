package com.scaudachuang.catlife.web;

import com.scaudachuang.catlife.dao.RedisDao;
import com.scaudachuang.catlife.entity.CatOwner;
import com.scaudachuang.catlife.model.RequestMessage;
import com.scaudachuang.catlife.model.wx.LoginParams;
import com.scaudachuang.catlife.model.wx.WxSessionResponse;
import com.scaudachuang.catlife.model.wx.WxUserDecryptedInfo;
import com.scaudachuang.catlife.service.CatOwnerService;
import com.scaudachuang.catlife.session.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.ConnectException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    @Resource
    private RedisDao redisDao;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
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
        /* ******************** */

        String openId = wxSessionResponse.getOpenId();
        String sessionKey = wxSessionResponse.getSessionKey();
        try {
            /* 解压用户信息 */
            WxUserDecryptedInfo wxUserDecryptedInfo = WxCodedInfoServerHelper.decryptUserInfo(encryptedData, sessionKey, iv);
            /* 获取或覆盖数据库用户数据 */
            CatOwner catOwner = catOwnerService.existThenGetOtherwiseInsert(openId, wxUserDecryptedInfo, sessionKey);

            if (catOwner == null)
                return RequestMessage.ERROR(500, "login fail", null);

            UserSession session = HttpSessionHelper.getSessionValue(request);
            // 不知道这里还需不需要保存进session
            session.setDefineOnlineStatus(catOwner.getOwnerId());

            return RequestMessage.OK(catOwner);
        } catch (Exception e) {
            return RequestMessage.ERROR(500, "decrypt uer info fail", null);
        }
    }
}
