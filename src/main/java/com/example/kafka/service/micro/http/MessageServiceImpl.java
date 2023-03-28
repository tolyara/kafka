package com.example.kafka.service.micro.http;

import com.example.kafka.dto.message.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageServiceImpl implements MessageService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${base-server.host}")
    private String host;

    @Value("${base-server.port}")
    private String port;

    @Value("${base-server.protocol}")
    private String protocol;

    private final RestTemplate restTemplate;

    @Autowired
    public MessageServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public MessageResponse sendMessage(String message) {
        logger.info("Saving message : {}", message);
        MessageResponse response = restTemplate.getForObject(protocol + "://" + host + ":" + port + "/messages/{message}", MessageResponse.class, message);
//        Object response = restTemplate.getForObject("http://localhost:8080/messages/{message}", Object.class, message);
        return response;
    }

}
