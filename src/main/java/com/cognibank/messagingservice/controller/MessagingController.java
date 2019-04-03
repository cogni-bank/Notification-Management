package com.cognibank.messagingservice.controller;

import com.cognibank.messagingservice.model.Message;
import com.cognibank.messagingservice.model.MessageForIF;
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
        try {
            System.out.println("Consumed Message <" + receivedMessage.toString() + ">");
            //Convert the byte array to String.
            final String messageStr = new String(receivedMessage);
            System.out.println("messageStr:" + messageStr);
            // Need to parse the received message as a JSON object using GSON library.
            final Gson gson = new Gson();
            // Message received should be in json format.
            final Message message = gson.fromJson(messageStr, Message.class);
            System.out.println("JSON Received <" + message + ">");
            // Service method call for processing the message.
            messagingService.sendMessage(message);
        } catch (Exception ex) {
            System.out.println("Exception occured on the listener method for otp notification queue...") ;
            ex.printStackTrace();
        }
    }

    public void receiveMessageForIF(byte[] receivedMessage) {
        try {
            System.out.println("receiveMessage1 Consumed Message <" + receivedMessage.toString() + ">");
            // Need to parse the received message as a JSON object using GSON library.
            final Gson gson = new Gson();
            // Message received should be in json format.
            final MessageForIF message = gson.fromJson(new String(receivedMessage), MessageForIF.class);
            System.out.println("JSON Received <" + message + ">");
            // Service method call for processing the message.
            messagingService.sendMessageForIF(message);
        } catch (Exception ex) {
            System.out.println("Exception occured on the listener method for insufficient funds queue..." );
            ex.printStackTrace();
        }
    }
}