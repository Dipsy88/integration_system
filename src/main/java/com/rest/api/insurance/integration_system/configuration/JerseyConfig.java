package com.rest.api.insurance.integration_system.configuration;

import com.rest.api.insurance.integration_system.rest.ApplicantResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/insurance")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(ApplicantResource.class);
    }
}
