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

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created : 18/04/2023 09:06
 * Project : ea-licensing-service
 * IDE : IntelliJ IDEA
 *
 * @author CyberAlexander
 * @version 1.0
 */
@Slf4j
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
}