package com.example.message_channel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class ChannelDetails {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    private String title;
}
