package com.aiwac.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
*
* @author HC
* @date 2017年10月31日
*
*/

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	
	@Autowired
	private WebSocketHandler webSocketHandler;
	
	@Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler,"/websocket/socketServer").addInterceptors(new WebSocketHandlerInterceptor());
        registry.addHandler(webSocketHandler, "/sockjs/socketServer").addInterceptors(new WebSocketHandlerInterceptor()).setAllowedOrigins("*");
    }

}
