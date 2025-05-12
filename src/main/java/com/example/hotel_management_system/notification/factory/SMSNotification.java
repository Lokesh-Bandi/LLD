package com.example.hotel_management_system.notification.factory;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SMSNotification implements Notification{
    private String message;
    private String recipient;

    @Override
    public void sendNotification() {
        System.out.println("Sending SMS to " + recipient);
        System.out.println("Message: " + message);
    }

}
