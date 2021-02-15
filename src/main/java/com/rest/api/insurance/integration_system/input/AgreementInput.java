package com.rest.api.insurance.integration_system.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

// Denne klassen må bli importeres som avhengighet fra fagsystem
@JsonDeserialize(as = AgreementInput.class)
public class AgreementInput {

    private static final long serialVersionUID = -1918301257905266472L;

    private final Long customerId;

    private final String productGroup;

    private final String product;

    public AgreementInput(@JsonProperty("customerId") Long customerId, @JsonProperty("productGroup") String productGroup, @JsonProperty("product") String product) {
        this.customerId = customerId;
        this.productGroup = productGroup;
        this.product = product;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public String getProduct() {
        return product;
    }
}

