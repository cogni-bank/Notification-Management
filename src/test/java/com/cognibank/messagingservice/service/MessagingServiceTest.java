package com.cognibank.messagingservice.service;

import com.cognibank.messagingservice.model.Message;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.cognibank.messagingservice.TwoFactorAuthentication.TwoFactorAuthenticationApplication.class)
public class MessagingServiceTest {

    @Autowired
    private Environment env;

    private MessagingService messagingService;

    @Before
    public void beforeEachTestMethod() {
        messagingService = new MessagingServiceImplementation();
    }

    @Test
    public void testForMessagingServiceObjectIsCreated() {
        assertNotNull("Should be able to create messaging service implementation object!", messagingService);
    }

    @Test
    public void testForMessageObjectIsCreated() {
        Message messageToBeParsed = new Message().withEmail("abcabc@gmail.edu").withCode(123456).withType(MessagingService.EMAIL_TYPE);
        assertNotNull("Should be able to create message object!", messageToBeParsed);
        assertEquals("Email should be matched.", "abcabc@gmail.edu", messageToBeParsed.getEmail());
        assertEquals("Code should be matched.", 123456, messageToBeParsed.getCode());
        assertEquals("Type should be matched.", MessagingService.EMAIL_TYPE, messageToBeParsed.getType());
    }

    @Test
    @Ignore
    public void testForSendingMessage() {
        Message messageToBeParsed = new Message().withEmail("abcabc@gmail.edu").withCode(123456).withType(MessagingService.EMAIL_TYPE);
        messagingService.sendMessage(messageToBeParsed);
    }

}
