package com.example.hotel_management_system.notification.factory.abstractFactory;

import com.example.hotel_management_system.notification.factory.EmailNotification;
import com.example.hotel_management_system.notification.factory.Notification;
import com.example.hotel_management_system.notification.factory.NotificationFactory;
import lombok.Data;

import java.util.Date;

@Data
public class EmailNotificationFactory implements NotificationFactory {
    private String email;
    private String subject;
    private String message;
    private Date timestamp;

    public EmailNotificationFactory(String email, String subject, String message) {
        this.email = email;
        this.subject = subject;
        this.message = message;
        this.timestamp = new Date();
    }
    @Override
    public Notification createNotification() {
        return new EmailNotification(email, subject, message, timestamp);
    }

}
