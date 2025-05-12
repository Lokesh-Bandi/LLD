package com.example.hotel_management_system.notification.factory;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PushNotification implements Notification{
    private String deviceToken;
    private String message;

    @Override
    public void sendNotification() {
        System.out.println("Sending push notification to " + deviceToken);
        System.out.println("Message: " + message);
    }
}
