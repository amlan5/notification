package com.notification.service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class NotificationConsumer {

    @Autowired
    EmailNotificationService emailNotificationService;

    @KafkaListener(topics = "users-notifications", groupId = "notification-group")
    public void consume(NotificationEvent event) {
        log.info("Received notification event: {}", event);

        // Here you can implement sending SMS, email, push notifications, etc.
        if ("USER_REGISTERED".equals(event.getEventType())) {
            log.info("Send email to {}", event.getEmail());
            log.info("Send SMS to {}", event.getMobileNumber());
            Map<String, Object> params = new HashMap<>();
            params.put("email", event.getEmail());
            params.put("name", event.getName());
            emailNotificationService.sendEmail("imamlanspd@gmail.com", event.getName(), 1L, params);
        }
    }
}
