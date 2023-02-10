package com.example.message_channel.controller;

import com.example.message_channel.model.ChannelDetails;
import com.example.message_channel.service.ChannelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/channels")
public class ChatChannelController {
    private ChannelService channelService;

    @GetMapping
    public ResponseEntity<List<ChannelDetails>> getChannels(){
        return ResponseEntity.ok(channelService.getChannels());
    }
    @PostMapping
    public ResponseEntity<ChannelDetails> createChannel(@RequestBody ChannelDetails channel){
        return ResponseEntity.status(201).body(channelService.save(channel));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<List<ChannelDetails>> deleteChannel(@PathVariable long id){
        channelService.delete(id);
        return getChannels();
    }
}
