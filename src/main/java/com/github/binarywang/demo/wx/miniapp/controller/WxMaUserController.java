package com.github.binarywang.demo.wx.miniapp.controller;

import com.github.binarywang.demo.wx.miniapp.error.BaseRuntimeException;
import com.github.binarywang.demo.wx.miniapp.vo.EncryptedDataVo;
import com.github.binarywang.demo.wx.miniapp.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.github.binarywang.demo.wx.miniapp.config.WxMaConfiguration;
import com.github.binarywang.demo.wx.miniapp.utils.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;

import javax.annotation.PostConstruct;

/**
 * 微信小程序用户接口
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RestController
@RequestMapping("/wx/user/{appid}")
public class WxMaUserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 登陆接口
     */
    @GetMapping("/login")
    public Result<WxMaJscode2SessionResult> login(@PathVariable String appid, String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            this.logger.info(session.getSessionKey());
            this.logger.info(session.getOpenid());
            //TODO 可以增加自己的逻辑，关联业务相关数据
            return Result.of(session);
        } catch (WxErrorException e) {
            throw new BaseRuntimeException("内部异常："+e.getMessage());
        }
    }

    /**
     * <pre>
     * 获取用户信息接口
     * </pre>
     */
    @GetMapping("/info")
    public Result<WxMaUserInfo> info(@PathVariable String appid, String sessionKey,
                       String signature, String rawData, String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);
        // 解密用户信息
        WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
        return Result.of(userInfo);
    }

    /**
     * <pre>
     * 获取用户绑定手机号信息
     * </pre>
     */
    @PostMapping("/phone")
    public Result<WxMaPhoneNumberInfo> phone(@PathVariable String appid, @RequestBody EncryptedDataVo vo) throws WxErrorException {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);
        // 解密
        return Result.of(wxService.getUserService().getPhoneNoInfo(vo.getSessionKey(), vo.getEncryptedData(), vo.getIv()));
    }

}
