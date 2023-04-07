/*
 * MIT License
 *
 * Copyright (c) 2022-2023 CyberAlexander
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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

import java.util.List;
import java.util.UUID;

/**
 * Created : 22/11/2022 10:03
 * Project : ea-licensing-service
 * IDE : IntelliJ IDEA
 *
 * @author CyberAlexander
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
        return licenseRepository.findById(licenseId).orElseThrow();
    }

    public License getLicense(UUID organisationId, UUID licenseId, String clientType) {
        License license = licenseRepository.findByOrganisationIdAndId(organisationId, licenseId);
        Organisation organisation = retrieveOrganisationInfo(organisationId, clientType);

        return new License(
                license.id(),
                license.organisationId(),
                organisation.name(),
                organisation.contactName(),
                organisation.contactEmail(),
                organisation.contactPhone(),
                license.productName(),
                license.licenseType(),
                license.licenseMax(),
                license.licenseAllocated(),
                config.getExampleProperty()
        );
    }

    public List<License> getLicensesByOrganisationId(UUID organisationId) {
        return licenseRepository.findByOrganisationId(organisationId);
    }

    public void saveLicense(License license) {
        licenseRepository.save(license);
    }

    public void deleteLicense(License license) {
        licenseRepository.deleteById(license.id());
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
