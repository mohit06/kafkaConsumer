package com.kafkaconsumer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@SpringBootApplication
public class KafkaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}
	
	 @Bean
	    public ConsumerFactory<String, String> consumerFactory() {
	        Map<String, Object> configProps = new HashMap<>();
	        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group-id");
	        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
	        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, org.springframework.kafka.support.serializer.JsonDeserializer.class);
	        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
	        return new DefaultKafkaConsumerFactory<>(configProps);
	    }

	    @Bean
	    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
	        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
	        factory.setConsumerFactory(consumerFactory());
	        return factory;
	    }

}
