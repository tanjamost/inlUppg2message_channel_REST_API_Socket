package com.example.message_channel.ws;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MessageRoomSocketHandler extends TextWebSocketHandler {

    private Map<String, List<WebSocketSession>> sessions = new HashMap<>();         //session list for varje chat

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        broadcast(session.getHandshakeHeaders().getFirst(" id "), message.getPayload(), session);       //broadcast
    }

    public void broadcast(String channel, String message, WebSocketSession webSocketSession) {
        try{
            for(WebSocketSession webSession : sessions.get(channel) ){                              //broadcast medd for deltagare
                if(!webSocketSession.equals(webSession)){
                   webSession.sendMessage(new TextMessage(message));
                }
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String id = session.getHandshakeHeaders().getFirst("id");
        List<WebSocketSession> list = sessions.get(id);
        if(list == null){
            list = new ArrayList<>();
        }
        list.add(session);
        sessions.put(id, list );
        System.out.println(" CREATED NEW SESSION ");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String id = session.getHandshakeHeaders().getFirst("id");
        List<WebSocketSession> list = sessions.get(id);
        list.remove(session);
        System.out.println(" DELETED SESSION " );
    }



}
