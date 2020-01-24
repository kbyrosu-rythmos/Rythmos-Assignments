package com.Rythmos.RabbitMqProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Rythmos.RabbitMqProject.service.RabbitMqMessageConsumer;

@RestController
@RequestMapping(value = "/rythmos-rabbitmq/header/")
public class ConsumerController {

	@Autowired
	private RabbitMqMessageConsumer rabbitMqMessageConsumer;

	@GetMapping(value = "/consumer")
	public List<String> producer() {
		return rabbitMqMessageConsumer.ls;

	}
}