package com.lefer.common;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    public static boolean verify(String key){
        return generateKey().equals(key);
    }

    public static String generateKey(){
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();

        long key = (year-month-day)*(day+month)/2;

        char[] seedStr= (key+"").toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for(char c:seedStr){
            switch (c){
                case '0':
                    stringBuilder.append("c");
                    break;
                case '1':
                    stringBuilder.append("e");
                    break;
                case '2':
                    stringBuilder.append("d");
                    break;
                case '3':
                    stringBuilder.append("f");
                    break;
                case '4':
                    stringBuilder.append("n");
                    break;
                case '5':
                    stringBuilder.append("z");
                    break;
                case '6':
                    stringBuilder.append("x");
                    break;
                case '7':
                    stringBuilder.append("m");
                    break;
                case '8':
                    stringBuilder.append("t");
                    break;
                case '9':
                    stringBuilder.append("s");
                    break;
                 default:
                     stringBuilder.append("r");
                     break;
            }

        }
        return stringBuilder.toString();
    }
}
