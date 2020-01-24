package com.Rythmos.RabbitMqProject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqMessageConsumer {

	public List<String> ls = new ArrayList<String>();

	@RabbitListener(queues = "${Rythmos.rabbitmq.queue1}")
	public void recievedMessageRegion1(String messageDataRegion1) {
		System.out.println("Recieved Message From Queue USA: " + messageDataRegion1);
		ls.add(messageDataRegion1);
	}

	@RabbitListener(queues = "${Rythmos.rabbitmq.queue2}")
	public void recievedMessageRegion2(String messageDataRegion2) {
		System.out.println("Recieved Message From Queue INDIA: " + messageDataRegion2);
		ls.add(messageDataRegion2);
	}

	@RabbitListener(queues = "${Rythmos.rabbitmq.queue3}")
	public void recievedMessageRegion3(String messageDataRegion3) {
		System.out.println("Recieved Message From Queue AUSTRALIA: " + messageDataRegion3);
		ls.add(messageDataRegion3);
	}

}
