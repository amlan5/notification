package com.notification.service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationService {

    public void sendEmail(String email, String message) {
        log.info("Sending email to {} with message: {}", email, message);
        // Integrate real email logic here
    }

    public void sendSMS(String number, String message) {
        log.info("Sending SMS to {} with message: {}", number, message);
        // Integrate SMS logic here
    }

    public void handleNotification(NotificationEvent event) {
        if (event.getEmail() != null) {
            sendEmail(event.getEmail(), event.getMessage());
        }
        if (event.getMobileNumber() != null) {
            sendSMS(event.getMobileNumber(), event.getMessage());
        }
    }
}
