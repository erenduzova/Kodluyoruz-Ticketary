package com.erenduzova.ticketary.client.model.request;

public class NotificationRequest {

    private String message;
    private String to;

    public NotificationRequest() {
    }

    public NotificationRequest(String message, String to) {
        this.message = message;
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
