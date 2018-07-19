package com.aiwac.controller;



import java.net.URLEncoder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aiwac.bean.request.WeChatCode2Token;
import com.aiwac.constant.WeChatConstant;
import com.aiwac.model.WeChatUserInfo;
import com.aiwac.service.HttpService;
import com.aiwac.service.WechatUserInfoSqlService;
import com.aiwac.utils.EmojiFilter;
import com.aiwac.utils.JsonUtil;


/**
*
* @author zed
* @date 2018.4.20
*
*/

@Controller
public class WechatSignIn {
	
	@Autowired
	WechatUserInfoSqlService wechatUserInfoSqlService;
	
	private static final Logger logger = LogManager.getLogger(H5SignIn.class);
	
	@RequestMapping(value = { "login"},method = RequestMethod.GET)
	public String Wechatlogin() {
		return generateLoginUrl();
	}
	
	@RequestMapping(value = { "usersignup"},method = RequestMethod.GET)
	public String WechatSignin(
			@RequestParam(value="code",required=false) String code,
    		@RequestParam(value="state",required=false) String state) {
		System.out.println(code);
		if(code==null) {
			return generateLoginUrl();
		}
		else {
			String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
			String param = "appid="+WeChatConstant.AppId+"&secret="+WeChatConstant.AppSecred+"&code="+code+"&grant_type=authorization_code";
			String token_result = HttpService.sendGet(url, param);
			logger.info(token_result);
			if(token_result.startsWith("{\"errcode")) {  //出错重新登录
				return generateLoginUrl();
			}
			WeChatCode2Token token = (WeChatCode2Token) JsonUtil.validateJsonStr(token_result, WeChatCode2Token.class);
			
			url = "https://api.weixin.qq.com/sns/userinfo";
			param = "access_token="+token.getAccess_token()+"&openid="+token.getOpenid();
			String info_result = HttpService.sendGet(url, param);	
			logger.info(info_result);
			
			if(info_result.startsWith("{\"errcode")) {  //出错重新登录
				return generateLoginUrl();
			}
			WeChatUserInfo info = (WeChatUserInfo) JsonUtil.validateJsonStr(info_result, WeChatUserInfo.class);
			info.setNickname(EmojiFilter.filterEmoji(info.getNickname()));
			WeChatUserInfo get = wechatUserInfoSqlService.getUserInfoInSql(info);
			if(get!=null) {
				//wechatUserInfoSqlService.deleteUserInfoInSql(info.getOpenid());
				logger.info("the id exists!!!now update info");
				wechatUserInfoSqlService.updateUserInfoInSql(info);
			}
			else {
				wechatUserInfoSqlService.saveUserInfoInSql(info);
			}
			return "redirect:https://aiwac.net/resources/html5/html5/index/indexStandard.html?nickname="+info.getNickname()
			+"&sex="+info.getSex()+"&headimgurl="+info.getHeadimgurl()+"&open_id="+info.getOpenid();
		}
	}
	
	public String generateLoginUrl() {
		String url = URLEncoder.encode(WeChatConstant.RedictURL);
		String fullUrl = "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ WeChatConstant.AppId  +"&redirect_uri="
				+ url + "&response_type=code&scope=snsapi_userinfo&state=STATE";
		return fullUrl; 
	}
}