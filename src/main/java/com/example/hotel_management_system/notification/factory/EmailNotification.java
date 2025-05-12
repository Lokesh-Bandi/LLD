package com.example.hotel_management_system.notification.factory;

import lombok.Data;

import java.util.Date;

@Data
public class EmailNotification implements Notification{
    private String email;
    private String subject;
    private String message;
    private Date timestamp;

    public EmailNotification(String email, String subject, String message, Date timestamp) {
        this.email = email;
        this.subject = subject;
        this.message = message;
        this.timestamp = timestamp;
    }

    @Override
    public void sendNotification() {
        System.out.println("Sending email to " + email);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
        System.out.println("Timestamp: " + timestamp);
    }
}
