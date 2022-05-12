package com.amer.spring.cloud.kafka;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApacheKafkaConsumerApplication {
	List<String> messages = new ArrayList<>();

	user userFromTopic = null;

	@GetMapping("/consumeStringMessage")
	public List<String> consumeMsg() {
		return messages;
	}

	@GetMapping("/consumeJsonMessage")
	public user consumeJsonMessage() {
		return userFromTopic;
	}

	@KafkaListener(groupId = "amer10", topics = "mohamed", containerFactory = "stringlistner")
	public List<String> getMsgFromTopic(String data) {
		messages.add(data);
		return messages;
	}

	@KafkaListener(groupId = "amer2", topics = "mohamed", containerFactory = "rawlistner")
	public user getJsonMsgFromTopic(user user1) {
		userFromTopic = user1;
		return userFromTopic;
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(ApacheKafkaConsumerApplication.class, args);
	}

}
