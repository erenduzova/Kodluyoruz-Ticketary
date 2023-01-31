package com.erenduzova.ticketarynotificationservice.entity;

import com.erenduzova.ticketarynotificationservice.entity.enums.NotificationType;
import jakarta.persistence.Entity;

@Entity
public class MailNotification extends Notification {

    public MailNotification(String message, String to) {
        super(message, to, NotificationType.MAIL);
    }

}
