package com.cognibank.messagingservice.service;

import com.cognibank.messagingservice.model.Message;
import com.cognibank.messagingservice.model.MessageForIF;

/**
 * Interface to be used for sending the message.
 */
public interface MessagingService {

    String EMAIL_TYPE = "EMAIL";
    String PHONE_TYPE = "PHONE";
    String SUBJECT = "OTP for Authentication. Please don't share with anyone!";
    String SUBJECTFORIF= "Your account has insufficient funds which might attract charges!";

    void sendMessage(Message message);
    void sendMessageForIF(MessageForIF message);
}