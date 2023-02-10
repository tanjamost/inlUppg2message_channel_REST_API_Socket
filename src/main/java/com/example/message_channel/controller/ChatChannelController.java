package com.example.message_channel.controller;

import com.example.message_channel.model.ChannelDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/channels")
public class ChatChannelController {

    @GetMapping
    public ResponseEntity<List<ChannelDetails>> getChannels(){
        return null;
    }
    @PostMapping
    public ResponseEntity<ChannelDetails> createChannel(){
        return null;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<List<ChannelDetails>> removeChannel(@PathVariable long id){
        return null;
    }
}
