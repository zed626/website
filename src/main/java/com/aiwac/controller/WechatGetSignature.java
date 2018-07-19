package com.aiwac.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.aiwac.service.WeChatJsapiService;


/**
*
* @author zed
* @date 2018.4.20
*
*/

@Controller
public class WechatGetSignature {
	
	private static final Logger logger = LogManager.getLogger(WechatGetSignature.class);
	
	
	@ResponseBody
	@RequestMapping(value = { "signature"},method = RequestMethod.GET)
	public String WechatSignin(
		@RequestParam(value="url",required=true) String url) throws UnsupportedEncodingException {
		url = URLDecoder.decode(url, "UTF-8");
		logger.info("url_decode:"+url);
		String noncestr = UUID.randomUUID().toString();
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String signature = WeChatJsapiService.getSignature(url, noncestr, timestamp);
        logger.info("signature:"+signature);
		return "{"+"\"noncestr\":\""+noncestr+"\",\"timestamp\":\""+timestamp+"\",\""+"signature\":\""+signature+"\"}";
	}
}