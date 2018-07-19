package com.aiwac.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiwac.dao.WeChatBusinessDao;
import com.aiwac.model.WeChatBusinessSql;;


/**
*
* @author WZR
* @date 2018年3月12日
*
*/

@Service
public class WechatBusinessSqlService {
	private static final Logger logger = LogManager.getLogger(WechatBusinessSqlService.class);
	
	@Autowired
	WeChatBusinessDao weChatBusinessDao;
	
	public void saveBusinessInSql(String business_id,String u_id,int s_id,String photopath,int score,String platform,int seriel,int realemotion,int expectemotion) {
		WeChatBusinessSql weChatBusiness = new WeChatBusinessSql();
		weChatBusiness.setBusiness_id(business_id);
		weChatBusiness.setU_id(u_id);
		weChatBusiness.setS_id(s_id);
		weChatBusiness.setPhotopath(photopath);
		weChatBusiness.setScore(score);
		weChatBusiness.setPlatform(platform);
		weChatBusiness.setSeriel(seriel);
		weChatBusiness.setRealemotion(realemotion);
		weChatBusiness.setExpectemotion(expectemotion);
		saveBusinessInSql(weChatBusiness);
	}
	
	public void saveBusinessInSql(WeChatBusinessSql WeChatBusiness) {
		logger.info("saveBusinessInSql : " + WeChatBusiness );
//		ApplicationContext ctx = ApplicationContextUtils.getApplicationContext();
//		WeChatBusinessDao WeChatBusinessDao = (WeChatBusinessDao) ctx.getBean("WeChatBusinessDao");
		weChatBusinessDao.addBusiness(WeChatBusiness);
	}
	
	
	public void updateBusinessInSql(WeChatBusinessSql WeChatBusiness) {
		logger.info("updateBusinessInSql :" + WeChatBusiness );
//		ApplicationContext ctx = ApplicationContextUtils.getApplicationContext();
//		WeChatBusinessDao WeChatBusinessDao = (WeChatBusinessDao) ctx.getBean("WeChatBusinessDao");
		weChatBusinessDao.addBusiness(WeChatBusiness);
	}
		
}
