package com.example.kafka.service.micro.http;

import com.example.kafka.dto.message.MessageDto;
import com.example.kafka.dto.message.MessageResponse;

public interface MessageService {

    MessageResponse sendMessage(MessageDto message);

}
