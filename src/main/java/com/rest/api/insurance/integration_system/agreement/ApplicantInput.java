package com.rest.api.insurance.integration_system.agreement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

@JsonDeserialize(as = ApplicantInput.class)
public class ApplicantInput implements Serializable {

    private static final long serialVersionUID = -58352190877079941L;

    private final String firstName;

    private final String lastName;

    private final String address;

    private final Long idNumber;

    private final String emailAddress;

    private final String productGroup;

    private final String product;

    public ApplicantInput(@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName, @JsonProperty("address") String address, @JsonProperty("idNumber") Long idNumber, @JsonProperty("emailAddress") String emailAddress, @JsonProperty("productGroup") String productGroup, @JsonProperty("product") String product) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.idNumber = idNumber;
        this.emailAddress = emailAddress;
        this.productGroup = productGroup;
        this.product = product;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public Long getIdNumber() {
        return idNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public String getProduct() {
        return product;
    }
}
