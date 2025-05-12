package com.example.hotel_management_system.notification.factory.abstractFactory;

import com.example.hotel_management_system.notification.factory.Notification;
import com.example.hotel_management_system.notification.factory.NotificationFactory;
import com.example.hotel_management_system.notification.factory.SMSNotification;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SMSNotificationFactory implements NotificationFactory {
    private String message;
    private String recipient;

    @Override
    public Notification createNotification() {
        return new SMSNotification(message, recipient);
    }
}
