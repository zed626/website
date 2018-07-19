package com.aiwac.controller;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiwac.bean.request.WeChatSceneSum;
import com.aiwac.model.WeChatPicture;
import com.aiwac.service.WechatPictureSqlService;
import com.aiwac.service.WechatSceneSqlService;
import com.aiwac.tool.DateUtils;
import com.aiwac.tool.UuidUtils;
import com.aiwac.utils.JsonUtil;


/**
*
* @author zed
* @date 2018.6.20
*
*/

@Controller
public class WechatGetPictures {
	
	@Autowired
	WechatPictureSqlService wechatPictureSqlService;
	private static final Logger logger = LogManager.getLogger(WechatGetPictures.class);
	
	
	@ResponseBody
	@RequestMapping(value = { "picture"},method = RequestMethod.GET)
	public String WechatSignin(@RequestParam(value="scene",required=true) int scene)
	{
		List<WeChatPicture> list= wechatPictureSqlService.getPictureInSql(scene);
		logger.info("return scens:"+list);
		return JsonUtil.writeValueAsString(list);
	}
}