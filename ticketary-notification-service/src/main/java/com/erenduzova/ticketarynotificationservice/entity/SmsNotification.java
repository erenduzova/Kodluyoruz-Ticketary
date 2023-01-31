package com.erenduzova.ticketarynotificationservice.entity;

import com.erenduzova.ticketarynotificationservice.entity.enums.NotificationType;
import jakarta.persistence.Entity;

@Entity
public class SmsNotification extends Notification {

    public SmsNotification(String message, String to) {
        super(message, to, NotificationType.SMS);
    }

}
