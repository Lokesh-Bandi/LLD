package com.example.hotel_management_system.notification.factory.abstractFactory;

import com.example.hotel_management_system.notification.factory.Notification;
import com.example.hotel_management_system.notification.factory.NotificationFactory;
import com.example.hotel_management_system.notification.factory.PushNotification;
import lombok.Data;

@Data
public class PushNotificationFactory implements NotificationFactory {
    private String deviceToken;
    private String message;

    public PushNotificationFactory(String deviceToken, String message) {
        this.deviceToken = deviceToken;
        this.message = message;
    }

    @Override
    public Notification createNotification() {
        return new PushNotification(deviceToken, message);
    }
}
