package com.cognibank.messagingservice.controller;

import com.cognibank.messagingservice.model.Message;
import com.cognibank.messagingservice.service.MessagingServiceImplementation;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Controller component for handling the messages produced in the queue.
 */
@Component
public class MessagingController {

    @Autowired
    private MessagingServiceImplementation messagingService;

    /** +
     * This function is called when the message is produced in the queue and the application is running.
     *
     * @param receivedMessage    A byte array in which we will receive the message.
     */
    public void receiveMessage(byte[] receivedMessage) {
        System.out.println("Consumed Message <" + receivedMessage.toString() + ">");
        // Need to parse the received message as a JSON object using GSON library.
        final Gson gson = new Gson();
        // Message received should be in json format.
        final Message message = gson.fromJson(new String(receivedMessage), Message.class);
        System.out.println("JSON Received <" + message + ">");
        // Service method call for processing the message.
        messagingService.sendMessage(message);
    }

}