package com.rest.api.insurance.integration_system.rest;


import com.rest.api.insurance.integration_system.agreement.AgreementOutput;
import com.rest.api.insurance.integration_system.agreement.ApplicantInput;
import com.rest.api.insurance.integration_system.agreement.ApplicantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Component
@Path("/agreements")
@Consumes("application/json")
@Produces("application/json")
// Bruk swagger for å dokumentere API
public class ApplicantResource {

    private static final Logger logger = LoggerFactory.getLogger(ApplicantResource.class);

    @Autowired
    private ApplicantService applicantService;

    @POST
    public Response createAgreement(ApplicantInput applicantInput) {
        AgreementOutput agreementOutput = applicantService.createAgreement(applicantInput);
        return Response.ok(agreementOutput).build();
    }


}
