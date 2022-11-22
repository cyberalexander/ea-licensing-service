package com.eagle.eye.licensing.controller;

import com.eagle.eye.licensing.model.License;
import com.eagle.eye.licensing.service.LicenseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created : 22/11/2022 09:47
 * Project : ea-licensing-service
 * IDE : IntelliJ IDEA
 *
 * @author Aliaksandr_Leanovich
 * @version 1.0
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/organisations/{organisationId}/licenses")
public class LicenseServiceController {

    private LicenseService licenseService;


    @GetMapping(value = "/{licenseId}")
    public License getLicense(@PathVariable UUID organisationId, @PathVariable UUID licenseId) {
        return licenseService.getLicense(licenseId);
    }
}
