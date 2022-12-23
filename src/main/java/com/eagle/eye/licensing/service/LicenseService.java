package com.eagle.eye.licensing.service;

import com.eagle.eye.licensing.client.OrganisationDiscoveryClient;
import com.eagle.eye.licensing.client.OrganisationFeignClient;
import com.eagle.eye.licensing.client.OrganisationRestTemplateClient;
import com.eagle.eye.licensing.config.ServiceConfig;
import com.eagle.eye.licensing.model.License;
import com.eagle.eye.licensing.model.Organisation;
import com.eagle.eye.licensing.repository.LicenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created : 22/11/2022 10:03
 * Project : ea-licensing-service
 * IDE : IntelliJ IDEA
 *
 * @author Aliaksandr_Leanovich
 * @version 1.0
 */
@Service
@AllArgsConstructor
public class LicenseService {

    private LicenseRepository licenseRepository;

    private ServiceConfig config;

    private OrganisationFeignClient organisationFeignClient;

    private OrganisationRestTemplateClient organisationRestTemplateClient;

    private OrganisationDiscoveryClient organisationDiscoveryClient;

    public License getLicense(UUID licenseId) {
        return new License(
                licenseId,
                UUID.randomUUID(),
                "Test Organisation Name",
                "Test Contact Name",
                "Test Contact Phone",
                "Test Contact Email",
                "Test Product Name",
                "PerSeat",
                100_000,
                9576,
                "Test Comment"
        );
    }

    private Organisation retrieveOrganisationInfo(UUID organisationId, String clientType) {
        Organisation organisation;

        switch (clientType) {
            case "feign" -> {
                System.out.println("I am using the feign client");
                organisation = organisationFeignClient.getOrganisation(organisationId);
            }
            case "rest" -> {
                System.out.println("I am using the rest client");
                organisation = organisationRestTemplateClient.getOrganisation(organisationId);
            }
            case "discovery" -> {
                System.out.println("I am using the discovery client");
                organisation = organisationDiscoveryClient.getOrganisation(organisationId);
            }
            default -> organisation = organisationRestTemplateClient.getOrganisation(organisationId);
        }
        return organisation;
    }
}
