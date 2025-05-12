package com.example.hotel_management_system.notification.observer.observers;

import com.example.hotel_management_system.notification.dto.NotificationDTO;

public interface NotificationObserver {
    void update(NotificationDTO notificationDTO);
}
