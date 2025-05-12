package com.example.hotel_management_system.notification.dto;

import lombok.Data;
import lombok.Setter;

@Data
public class NotificationDTO {
    private String recipient;
    private String subject;
    private String message;
    private String payload;

    public static class Builder {
        private String recipient;
        private String subject;
        private String message;
        private String payload;

        public Builder recipient(String recipient) {
            this.recipient = recipient;
            return this;
        }

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder payload(String payload) {
            this.payload = payload;
            return this;
        }

        public NotificationDTO build() {
            NotificationDTO notificationDTO = new NotificationDTO();
            notificationDTO.setRecipient(recipient);
            notificationDTO.setSubject(subject);
            notificationDTO.setMessage(message);
            notificationDTO.setPayload(payload);
            return notificationDTO;
        }
    }

}
