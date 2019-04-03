package com.cognibank.messagingservice.model;

public class MessageForIF {
    String accountBalance;
    String email;
    String phone;
    String type;

    public MessageForIF() {

    }

    public MessageForIF withEmail (final String emailId) {
        setEmail(emailId);
        return this;
    }

    public MessageForIF withPhone (final String phone) {
        setPhone(phone);
        return this;
    }

    public MessageForIF withCode (final String accountBalance) {
        setAccountBalance(accountBalance);
        return this;
    }

    public MessageForIF withType (final String type) {
        setType(type);
        return this;
    }

    public String getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
