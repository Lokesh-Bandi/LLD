package com.example.hotel_management_system.notification.dto;

import lombok.Data;
import lombok.Setter;

@Data
public class NotificationDTO {
    private String recipient;
    private String subject;
    private String message;
    private String payload;

}
