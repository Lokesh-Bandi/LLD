package com.example.hotel_management_system.notification.observer.observers;

import com.example.hotel_management_system.notification.dto.NotificationDTO;
import com.example.hotel_management_system.notification.factory.Notification;
import com.example.hotel_management_system.notification.factory.NotificationFactory;
import com.example.hotel_management_system.notification.factory.abstractFactory.EmailNotificationFactory;

public class EmailNotificationNotificationObserver implements NotificationObserver {

    @Override
    public void update(NotificationDTO notificationDTO) {
        NotificationFactory notificationFactory = new EmailNotificationFactory(
                notificationDTO.getRecipient(),
                notificationDTO.getSubject(),
                notificationDTO.getMessage()
        );
        Notification notification = notificationFactory.createNotification();
        notification.sendNotification();
    }

}
