package com.example.message_channel.service;

import com.example.message_channel.model.ChannelDetails;
import com.example.message_channel.repository.JpaChannelRepository;

import java.util.List;

public class ChannelService {

    public JpaChannelRepository jpaChannelRepository;

    public List<ChannelDetails> getChannelDetails(){
        return jpaChannelRepository.findAll();
    }

    public ChannelDetails save(ChannelDetails channelDetails){          //save=create
        return jpaChannelRepository.save(channelDetails);
    }

}
