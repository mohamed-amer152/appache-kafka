package com.amer.spring.cloud.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.JacksonUtils;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.amer.spring.cloud.kafka.user;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableKafka
@Configuration
public class kafkaConfigurar {
	
	// for string data 
	@Bean
	public ConsumerFactory<String, String> stringconsumer()
	{
		
		Map<String, Object> configs = new HashMap<>();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(JsonDeserializer.TRUSTED_PACKAGES , "*");
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, "amer10");
		return new DefaultKafkaConsumerFactory<String , String>(configs , new StringDeserializer() , new StringDeserializer());
		
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String,String> stringlistner()
	{
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String ,String>();
		factory.setConsumerFactory(stringconsumer());
		return factory;
	}
	// for raw data 
	@Bean
	public ConsumerFactory<String , user> rawconsumer()
	{
		Map<String, Object> configs = new HashMap<>();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, "amer2");
		return new DefaultKafkaConsumerFactory<>(configs , new StringDeserializer() , new JsonDeserializer<>(user.class));
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String , user> rawlistner()
	{
		ConcurrentKafkaListenerContainerFactory<String, user> factory = new ConcurrentKafkaListenerContainerFactory<String , user>();
		factory.setConsumerFactory(rawconsumer());
		return factory;
	}
	
	
	
	
	

}
