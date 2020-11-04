package com.example.kafka.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("msg")
public class MsgController {

	@Autowired
	private KafkaTemplate<Long, String> kafkaTemplate;

//	@PostMapping
//	public void sendMessage(Long msgId, String msg) {
//		ListenableFuture<SendResult<Long, String>> future = kafkaTemplate.send("msg", msgId, msg);
//	    future.addCallback(System.out::println, System.err::println);
//	    kafkaTemplate.flush();
//	}
	
	@PostMapping
	public void sendMessage(@RequestParam("msgId") Long msgId, @RequestParam("msg") String msg) {
//	public void sendMessage(@RequestBody UserDTO user) {
		
//		user.setAddress(new Address("RUS", "Msk", "Lenina", 1L, 1L));
//		msg = "test";
		ListenableFuture<SendResult<Long, String>> future = kafkaTemplate.send("msg", msgId, msg);
	    future.addCallback(System.out::println, System.err::println);
	    kafkaTemplate.flush();
	}

	@GetMapping
	public void test() {
		System.out.println("Test success"); 
	}
}