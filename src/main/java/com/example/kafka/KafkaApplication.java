package com.example.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

import com.example.kafka.dto.UserDto;

@EnableKafka
@SpringBootApplication
public class KafkaApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(KafkaApplication.class);

	@KafkaListener(topics = "msg")
	public void msgListener(ConsumerRecord<Long, UserDto> record) {
		System.out.println("partition - " + record.partition());
		System.out.println("key - " + record.key());
		System.out.println("value - " + record.value());
	}

	public static void main(String[] args) {
		logger.debug("KafkaApplication started");
		SpringApplication.run(KafkaApplication.class, args);
	}

}
