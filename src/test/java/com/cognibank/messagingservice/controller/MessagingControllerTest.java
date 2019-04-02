package com.cognibank.messagingservice.controller;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.cognibank.messagingservice.TwoFactorAuthentication.TwoFactorAuthenticationApplication.class)
public class MessagingControllerTest {

    @Autowired
    private Environment env;
    private CachingConnectionFactory connectionFactory;
    private RabbitTemplate template;

    @Before
    public void createConnection() {
        connectionFactory = new CachingConnectionFactory();
        assertTrue("Should be able to create a connection with the Rabbit MQ server.",
                connectionFactory != null);
        template = new RabbitTemplate(connectionFactory);
        assertTrue("Should be able to create a Rabbit template from the connection.",
                template != null);
    }

    @Test
    @Ignore
    public void testForSendMessageToNotificationQueueWithEmail() {
        final byte[] producedMessage = "{email:'abcabc@gmail.com', code:123456, type:'EMAIL'}".getBytes();
        template.convertAndSend(env.getProperty("spring.rabbitmq.api.directExchangeName"), env.getProperty("spring.rabbitmq.api.routingKey"), producedMessage);
        connectionFactory.destroy();
    }

    @Test
    @Ignore
    public void testForSendMessageToNotificationQueueWithPhone() {
        final byte[] producedMessage = "{phone:'+10123456789', code:123456, type:'PHONE'}".getBytes();
        template.convertAndSend(env.getProperty("spring.rabbitmq.api.directExchangeName"), env.getProperty("spring.rabbitmq.api.routingKey"), producedMessage);
        connectionFactory.destroy();
    }
}