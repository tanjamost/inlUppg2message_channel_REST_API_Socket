package com.example.message_channel.repository;

import com.example.message_channel.model.ChannelDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface JpaChannelRepository extends JpaRepository <ChannelDetails,Long> {
}
