package com.scaudachuang.catlife.web;

import com.scaudachuang.catlife.dao.CatOwnerMapper;
import com.scaudachuang.catlife.pojo.wx.LoginParams;
import com.scaudachuang.catlife.pojo.wx.WxSessionResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
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
    private CatOwnerMapper catOwnerMapper;

    @Resource
    private WxCodedInfoServerHelper wxCodedInfoServerHelper;

    @RequestMapping(path = "wxLogin", method = RequestMethod.POST)
    public void wxLogin(@RequestParam(value = "/login_params") LoginParams params,
                        HttpServletRequest request, HttpServletResponse response) throws ConnectException {
        String code = params.getCode();
        String encryptedData = params.getEncryptedData();
        String iv = params.getIv();
        WxSessionResponse wxSessionResponse = wxCodedInfoServerHelper.code2Session(code);
        if (wxSessionResponse.getErrCode() != 0) {
            throw new ConnectException(wxSessionResponse.getErrMsg());
        }
        String openId = wxSessionResponse.getOpenId();
        String sessionKey = wxSessionResponse.getSessionKey();

    }
}
