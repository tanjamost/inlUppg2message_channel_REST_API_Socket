package com.example.message_channel.config;

import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

public class SocketConfig implements WebSocketConfigurer {

    private final static String SOCKET_PREFIX = "/sub";
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

    }
}
