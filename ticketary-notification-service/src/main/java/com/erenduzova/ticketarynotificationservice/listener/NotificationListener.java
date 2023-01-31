package com.erenduzova.ticketarynotificationservice.listener;

import com.erenduzova.ticketarynotificationservice.dto.NotificationRequest;
import com.erenduzova.ticketarynotificationservice.entity.MailNotification;
import com.erenduzova.ticketarynotificationservice.entity.Notification;
import com.erenduzova.ticketarynotificationservice.entity.SmsNotification;
import com.erenduzova.ticketarynotificationservice.repository.NotificationRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    @Autowired
    private NotificationRepository notificationRepository;

    @RabbitListener(queues = "ticketary.notification")
    public void notificationListener(NotificationRequest notificationRequest) {
        if (notificationRequest.getTo().contains("@")) {
            Notification notification = new MailNotification(notificationRequest.getMessage(), notificationRequest.getTo());
            notificationRepository.save(notification);
        } else {
            Notification notification = new SmsNotification(notificationRequest.getMessage(), notificationRequest.getTo());
            notificationRepository.save(notification);
        }
    }

}
