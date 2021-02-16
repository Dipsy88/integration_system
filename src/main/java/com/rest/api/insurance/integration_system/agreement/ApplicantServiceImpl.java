package com.rest.api.insurance.integration_system.agreement;


import com.rest.api.insurance.integration_system.IntegrationSystemException;
import com.rest.api.insurance.integration_system.configuration.PropertiesConfiguration;
import com.rest.api.insurance.integration_system.input.AgreementInput;
import com.rest.api.insurance.integration_system.input.AgreementStatus;
import com.rest.api.insurance.integration_system.input.CustomerInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Service
// Løs ut unntak med feilkode i stedet for bare å logg inn overalt
// Prøv session for autentisering når du kalle en annet tjeneste
// Bruk immutables bibliotek for å få innspill
public class ApplicantServiceImpl implements ApplicantService {

    private static final Logger logger = LoggerFactory.getLogger(ApplicantServiceImpl.class);

    @Autowired
    private PropertiesConfiguration propertiesConfiguration;


    @Override
    public AgreementOutput createAgreement(final ApplicantInput applicantInput) {
        if(!isApplicantInputValid(applicantInput)){
            logger.error("Det oppsto en feil når prøvde å opprette kunde");
            throw new IntegrationSystemException("101"); // Må bli feilkode
        }

        Client client = ClientBuilder.newClient();
        CustomerInput customerInput = new CustomerInput(applicantInput.getFirstName(), applicantInput.getLastName(), applicantInput.getAddress(), applicantInput.getIdNumber());
        long customerId = createCustomer(customerInput, client);

        AgreementInput agreementInput = new AgreementInput(customerId, applicantInput.getProductGroup(), applicantInput.getProduct());
        long agreementId = createAgreement(agreementInput, client);

        // Brevtjeneste er ikke implementert
        // String status = sendLetter(applicantInput, client);
       // checkStatus(status);

        updateAgreementStatus(AgreementStatus.SEND, client, agreementId);
        return new AgreementOutput(agreementId, AgreementStatus.SEND.name());
    }

    private Long createCustomer(final CustomerInput customerInput, final Client client) {
        Response responseCreateCustomer = client.target(propertiesConfiguration.getCreateCustomerUrl())
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(customerInput, MediaType.APPLICATION_JSON));
        if (!hasNoError(responseCreateCustomer.getStatus())) {
            logger.error("Det oppsto en feil når prøvde å opprette kunde");
            throw new IntegrationSystemException("102");
        }
        return responseCreateCustomer.readEntity(Long.class);
    }

    private Long createAgreement(final AgreementInput agreementInput, final Client client) {
        Response responseCreateAgreement = client.target(propertiesConfiguration.getCreateAgreementUrl())
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(agreementInput, MediaType.APPLICATION_JSON));
        if (!hasNoError(responseCreateAgreement.getStatus())) {
            logger.error("Det oppsto en feil når prøvde å opprette avtale");
            throw new IntegrationSystemException("103");
        }
        return responseCreateAgreement.readEntity(Long.class);
    }

    private String sendLetter(final ApplicantInput applicantInput, final Client client) {
        Response responseCreateAgreement = client.target(propertiesConfiguration.getSendLetterUrl())
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(applicantInput, MediaType.APPLICATION_JSON));
        if (!hasNoError(responseCreateAgreement.getStatus())) {
            logger.error("Det oppsto en feil når prøvde å sende brev");
            throw new IntegrationSystemException("104");
        }
        return responseCreateAgreement.readEntity(String.class);
    }


    private void updateAgreementStatus(final AgreementStatus agreementStatus, final Client client, final long agreementId) {
        Response responseUpdateAgreementStatus = client.target(propertiesConfiguration.getCreateAgreementUrl() + "/" + agreementId)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json("\"" + agreementStatus.name() + "\""));
        if (!hasNoError(responseUpdateAgreementStatus.getStatus())) {
            logger.error("Det oppsto en feil når prøvde å oppdatere avtalestatus");
            throw new IntegrationSystemException("105");
        }

    }

    // Validere innspill mot forskjellige kriterier
    private boolean isApplicantInputValid(final ApplicantInput applicantInput){
        if(applicantInput.getFirstName()!=null && applicantInput.getLastName()!=null &&
                applicantInput.getAddress()!=null && applicantInput.getIdNumber()!=null &&
                applicantInput.getEmailAddress()!=null && applicantInput.getProductGroup()!=null
                && applicantInput.getProduct()!=null){
            return true;
        }
        return false;
    }

    private void checkStatus(String status){
        if (hasErrorStatus(status)){
            logger.error("Det hadde en feil da det prøvde å sende brev");
            throw new IntegrationSystemException("106");
        }
    }


    private boolean hasErrorStatus(String status){
        return !status.equalsIgnoreCase("SEND");
    }

    private boolean hasNoError(int status) {
        return (status != 200 || status != 204);
    }

}
