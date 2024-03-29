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
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

/**
 * Created : 18/04/2023 09:06
 * Project : ea-licensing-service
 * IDE : IntelliJ IDEA
 *
 * @author CyberAlexander
 * @version 1.0
 */
@Slf4j
@ActiveProfiles({"test"})
@ExtendWith(MockitoExtension.class)
class LicenseServiceTests {
    private static final EasyRandom R = new EasyRandom();

    @Mock
    private LicenseRepository licenseRepository;

    @Mock
    private ServiceConfig config;

    @Mock
    private OrganisationFeignClient organisationFeignClient;

    @Mock
    private OrganisationRestTemplateClient organisationRestTemplateClient;

    @Mock
    private OrganisationDiscoveryClient organisationDiscoveryClient;

    @MockBean
    @InjectMocks
    private LicenseService service;

    @Test
    void testGetLicenseById() {
        License expected = R.nextObject(License.class);
        Mockito.when(licenseRepository.findById(expected.getId())).thenReturn(Optional.of(expected));

        License received = service.getLicense(expected.getId());

        Assertions.assertThat(received).isEqualTo(expected);
    }

    @Test
    void testGetLicenseById_WhenLicenseNotFound() {
        License expected = R.nextObject(License.class);
        Mockito.when(licenseRepository.findById(expected.getId())).thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> service.getLicense(expected.getId()));
    }

    @Test
    void testGetLicenseWithHttpClient_ValidateConfigInvoked() {
        License expected = R.nextObject(License.class);
        Organisation organisation = new Organisation(UUID.randomUUID(), "name", "contactName",
                "contactEmail", "contactPhone");

        Mockito.when(licenseRepository.findByOrganisationIdAndId(expected.getOrganisationId(), expected.getId()))
                .thenReturn(expected);
        Mockito.when(organisationRestTemplateClient.getOrganisation(expected.getOrganisationId()))
                .thenReturn(organisation);

        service.getLicense(expected.getOrganisationId(), expected.getId(), "Non Supported");

        Mockito.verify(config).getExampleProperty();
    }

    @Test
    void testGetLicenseWithHttpClient_ValidateRepositoryInvoked() {
        License expected = R.nextObject(License.class);
        Organisation organisation = new Organisation(UUID.randomUUID(), "name", "contactName",
                "contactEmail", "contactPhone");

        Mockito.when(licenseRepository.findByOrganisationIdAndId(expected.getOrganisationId(), expected.getId()))
                .thenReturn(expected);
        Mockito.when(organisationRestTemplateClient.getOrganisation(expected.getOrganisationId()))
                .thenReturn(organisation);

        service.getLicense(expected.getOrganisationId(), expected.getId(), "Non Supported");

        Mockito.verify(licenseRepository).findByOrganisationIdAndId(expected.getOrganisationId(), expected.getId());
    }

    @Test
    void testGetLicenseWithHttpClient_ValidateDefaultClientInvoked() {
        License expected = R.nextObject(License.class);
        Organisation organisation = new Organisation(UUID.randomUUID(), "name", "contactName",
                "contactEmail", "contactPhone");

        Mockito.when(licenseRepository.findByOrganisationIdAndId(expected.getOrganisationId(), expected.getId()))
                .thenReturn(expected);
        Mockito.when(organisationRestTemplateClient.getOrganisation(expected.getOrganisationId()))
                .thenReturn(organisation);

        service.getLicense(expected.getOrganisationId(), expected.getId(), "Non Supported");

        Mockito.verify(organisationRestTemplateClient).getOrganisation(expected.getOrganisationId());
    }

    @Test
    void testGetLicenseWithHttpClient_ValidateFeignClientInvoked() {
        License expected = R.nextObject(License.class);
        Organisation organisation = new Organisation(UUID.randomUUID(), "name", "contactName",
                "contactEmail", "contactPhone");

        Mockito.when(licenseRepository.findByOrganisationIdAndId(expected.getOrganisationId(), expected.getId()))
                .thenReturn(expected);
        Mockito.when(organisationFeignClient.getOrganisation(expected.getOrganisationId()))
                .thenReturn(organisation);

        service.getLicense(expected.getOrganisationId(), expected.getId(), "feign");

        Mockito.verify(organisationFeignClient).getOrganisation(expected.getOrganisationId());
    }

    @Test
    void testGetLicenseWithHttpClient_ValidateRestClientInvoked() {
        License expected = R.nextObject(License.class);
        Organisation organisation = new Organisation(UUID.randomUUID(), "name", "contactName",
                "contactEmail", "contactPhone");

        Mockito.when(licenseRepository.findByOrganisationIdAndId(expected.getOrganisationId(), expected.getId()))
                .thenReturn(expected);
        Mockito.when(organisationRestTemplateClient.getOrganisation(expected.getOrganisationId()))
                .thenReturn(organisation);

        service.getLicense(expected.getOrganisationId(), expected.getId(), "rest");

        Mockito.verify(organisationRestTemplateClient).getOrganisation(expected.getOrganisationId());
    }

    @Test
    void testGetLicenseWithHttpClient_ValidateDiscoveryClientInvoked() {
        License expected = R.nextObject(License.class);
        Organisation organisation = new Organisation(UUID.randomUUID(), "name", "contactName",
                "contactEmail", "contactPhone");

        Mockito.when(licenseRepository.findByOrganisationIdAndId(expected.getOrganisationId(), expected.getId()))
                .thenReturn(expected);
        Mockito.when(organisationDiscoveryClient.getOrganisation(expected.getOrganisationId()))
                .thenReturn(organisation);

        service.getLicense(expected.getOrganisationId(), expected.getId(), "discovery");

        Mockito.verify(organisationDiscoveryClient).getOrganisation(expected.getOrganisationId());
    }

    @Test
    void testGetLicensesByOrganisationId() {
        License expected = R.nextObject(License.class);
        List<License> expectedList = List.of(expected);

        Mockito.when(licenseRepository.findByOrganisationId(expected.getOrganisationId())).thenReturn(expectedList);

        List<License> actual = service.getLicensesByOrganisationId(expected.getOrganisationId());

        Assertions.assertThat(actual).isEqualTo(expectedList);
    }

    @Test
    void testGetLicensesByOrganisationId_ValidateRepositoryInvoked() {
        License expected = R.nextObject(License.class);
        List<License> expectedList = List.of(expected);

        Mockito.when(licenseRepository.findByOrganisationId(expected.getOrganisationId())).thenReturn(expectedList);

        service.getLicensesByOrganisationId(expected.getOrganisationId());

        Mockito.verify(licenseRepository).findByOrganisationId(expected.getOrganisationId());
    }

    @Test
    void testSaveLicense() {
        License toSave = R.nextObject(License.class);

        Mockito.when(licenseRepository.save(toSave)).thenReturn(toSave);

        License saved = service.saveLicense(toSave);
        Assertions.assertThat(toSave).isEqualTo(saved);
    }

    @Test
    void testDeleteLicense() {
        License toDelete = R.nextObject(License.class);
        service.deleteLicense(toDelete);
        Mockito.verify(licenseRepository).deleteById(toDelete.getId());
    }
}