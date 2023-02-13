package com.example.message_channel.config;

import com.example.message_channel.ws.ChannelSocketHandler;
import com.example.message_channel.ws.MessageRoomSocketHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@EnableWebSocket
@Configuration
@AllArgsConstructor
public class SocketConfig implements WebSocketConfigurer {

    private ChannelSocketHandler channelSocketHandler;
    private MessageRoomSocketHandler messageRoomSocketHandler;

    private final static String SOCKET_PREFIX = "/sub";
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(channelSocketHandler, SOCKET_PREFIX + "/channels/");         //main channel
        registry.addHandler(messageRoomSocketHandler, SOCKET_PREFIX + "/chat/");

    }
}
