package com.example.message_channel.ws;

import com.example.message_channel.model.ChannelDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChannelSocketHandler extends TextWebSocketHandler {

    private List<WebSocketSession> sessions = new ArrayList<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        broadcast(message.getPayload(), session);
    }

    public void broadcast(String message, WebSocketSession webSocketSession){
        try {
            for (WebSocketSession webSession :sessions) {
                if(!webSocketSession.equals(webSession)) {
                    webSession.sendMessage(new TextMessage(message));
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void broadcast(ChannelDetails channelDetails){
       try {
           for (WebSocketSession webSession : sessions){

               {
                    webSession.sendMessage(new TextMessage("Channel" + channelDetails.getTitle() + " created channel with id:  " + channelDetails.getId()));       //channel created
               }
           }
       } catch(IOException ex){
            ex.printStackTrace();
       }

    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {      //create session
        sessions.add(session);
        System.out.println(" Created new session ");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status){        //delete session
        sessions.remove(session);
        System.out.println(" Deleted session ");

    }

}
