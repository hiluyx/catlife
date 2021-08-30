package com.scaudachuang.catlife.web;

import com.scaudachuang.catlife.dao.RedisDao;
import com.scaudachuang.catlife.entity.CatOwner;
import com.scaudachuang.catlife.model.RequestMessage;
import com.scaudachuang.catlife.model.session.UserSession;
import com.scaudachuang.catlife.model.wx.LoginParams;
import com.scaudachuang.catlife.model.wx.WxSessionResponse;
import com.scaudachuang.catlife.model.wx.WxUserDecryptedInfo;
import com.scaudachuang.catlife.service.CatOwnerService;
import com.scaudachuang.catlife.utils.HttpHelper;
import com.scaudachuang.catlife.utils.WxCodedInfoServerHelper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.ConnectException;

/**
 * @author hiluyx
 * @since 2021/7/11 21:12
 **/
@RestController
@RequestMapping("/self")
public class LoginController {

    @Resource
    private WxCodedInfoServerHelper wxCodedInfoServerHelper;

    @Resource
    private CatOwnerService catOwnerService;

    @Resource
    private RedisDao redisDao;

    @GetMapping("/myselfInfo")
    public RequestMessage<CatOwner> myself(HttpServletRequest request) throws Exception {
        UserSession userSessionValue = HttpHelper.getUserSessionValue(request);
        if (userSessionValue.getDefineOnlineStatus() <= 0)
            return RequestMessage.ERROR(404, "尚未登录", null);
        return RequestMessage.OK(catOwnerService.getMyselfInfo(userSessionValue.getDefineOnlineStatus()));
    }

    @PostMapping(path = "/login")
    public RequestMessage<CatOwner> wxLogin(@RequestBody LoginParams params,
                                            HttpServletRequest request) throws ConnectException {
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

            HttpHelper.setUserSessionId(request, catOwner.getOwnerId());
            return RequestMessage.OK(catOwner);
        } catch (Exception e) {
            return RequestMessage.ERROR(500, "decrypt uer info fail", null);
        }
    }
}
