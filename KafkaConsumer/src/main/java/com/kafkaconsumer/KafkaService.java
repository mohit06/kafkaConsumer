package com.kafkaconsumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafkaproducer.Product;

@Service
public class KafkaService {

	static int x =0;
	@KafkaListener(topics = "quickstart-events-data1", groupId = "my-group-id")
	public void listen(Product message) {
		System.out.println("Received message:---> " + message.toString());
		System.out.println("x ===>" + x);
		x++;
	}

}
