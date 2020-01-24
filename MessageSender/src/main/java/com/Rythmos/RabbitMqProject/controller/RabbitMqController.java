package com.Rythmos.RabbitMqProject.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rythmos-rabbitmq/header/")
public class RabbitMqController {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@GetMapping(value = "/producer")
	public Message producer(@RequestParam("regionCode") String regionCode,
			@RequestParam("messageData") String messageData) {

		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setHeader("regionCode", regionCode);
		MessageConverter messageConverter = new SimpleMessageConverter();
		Message message = messageConverter.toMessage(messageData, messageProperties);
		amqpTemplate.send("header-exchange", "", message);

		return message;
	}
}