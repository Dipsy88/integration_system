package com.rest.api.insurance.integration_system.input;

import com.fasterxml.jackson.annotation.JsonProperty;

// Denne klassen må bli importeres som avhengighet fra fagsystem
public class CustomerInput {

    private static final long serialVersionUID = -5127656081903693812L;

    private final String firstName;

    private final String lastName;

    private final String address;

    private final Long idNumber;

    public CustomerInput(@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName, @JsonProperty("address") String address, @JsonProperty("idNumber") Long idNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.idNumber = idNumber;
    }

    public Long getIdNumber() {
        return idNumber;
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

}
