package com.rest.api.insurance.integration_system.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfiguration {

    @Value("${createCustomer.url: null}")
    private String createCustomerUrl;

    @Value("${createAgreement.url: null}")
    private String createAgreementUrl;

    @Value("${sendLetter.url: null}")
    private String sendLetterUrl;


    public String getCreateCustomerUrl() {
        return createCustomerUrl;
    }

    public String getCreateAgreementUrl() {
        return createAgreementUrl;
    }

    public String getSendLetterUrl() {
        return sendLetterUrl;
    }
}
