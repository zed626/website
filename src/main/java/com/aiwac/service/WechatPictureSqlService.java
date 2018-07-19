package com.aiwac.service;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiwac.dao.WeChatPictureDao;
import com.aiwac.model.WeChatPicture;


/**
*
* @author WZR
* @date 2018年3月12日
*
*/

@Service
public class WechatPictureSqlService {
	private static final Logger logger = LogManager.getLogger(WechatPictureSqlService.class);
	
	@Autowired
	WeChatPictureDao weChatPictureDao;
	
	
	public void savePictureInSql(WeChatPicture WeChatPicture) {
		logger.info("savePictureInSql : " + WeChatPicture );
//		ApplicationContext ctx = ApplicationContextUtils.getApplicationContext();
//		WeChatPictureDao WeChatPictureDao = (WeChatPictureDao) ctx.getBean("WeChatPictureDao");
		weChatPictureDao.addPicture(WeChatPicture);
	}
	
	
	public void updatePictureInSql(WeChatPicture WeChatPicture) {
		logger.info("updatePictureInSql :" + WeChatPicture );
//		ApplicationContext ctx = ApplicationContextUtils.getApplicationContext();
//		WeChatPictureDao WeChatPictureDao = (WeChatPictureDao) ctx.getBean("WeChatPictureDao");
		weChatPictureDao.addPicture(WeChatPicture);
	}
	
	public List<WeChatPicture> getPictureInSql(int sceneId) {
		logger.info("getPictureInSql :" + sceneId );
//		ApplicationContext ctx = ApplicationContextUtils.getApplicationContext();
//		WeChatPictureDao WeChatPictureDao = (WeChatPictureDao) ctx.getBean("WeChatPictureDao");
		return weChatPictureDao.getPictureWithSceneId(sceneId);
	}
		
}
