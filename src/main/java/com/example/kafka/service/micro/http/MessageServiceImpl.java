package com.example.kafka.service.micro.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageServiceImpl implements MessageService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${m}")
    private String host;

    @Value("${m}")
    private String port;

    @Value("${m}")
    private String protocol;

    private final RestTemplate restTemplate;

    @Autowired
    public MessageServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Object sendMessage(String message) {
        logger.info("Saving message : {}", message);
        Object response = restTemplate.getForObject("http://localhost:8080/messages/{message}", Object.class, message);
        return response;
    }

}
