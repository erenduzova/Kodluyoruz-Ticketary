package com.erenduzova.ticketarynotificationservice.entity;

import com.erenduzova.ticketarynotificationservice.entity.enums.NotificationType;
import jakarta.persistence.*;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "message")
    protected String message;
    @Column(name = "send_to")
    protected String sendTo;
    @Column(name = "notification_type")
    protected NotificationType notificationType;

    public Notification() {

    }

    public Notification(String message, String sendTo, NotificationType notificationType) {
        this.message = message;
        this.sendTo = sendTo;
        this.notificationType = notificationType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }
}
