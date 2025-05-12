package com.example.hotel_management_system.notification.observer;

import com.example.hotel_management_system.notification.dto.NotificationDTO;
import com.example.hotel_management_system.notification.observer.observers.NotificationObserver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {
    List<NotificationObserver> notificationObservers = new ArrayList<>();
    public void registerObserver(NotificationObserver notificationObserver) {
        notificationObservers.add(notificationObserver);
    }
    public void unregisterObserver(NotificationObserver notificationObserver) {
        notificationObservers.remove(notificationObserver);
    }
    private void notifyObservers(NotificationDTO notificationDTO) {
        for (NotificationObserver notificationObserver : notificationObservers) {
            notificationObserver.update(notificationDTO);
        }
    }

    public void sendNotification(NotificationDTO notificationDTO) {
        // Notify all registered observers
        notifyObservers(notificationDTO);
    }
}
