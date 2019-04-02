package com.cognibank.messagingservice.TwoFactorAuthentication;

import com.cognibank.messagingservice.controller.MessagingController;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import com.twilio.Twilio;
import org.springframework.core.env.Environment;

@SpringBootApplication
@ComponentScan(basePackages = "com.cognibank.messagingservice")
public class TwoFactorAuthenticationApplication {

	@Autowired
	private Environment env;

	@Bean
	Queue queue() {
		return QueueBuilder.durable(env.getProperty("spring.rabbitmq.api.queueName")).build();
	}

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(env.getProperty("spring.rabbitmq.api.directExchangeName"), true, false);
	}

	@Bean
	Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(env.getProperty("spring.rabbitmq.api.routingKey"));
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
											 MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(env.getProperty("spring.rabbitmq.api.queueName"));
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(MessagingController receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	@Bean
	void initializeTwilio () {
		Twilio.init(env.getProperty("spring.twilio.api.account_sid"), env.getProperty("spring.twilio.api.auth_id"));
	}

	public static void main(String[] args) {
		SpringApplication.run(TwoFactorAuthenticationApplication.class, args);
	}

}