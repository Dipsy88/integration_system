package com.rest.api.insurance.integration_system;

// Feilkode implementering her
public class IntegrationSystemException extends RuntimeException {

    public IntegrationSystemException() {
        super();
    }

    public IntegrationSystemException(String nativeMessage) {
        super(nativeMessage);
    }


}
