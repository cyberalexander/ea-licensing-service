package com.eagle.eye.licensing.controller;

import com.eagle.eye.licensing.config.ServiceConfig;
import com.eagle.eye.licensing.model.License;
import com.eagle.eye.licensing.service.LicenseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * Created : 22/11/2022 09:47
 * Project : ea-licensing-service
 * IDE : IntelliJ IDEA
 *
 * @author Aliaksandr_Leanovich
 * @version 1.0
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = "/v1/organisations/{organisationId}/licenses", produces = MediaType.APPLICATION_JSON_VALUE)
public class LicenseServiceController {

    private LicenseService licenseService;

    private ServiceConfig serviceConfig;

    @GetMapping(value = "/{licenseId}")
    public License getLicense(@PathVariable UUID organisationId, @PathVariable UUID licenseId) {
        return licenseService.getLicense(organisationId, licenseId, "");
    }

    @GetMapping
    public List<License> getLicenses(@PathVariable("organisationId") UUID organisationId) {
        return licenseService.getLicensesByOrganisationId(organisationId);
    }

    @GetMapping(value = "/{licenseId}/{clientType}")
    public License getLicenseWithClient(
            @PathVariable UUID organisationId,
            @PathVariable UUID licenseId,
            @PathVariable String clientType) {
        return licenseService.getLicense(organisationId, licenseId, clientType);
    }

    @PutMapping(value = "/{licenseId}")
    public void updateLicense(@PathVariable("licenseId") UUID licenseId, @RequestBody License license) {
        log.debug("Updating license:{}", licenseId);
        licenseService.saveLicense(license);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveLicense(@RequestBody License license) {
        licenseService.saveLicense(license);
    }

    @DeleteMapping(value = "/{licenseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLicense(@PathVariable("licenseId") UUID licenseId, @RequestBody License license) {
        log.debug("Deleting License:{}", licenseId);
        licenseService.deleteLicense(license);
    }
}
