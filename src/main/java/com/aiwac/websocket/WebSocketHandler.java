package com.aiwac.websocket;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import com.aiwac.constant.BusinessConstant;
import com.aiwac.service.HandleBusinessService;
import com.aiwac.utils.SendMessageUtil;
/**
*
* @author HC
* @date 2017年10月31日
*
*/

@Component
public class WebSocketHandler extends AbstractWebSocketHandler {
	private static final Logger logger = LogManager.getLogger(WebSocketHandler.class);
	private static final Map<String, WebSocketSession> users = new ConcurrentHashMap<>();
	private static final Map<String, String> partners = new ConcurrentHashMap<>();
	
	@Autowired
	private HandleBusinessService handleBusinessService;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String userId = session.getId();
		String userNumber = session.getHandshakeHeaders().getFirst("number");
    	logger.info("user login, user id: {} with user number: {}", userId, userNumber);
        users.put(userNumber, session);
        /*String partnerNumber = session.getHandshakeHeaders().getFirst("partner");
        if(partnerNumber!="null" && users.containsKey(partnerNumber)) {
        	partners.put(userNumber,partnerNumber);
        	partners.put(partnerNumber,userNumber);
        }
        */
        //session.sendMessage(new TextMessage("hello ,websocket"));
        
        /*if (userNumber.startsWith(BusinessConstant.AIWAC_CHAT_TRACK_USER_PREFIX)) {
        	String[] userNumberArray = userNumber.split("\\.");
        	applicationInfoAggregate.addAiwacChatTrackUser(userNumberArray[1]);
        	applicationInfoAggregate.addAiwacChatTrackUser(userNumber);
        } else {
        	pushRetransmissionService.handleBusiness(userNumber, null);
		}*/
    }
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		String userNumber = session.getHandshakeHeaders().getFirst("number");
		logger.info("user {} close connection", userNumber);
        users.remove(userNumber);
        String partner = partners.get(userNumber);
        partners.remove(userNumber);
        if(partners.containsKey(partner)){
        	partners.remove(partner);
        }
    }
	
	@Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String userNumber = session.getHandshakeHeaders().getFirst("number");
		String msg = message.getPayload();
        logger.info("收到{}的消息{}", userNumber,msg);
        
        //handleBusinessService.dispatchBusiness(userNumber, msg);
    }
	
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		String userNumber = session.getHandshakeHeaders().getFirst("number");
		//applicationInfoAggregate.removeAiwacChatPairUsers(userNumber);
		if (session.isOpen()) {
        	session.close();
        }
		logger.info("user {} close connection", userNumber);
        users.remove(userNumber);
    }

	@Override
    public boolean supportsPartialMessages() {
        return false;
    }
    
    public Map<String, WebSocketSession> getAllOnlineUsers() {
    	return Collections.unmodifiableMap(users);
    }
    
    public String getPartner(String user) {
    	return partners.get(user);
    }
    
    public WebSocketSession getWebSocketSession(String userNumber) {
    	WebSocketSession webSocketSession = users.get(userNumber);
    	return webSocketSession;
    }
    
    /**
     * send a message to someone
     *
     * @param userNumber
     * @param bean
     */
    /*
    public boolean sendMessageToUser(String userNumber, AbstractSocketBean bean) {
		WebSocketSession session = users.get(userNumber);
		return SendMessageUtil.sendMessage2User(session, bean, userNumber);
    }
    */
    /**
     * send a message to someone
     *
     * @param userNumber
     * @param bean
     */
    /*
    public boolean sendMessageToUser(String userNumber, AbstractWeChatBean bean) {
		WebSocketSession session = users.get(userNumber);
		return SendMessageUtil.sendMessage2User(session, bean, userNumber);
    }
    */
    
    /**
     * send a message to someone
     *
     * @param userNumber
     * @param message
     */
    public boolean sendMessageToUser(String userNumber, String message) {
		WebSocketSession session = users.get(userNumber);
		return SendMessageUtil.sendMessage2User(session, message);
    }
}
