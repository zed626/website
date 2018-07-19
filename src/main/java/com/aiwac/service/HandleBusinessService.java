package com.aiwac.service;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiwac.utils.LoggerUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
*
* @author HC
* @date 2017年11月1日
*
*/

@Service
public class HandleBusinessService {
	private static final Logger logger = LogManager.getLogger(HandleBusinessService.class);
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	
/*
	
	public void dispatchBusiness(String userNumber, String businessJsonStr) {
		String businessType = parseBusinessType(businessJsonStr);
		BusinessService businessService = getBusinessService(userNumber, businessType);
		long startHandleBusinessTime = System.currentTimeMillis();
		
		if (businessService != null) {
			logger.info("start handle business for user {}", userNumber);
			businessService.handleBusiness(userNumber, businessJsonStr);
		} else {
			logger.error("invalid business service from user: {}", userNumber);
		}
		long finishHandleBusinessTime = System.currentTimeMillis();
		logger.info("finish handle business for user {}, use time: {} ms", userNumber,
				(finishHandleBusinessTime - startHandleBusinessTime));
	}
	*/
	
	private String parseBusinessType(String businessJsonStr) {
		JsonNode node;
		try {
			node = OBJECT_MAPPER.readTree(businessJsonStr);
			JsonNode businessType = node.get("businessType");
			logger.info("business type: {}", businessType);
			return businessType.toString().replace("\"", "");
		} catch (Exception e) {
			LoggerUtil.LogException(logger, e);
		}
		return "";
	}
	
	/*
	private BusinessService getBusinessService(String userNumber, String businessType) {
		BusinessService businessService = null;
		
		switch (businessType) {
			case "0001":
				businessService = weChatSignInService;
				break;
			case "0002":
				businessService = weChatEmotionCompareSevice;
				break;
			default:
				logger.error("unknown businesstype: {}", businessType);
		}
		return businessService;
	}
	*/
	
}
