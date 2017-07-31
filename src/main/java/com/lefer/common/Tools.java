package com.lefer.common;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author fang
 * @creatdate 17-7-14
 */
public class Tools {
    @Value("${wx.appid}")
    static String wxAppId;
    @Value("${wx.secret")
    static String wxSecret;
    @Value("${wx.url}")
    static String wxUrl;
    @Value("${wx.grantType}")
    static String grantType;

    public final static String genWeixinLoginUrl(String code) {
        return wxUrl + "appid=" + wxAppId + "&secret=" + wxSecret + "&js_code=" + code + "&grant_type=" + grantType;
    }
}
