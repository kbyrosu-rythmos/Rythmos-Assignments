package com.Rythmos.RabbitMqProject.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

	@Bean
	Queue USA() {
		return new Queue("USA", false);
	}

	@Bean
	Queue INDIA() {
		return new Queue("INDIA", false);
	}

	@Bean
	Queue AUSTRALIA() {
		return new Queue("AUSTRALIA", false);
	}

	@Bean
	HeadersExchange headerExchange() {
		return new HeadersExchange("header-exchange");
	}

	@Bean
	Binding Region1Binding(Queue USA, HeadersExchange headerExchange) {
		return BindingBuilder.bind(USA).to(headerExchange).where("regionCode").matches("Region1");
	}

	@Bean
	Binding Region2Binding(Queue INDIA, HeadersExchange headerExchange) {
		return BindingBuilder.bind(INDIA).to(headerExchange).where("regionCode").matches("Region2");
	}

	@Bean
	Binding Region3Binding(Queue AUSTRALIA, HeadersExchange headerExchange) {
		return BindingBuilder.bind(AUSTRALIA).to(headerExchange).where("regionCode").matches("Region3");
	}

}