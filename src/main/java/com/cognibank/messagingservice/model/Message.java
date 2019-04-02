package com.cognibank.messagingservice.model;

/** +
 * Message object which we parse from the queue as a json object.
 */
public class Message {
    private String email;
    private String phone;
    private long code;
    private String type;

    public Message() {

    }

    public Message withEmail (final String emailId) {
        setEmail(emailId);
        return this;
    }

    public Message withPhone (final String phone) {
        setPhone(phone);
        return this;
    }

    public Message withCode (final long code) {
        setCode(code);
        return this;
    }

    public Message withType (final String type) {
        setType(type);
        return this;
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

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "{" +
                "email:'" + email + '\'' +
                "phone:'" + phone + '\'' +
                ", code:" + code +
                ", type:'" + type + '\'' +
                '}';
    }
}