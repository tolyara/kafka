package com.example.kafka.rest;

import com.example.kafka.dto.message.MessageDto;
import com.example.kafka.service.micro.http.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("messages")
public class MessageController {

	private final MessageService messageService;

	@Autowired
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}

	@PostMapping
	public void sendMessage(@RequestBody MessageDto messageDto) {
		messageService.sendMessage(messageDto.getMessage());
	}

}