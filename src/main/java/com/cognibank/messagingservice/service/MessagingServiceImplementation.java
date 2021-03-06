package com.cognibank.messagingservice.service;

import com.cognibank.messagingservice.model.Message;
import com.cognibank.messagingservice.model.MessageForIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
//import com.twilio.type.PhoneNumber;

@Service
public class MessagingServiceImplementation implements MessagingService {

    @Autowired
    private Environment env;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMessage(final Message message) {
        // TODO: Need to replace the hard-coded string with configuration

        if(message != null && message.getType().equalsIgnoreCase(EMAIL_TYPE)) {
            sendAnEmailToUser(message);
        } else if(message.getType().equalsIgnoreCase(PHONE_TYPE)) {
            sendASmsToUser(message);
        }
    }

    @Override
    public void sendMessageForIF(final MessageForIF messageForIF){

        if(messageForIF != null && messageForIF.getType().equalsIgnoreCase(EMAIL_TYPE)){
            sendEMailNotificationForIF(messageForIF);
        }else if(messageForIF.getType().equalsIgnoreCase(PHONE_TYPE)) {
            //sendASmsToUser(messageForIF);
        }

    }

    void sendEMailNotificationForIF(final MessageForIF messageForIF){
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(messageForIF.getEmail());
            mailMessage.setFrom("notify@cognibank.name");
            mailMessage.setSubject(SUBJECTFORIF);
            mailMessage.setText(getEmailTextForIF(messageForIF.getAccountBalance()));
            // Java Mail API for sending the email.
            javaMailSender.send(mailMessage);
        } catch (Exception exception) {
            System.out.println("Exception has occurred while sending the email...Exception class is " + exception);
        }
    }


    /**
     *
     * @param message
     */
    private void sendAnEmailToUser(Message message) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(message.getEmail());
            mailMessage.setFrom("notify@cognibank.name");
            mailMessage.setSubject(SUBJECT);
            mailMessage.setText(getEmailText(message.getCode()));
            // Java Mail API for sending the email.
            javaMailSender.send(mailMessage);
        } catch (Exception exception) {
            System.out.println("Exception has occured while sending the email...Exception class is " + exception);
        }
    }

    private String getEmailText(final long code) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Dear Customer,\n\n");
        sb.append("\tYour One Time Password is " + code);
        sb.append("\n\n");
        sb.append("\tIt will expiry in next 10 minutes. Please don't share with anyone else. Cogni-Bank associates will never call you and ask for your code.\n\n");
        sb.append("\tThank you for banking with us!");
        return sb.toString();
    }

    private String getEmailTextForIF(final String accountBalance) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Dear Customer,\n\n");
        sb.append("\tYour Account Balance is " + accountBalance);
        sb.append(" ");
        sb.append("which is less than the minimum requirement. Please maintain minimum funds to escape penalty.\n\n");
        sb.append("\tThank you for banking with us!");
        return sb.toString();
    }

    private void sendASmsToUser(Message message) {
//        try {
//            // Twilio Rest API for sending the sms with the configured from number.
//            com.twilio.rest.api.v2010.account.Message smsMessage =
//                    com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber(message.getPhone()), // to
//                    new PhoneNumber(env.getProperty("spring.twilio.api.fromPhoneNumber")), // from
//                    getSmsText(message.getCode())).create();
//        } catch (Exception exception) {
//            System.out.println("Exception has occurred while sending the sms.");
//            System.out.println(exception);
//        }
    }

    private String getSmsText(final long code) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Dear Customer,\n\n");
        sb.append("\tYour One Time Password is " + code);
        sb.append("\n\n");
        sb.append("\tIt will expiry in next 10 minutes. \n\n");
        sb.append("\tThank you for banking with us!");
        return sb.toString();
    }

}