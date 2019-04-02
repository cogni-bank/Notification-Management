package com.cognibank.messagingservice.service;

import com.cognibank.messagingservice.model.Message;

/**
 * Interface to be used for sending the message.
 */
public interface MessagingService {

    String EMAIL_TYPE = "EMAIL";
    String PHONE_TYPE = "PHONE";
    String SUBJECT = "OTP for Authentication. Please don't share with anyone!";

    void sendMessage(Message message);
}