package com.example.message_channel.ws;

import com.example.message_channel.model.ChannelDetails;
import com.example.message_channel.service.ChannelService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ChannelSocketHandler extends TextWebSocketHandler {

    private final ChannelService channelService;              // "act channels"

    private List<WebSocketSession> sessions = new ArrayList<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        broadcast(message.getPayload(), session);
    }

    public void broadcast(String message, WebSocketSession webSocketSession){
        try {
            for (WebSocketSession webSession : sessions) {

                if(!webSocketSession.equals(webSession)) {
                    webSession.sendMessage(new TextMessage(message));
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void broadcast(ChannelDetails channel){               //all channels
       try {
           for (WebSocketSession webSession : sessions){

               {
                    webSession.sendMessage(new TextMessage("Channel " + channel.getTitle() + " was created with id:  " + channel.getId()));
               }
           }
       } catch(IOException ex){
            ex.printStackTrace();
       }

    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {      //create ses

        sessions.add(session);
        System.out.println(" Created new sub/channels session ");

        List<ChannelDetails> channels = channelService.getChannels();       //list created channels
        if(channels != null && channels.size() != 0 ){                      //channel exists

            try {
                session.sendMessage(new TextMessage("ACTIVE message channels"));

                for (ChannelDetails channel  : channels) {
                    session.sendMessage(new TextMessage(channel.toString()));
                }

            } catch (IOException e) {
                throw new RuntimeException(e);

            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status){        //delete ses
        sessions.remove(session);
        System.out.println(" Deleted session ");

    }

}
