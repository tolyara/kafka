package com.example.kafka.service.micro.rabbitmq;

import com.example.kafka.util.RabbitmqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

@Component
public class RabbitmqSender {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @PostConstruct
    private void init() {
        Scanner scanner = new Scanner(System.in);
        askForMessage(scanner);
    }

    private void sendMessage(String message) {
        ConnectionFactory factory = new ConnectionFactory();

        try (Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(RabbitmqUtil.EXCHANGE, RabbitmqUtil.TYPE);
            channel.basicPublish(RabbitmqUtil.EXCHANGE, RabbitmqUtil.ROUTING_KEY, false, null, message.getBytes());
        } catch (TimeoutException | IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void askForMessage(Scanner scanner) {
        logger.info("Enter your message : ");
        String message = scanner.nextLine();    // + LocalDateTime.now
        sendMessage(message);
        askForMessage(scanner);
    }

}
