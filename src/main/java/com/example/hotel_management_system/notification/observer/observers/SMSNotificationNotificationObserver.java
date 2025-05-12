package com.example.hotel_management_system.notification.observer.observers;

import com.example.hotel_management_system.notification.dto.NotificationDTO;
import com.example.hotel_management_system.notification.factory.Notification;
import com.example.hotel_management_system.notification.factory.NotificationFactory;
import com.example.hotel_management_system.notification.factory.abstractFactory.SMSNotificationFactory;

public class SMSNotificationNotificationObserver implements NotificationObserver {
    @Override
    public void update(NotificationDTO notificationDTO) {
        // Create an SMS notification using the factory
        NotificationFactory notificationFactory = new SMSNotificationFactory(
                notificationDTO.getRecipient(),
                notificationDTO.getMessage()
        );
        Notification notification = notificationFactory.createNotification();
        notification.sendNotification();
    }
}
