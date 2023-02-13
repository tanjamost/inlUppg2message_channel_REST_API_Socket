package com.example.message_channel.controller;

import com.example.message_channel.model.ChannelDetails;
import com.example.message_channel.service.ChannelService;
import com.example.message_channel.ws.ChannelSocketHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/channels/")
public class ChatChannelController {
    private ChannelService channelService;
    private ChannelSocketHandler channelSocketHandler;

    @GetMapping
    public ResponseEntity<List<ChannelDetails>> getChannels(){
        return ResponseEntity.ok(channelService.getChannels());
    }
    @PostMapping
    public ResponseEntity<ChannelDetails> createChannel(@RequestBody ChannelDetails channel){       //create=save

        ChannelDetails createdChannel = channelService.save(channel);
        channelSocketHandler.broadcast(createdChannel);
        return ResponseEntity.status(201).body(createdChannel);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<List<ChannelDetails>> deleteChannel(@PathVariable long id){
        channelService.delete(id);
        return getChannels();
    }
}
