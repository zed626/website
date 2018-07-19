package com.aiwac.utils;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
*
* @author HC
* @date 2017年11月6日
*
*/

public class JsonUtil {
	private static final Logger logger = LogManager.getLogger(JsonUtil.class);
	
	public static Object validateJsonStr(String jsonStr, Class<?> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    	mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    	
    	Object bean = null;
    	try {
			bean = mapper.readValue(jsonStr, clazz);
		} catch (IOException e) {
			logger.error("validate json error! error message: {}", e.getMessage());
		}
    	
		return bean;
	}
	
	public static String writeValueAsString(Object bean) {
		ObjectMapper mapper = new ObjectMapper();
		String result = "";
		try {
			result = mapper.writeValueAsString(bean);
		} catch (JsonProcessingException e) {
			LoggerUtil.LogException(logger, e);
		}
		return result;
	}
}
