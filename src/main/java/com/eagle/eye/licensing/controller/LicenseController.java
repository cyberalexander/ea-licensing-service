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
 * @author CyberAlexander
 * @version 1.0
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = "/v1/organisations/{organisationId}/licenses", produces = MediaType.APPLICATION_JSON_VALUE)
public class LicenseController {

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
    public License updateLicense(@PathVariable("licenseId") UUID licenseId, @RequestBody License toUpdate) {
        log.debug("Updating license:{}", toUpdate);
        License updated = licenseService.saveLicense(toUpdate);
        log.debug("Updated license:{}", updated);
        return updated;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public License saveLicense(@RequestBody License license) {
        return licenseService.saveLicense(license);
    }

    @DeleteMapping(value = "/{licenseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLicense(@PathVariable("licenseId") UUID licenseId, @RequestBody License license) {
        log.warn("Deleting License:{}", licenseId);
        licenseService.deleteLicense(license);
    }
}
