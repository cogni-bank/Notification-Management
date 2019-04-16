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
//import com.twilio.Twilio;
import org.springframework.core.env.Environment;

@SpringBootApplication
@ComponentScan(basePackages = "com.cognibank.messagingservice")
public class TwoFactorAuthenticationApplication {

	@Autowired
	private Environment env;

	/*@Bean
	Queue queue() {
		return QueueBuilder.durable(env.getProperty("spring.rabbitmq.api.queueName.otp")).build();
	}

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(env.getProperty("spring.rabbitmq.api.directExchangeName"), true, false);
	}

	@Bean
	Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(env.getProperty("spring.rabbitmq.api.routingKey.otp"));
	}

	@Bean
	Queue queue1() {
		return QueueBuilder.durable(env.getProperty("spring.rabbitmq.api.queueName.insufficient")).build();
	}

	@Bean
	Binding binding1(DirectExchange exchange) {
		return BindingBuilder.bind(queue1()).to(exchange).with(env.getProperty("spring.rabbitmq.api.routingKey.insufficient"));
	}*/

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
											 MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(env.getProperty("spring.rabbitmq.api.queueName.otp"));
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	SimpleMessageListenerContainer containerForIF(ConnectionFactory connectionFactory,
											 MessageListenerAdapter listenerAdapterForIF) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(env.getProperty("spring.rabbitmq.api.queueName.insufficient"));
		container.setMessageListener(listenerAdapterForIF);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(MessagingController receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	@Bean
	MessageListenerAdapter listenerAdapterForIF(MessagingController receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessageForIF");
	}

	@Bean
	void initializeTwilio () {
//		Twilio.init(env.getProperty("spring.twilio.api.account_sid"), env.getProperty("spring.twilio.api.auth_id"));
	}

	public static void main(String[] args) {
		SpringApplication.run(TwoFactorAuthenticationApplication.class, args);
	}


}