package com.azure.bau.aiproject.config;

import com.azure.ai.textanalytics.TextAnalyticsClient;
import com.azure.ai.textanalytics.TextAnalyticsClientBuilder;
import com.azure.core.credential.AzureKeyCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureConfig {

    @Value("${azure.textanalytics.key}")
    private String azureKey;

    @Value("${azure.textanalytics.endpoint}")
    private String azureEndpoint;

    @Bean
    public TextAnalyticsClient textAnalyticsClient() {
        return new TextAnalyticsClientBuilder()
                .credential(new AzureKeyCredential(azureKey))
                .endpoint(azureEndpoint)
                .buildClient();
    }
}

