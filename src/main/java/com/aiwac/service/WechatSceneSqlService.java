package com.aiwac.service;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiwac.dao.WeChatSceneDao;
import com.aiwac.model.WeChatScene;


/**
*
* @author WZR
* @date 2018年3月12日
*
*/

@Service
public class WechatSceneSqlService {
	private static final Logger logger = LogManager.getLogger(WechatSceneSqlService.class);
	
	@Autowired
	WeChatSceneDao weChatSceneDao;
	
	
	public void saveSceneInSql(WeChatScene WeChatScene) {
		logger.info("saveSceneInSql : " + WeChatScene );
//		ApplicationContext ctx = ApplicationContextUtils.getApplicationContext();
//		WeChatSceneDao WeChatSceneDao = (WeChatSceneDao) ctx.getBean("WeChatSceneDao");
		weChatSceneDao.addScene(WeChatScene);
	}
	
	
	public void updateSceneInSql(WeChatScene WeChatScene) {
		logger.info("updateSceneInSql :" + WeChatScene );
//		ApplicationContext ctx = ApplicationContextUtils.getApplicationContext();
//		WeChatSceneDao WeChatSceneDao = (WeChatSceneDao) ctx.getBean("WeChatSceneDao");
		weChatSceneDao.updateScene(WeChatScene);
	}
	
	public List<WeChatScene> getSceneInSql() {
		logger.info("getAllSceneInSql "  );
//		ApplicationContext ctx = ApplicationContextUtils.getApplicationContext();
//		WeChatSceneDao WeChatSceneDao = (WeChatSceneDao) ctx.getBean("WeChatSceneDao");
		return weChatSceneDao.getAllScene();
	}
	
	public List<WeChatScene> getAvailableSceneInSql() {
		logger.info("getAllSceneInSql "  );
//		ApplicationContext ctx = ApplicationContextUtils.getApplicationContext();
//		WeChatSceneDao WeChatSceneDao = (WeChatSceneDao) ctx.getBean("WeChatSceneDao");
		return weChatSceneDao.getAvailableScene();
	}
	
	public WeChatScene getSceneByNameInSql(WeChatScene weChatScene) {
		logger.info("getSceneBynameInSql "  );
//		ApplicationContext ctx = ApplicationContextUtils.getApplicationContext();
//		WeChatSceneDao WeChatSceneDao = (WeChatSceneDao) ctx.getBean("WeChatSceneDao");
		return weChatSceneDao.getSceneByName(weChatScene);
	}
	
	public int getSceneCountInSql() {
		logger.info("getSceneCountInSql "  );
//		ApplicationContext ctx = ApplicationContextUtils.getApplicationContext();
//		WeChatSceneDao WeChatSceneDao = (WeChatSceneDao) ctx.getBean("WeChatSceneDao");
		return weChatSceneDao.getSceneCount();
	}
	
	public List<WeChatScene> getSceneByPageInSql(int startrow,int pagesize) {
		logger.info("getSceneBypageInSql "  );
//		ApplicationContext ctx = ApplicationContextUtils.getApplicationContext();
//		WeChatSceneDao WeChatSceneDao = (WeChatSceneDao) ctx.getBean("WeChatSceneDao");
		return weChatSceneDao.getPageScene(startrow, pagesize);
	}
		
}
