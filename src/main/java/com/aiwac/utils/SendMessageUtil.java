package com.aiwac.utils;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.aiwac.bean.AbstractSocketBean;
import com.aiwac.bean.AbstractWeChatBean;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
*
* @author HC
* @date 2017年11月6日
*
*/

public class SendMessageUtil {
	private static final Logger logger = LogManager.getLogger(SendMessageUtil.class);
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	public static boolean sendMessage2User(WebSocketSession session, AbstractSocketBean bean, String userNumber) {
		try {
	    	String response = objectMapper.writeValueAsString(bean);
	    	return sendMessage2User(session, response);
		} catch(Exception e) {
			logger.error("send message to user {} error!", userNumber);
			LoggerUtil.LogException(logger, e);
		}
		return false;
	}
	
	
	public static boolean sendMessage2User(WebSocketSession session, AbstractWeChatBean bean, String userNumber) {
		try {
	    	String response = objectMapper.writeValueAsString(bean);
	    	return sendMessage2User(session, response);
		} catch(Exception e) {
			logger.error("send message to user {} error!", userNumber);
			LoggerUtil.LogException(logger, e);
		}
		return false;
	}
	
	public static boolean sendMessage2User(WebSocketSession session, String msg) {
		try {
			TextMessage textMessage = new TextMessage(msg);
			return sendMessage2User(session, textMessage);
		} catch (IOException e) {
			logger.error("send message to user {} error!", session.getHandshakeHeaders().getFirst("number"));
			LoggerUtil.LogException(logger, e);
		}
		return false;
	}
	
	public static boolean sendMessage2User(WebSocketSession session, TextMessage textMsg) throws IOException {
    	try {
    		if (session != null && session.isOpen()) {
    			logger.info("发送消息给{}", session.getHandshakeHeaders().getFirst("number"));
    			session.sendMessage(textMsg);
    			return true;
    		} else {
    			if (session == null) {
    				logger.warn("send message to an offline user!");
    			} else {
    				logger.warn("user {} is offline!", session.getHandshakeHeaders().getFirst("number"));
    			}
    		}
		} catch (IOException e) {
			throw e;
		}
    	return false;
	}
}
