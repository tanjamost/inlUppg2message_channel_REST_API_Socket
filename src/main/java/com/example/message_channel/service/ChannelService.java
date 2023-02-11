package com.example.message_channel.service;

import com.example.message_channel.model.ChannelDetails;
import com.example.message_channel.repository.JpaChannelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ChannelService {

    public JpaChannelRepository jpaChannelRepository;

    public List<ChannelDetails> getChannels(){
        return jpaChannelRepository.findAll();
    }

    public ChannelDetails save(ChannelDetails channelDetails){          //save=create
        return jpaChannelRepository.save(channelDetails);
    }

    public void delete(long id){
        jpaChannelRepository.deleteById(id);

    }

}
