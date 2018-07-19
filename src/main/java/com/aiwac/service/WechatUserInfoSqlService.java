package com.aiwac.service;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiwac.dao.WeChatUserInfoDao;
import com.aiwac.model.WeChatUserInfo;


/**
*
* @author WZR
* @date 2018年3月12日
*
*/

@Service
public class WechatUserInfoSqlService {
	private static final Logger logger = LogManager.getLogger(WechatUserInfoSqlService.class);
	
	@Autowired
	WeChatUserInfoDao weChatUserInfoDao;
	
	
	public void saveUserInfoInSql(WeChatUserInfo WeChatUserInfo) {
		logger.info("saveUserInfoInSql : " + WeChatUserInfo );
//		ApplicationContext ctx = ApplicationContextUtils.getApplicationContext();
//		WeChatUserInfoDao WeChatUserInfoDao = (WeChatUserInfoDao) ctx.getBean("WeChatUserInfoDao");
		weChatUserInfoDao.addUser(WeChatUserInfo);
	}
	
	
	public void updateUserInfoInSql(WeChatUserInfo WeChatUserInfo) {
		logger.info("updateUserInfoInSql :" + WeChatUserInfo );
//		ApplicationContext ctx = ApplicationContextUtils.getApplicationContext();
//		WeChatUserInfoDao WeChatUserInfoDao = (WeChatUserInfoDao) ctx.getBean("WeChatUserInfoDao");
		weChatUserInfoDao.updateUser(WeChatUserInfo);
	}
	
	public WeChatUserInfo getUserInfoInSql(WeChatUserInfo WeChatUserInfo) {
		logger.info("getUserInfoInSql :" +WeChatUserInfo);
//		ApplicationContext ctx = ApplicationContextUtils.getApplicationContext();
//		WeChatUserInfoDao WeChatUserInfoDao = (WeChatUserInfoDao) ctx.getBean("WeChatUserInfoDao");
		return weChatUserInfoDao.getUserWithId(WeChatUserInfo);
	}
	
	public void deleteUserInfoInSql(String openid) {
		logger.info("deleteUserInfoInSql :" +openid);
//		ApplicationContext ctx = ApplicationContextUtils.getApplicationContext();
//		WeChatUserInfoDao WeChatUserInfoDao = (WeChatUserInfoDao) ctx.getBean("WeChatUserInfoDao");
		weChatUserInfoDao.deleteUser(openid);
	}
		
}
