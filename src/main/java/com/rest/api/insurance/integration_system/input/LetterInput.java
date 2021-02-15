package com.rest.api.insurance.integration_system.input;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

// Denne klassen må bli importeres som avhengighet fra brevtjeneste
@JsonDeserialize(as = LetterInput.class)
public class LetterInput {

    private static final long serialVersionUID = -2431566991663906555L;

    private final String firstName;

    private final String lastName;

    private final String address;

    private final long idNumber;

    private final String emailAddress;

    private final String productGroup;

    private final String product;

    private final long customerNumber;

    private final long agreementNumber;

    public LetterInput(String firstName, String lastName, String address, long idNumber, String emailAddress, String productGroup, String product, long customerNumber, long agreementNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.idNumber = idNumber;
        this.emailAddress = emailAddress;
        this.productGroup = productGroup;
        this.product = product;
        this.customerNumber = customerNumber;
        this.agreementNumber = agreementNumber;
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

    public long getIdNumber() {
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

    public long getCustomerNumber() {
        return customerNumber;
    }

    public long getAgreementNumber() {
        return agreementNumber;
    }
}

