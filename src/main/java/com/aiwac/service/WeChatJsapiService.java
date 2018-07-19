package com.aiwac.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omg.PortableInterceptor.ForwardRequestHelper;

import com.aiwac.bean.request.WeChatJsTicket;
import com.aiwac.bean.request.WeChatJsToken;
import com.aiwac.constant.WeChatConstant;
import com.aiwac.controller.WechatGetSignature;
import com.aiwac.utils.JsonUtil;

public class WeChatJsapiService {
	private static String jsapi_ticket;
	private static long last;
	
	
	private static final Logger logger = LogManager.getLogger(WeChatJsapiService.class);
	
	public static String  getToken() {
		logger.info("fresh ticket");
		String url ="https://api.weixin.qq.com/cgi-bin/token";
		String para = "grant_type=client_credential&appid="+WeChatConstant.AppId+"&secret="+WeChatConstant.AppSecred+"";
		String result = HttpService.sendGet(url,para);
		WeChatJsToken access_token = (WeChatJsToken) JsonUtil.validateJsonStr(result, WeChatJsToken.class);
		String token = access_token.getAccess_token()
;		//System.out.println(token);
		
		url ="https://api.weixin.qq.com/cgi-bin/ticket/getticket";
		para = "access_token="+token+"&type=jsapi";
		result = HttpService.sendGet(url,para);
		WeChatJsTicket access_ticket = (WeChatJsTicket) JsonUtil.validateJsonStr(result, WeChatJsTicket.class);
		String ticket = access_ticket.getTicket();
		jsapi_ticket = ticket;
		logger.info("ticket:"+ticket);
		last = System.currentTimeMillis()/1000;
		return ticket;
	}
	
	public static String getSignature(String url,String noncestr,String timestamp) {
        if(jsapi_ticket==null || System.currentTimeMillis()/1000-last>7000) {
        	getToken();
        }
        String str = "jsapi_ticket=" + jsapi_ticket +
                "&noncestr=" + noncestr +
                "&timestamp=" + timestamp +
                "&url=" + url;
        //sha1加密
        String signature = SHA1(str);
		return signature;
	}
	
	
    public static String SHA1(String str) {
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA-1"); //如果是SHA加密只需要将"SHA-1"改成"SHA"即可
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexStr = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexStr.append(0);
                }
                hexStr.append(shaHex);
            }
            return hexStr.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
