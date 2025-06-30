package com.notification.service.kafka;
import org.springframework.beans.factory.annotation.Value;
import sendinblue.ApiClient;
import sibApi.TransactionalEmailsApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrevoConfig {

    @Value("${brevo.secret.key}")
    private String brevoKey;

    @Bean
    public TransactionalEmailsApi transactionalEmailsApi() {
        ApiClient defaultClient = new ApiClient();
        defaultClient.setApiKey(brevoKey);
        return new TransactionalEmailsApi(defaultClient);
    }
}



