package com.notification.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sendinblue.ApiException;
import sibApi.TransactionalEmailsApi;
import sibModel.CreateSmtpEmail;
import sibModel.SendSmtpEmail;
import sibModel.SendSmtpEmailTo;

import java.util.*;

@Service
public class EmailNotificationService {

    private final TransactionalEmailsApi transactionalEmailsApi;

    @Autowired
    public EmailNotificationService(TransactionalEmailsApi transactionalEmailsApi) {
        this.transactionalEmailsApi = transactionalEmailsApi;
    }

    public void sendEmail(String toEmail, String toName, Long templateId, Map<String, Object> params) {
        SendSmtpEmail email = new SendSmtpEmail()
                .to(List.of(new SendSmtpEmailTo().email(toEmail).name(toName)))
                .templateId(templateId)
                .params(params);

        try {
            CreateSmtpEmail result = transactionalEmailsApi.sendTransacEmail(email);
            System.out.println("Email sent! Message ID: " + result.getMessageId());
        } catch (ApiException e) {
            System.err.println("Error sending email: " + e.getResponseBody());
        }
    }
}
