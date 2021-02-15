package com.rest.api.insurance.integration_system.agreement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = AgreementOutput.class)
public class AgreementOutput {

    private static final long serialVersionUID = -1247067735983222586L;

    private final Long agreementNumber;

    private final String AgreementStatus;

    public AgreementOutput(@JsonProperty("agreementNumber") Long agreementNumber, @JsonProperty("agreementStatus") String agreementStatus) {
        this.agreementNumber = agreementNumber;
        AgreementStatus = agreementStatus;
    }

    public Long getAgreementNumber() {
        return agreementNumber;
    }

    public String getAgreementStatus() {
        return AgreementStatus;
    }
}
