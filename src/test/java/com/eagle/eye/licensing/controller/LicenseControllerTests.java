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
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created : 07/04/2023 09:29
 * Project : ea-licensing-service
 * IDE : IntelliJ IDEA
 *
 * @author Aliaksandr_Leanovich
 * @version 1.0
 */
@Slf4j
@ActiveProfiles(value = {"test"})
@WebMvcTest(controllers = {LicenseController.class})
class LicenseControllerTests {

    private static final EasyRandom EASY_RANDOM = new EasyRandom();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private LicenseService licenseService;

    @MockBean
    private ServiceConfig serviceConfig;

    @Autowired
    @InjectMocks
    private LicenseController controller;

    @Test
    void testControllerInstantiated() {
        log.info("{} instantiated : {}", LicenseController.class.getName(), !Objects.isNull(controller));
    }

    @Test
    @SneakyThrows
    void testGetLicense() {
        License expected = EASY_RANDOM.nextObject(License.class);

        Mockito.when(licenseService.getLicense(Mockito.any(), Mockito.eq(expected.getId()), Mockito.any())).thenReturn(expected);

        mockMvc.perform(
                        get(
                                "/v1/organisations/{organisationId}/licenses/{licenseId}",
                                UUID.randomUUID(),
                                expected.getId()
                        ).contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").hasJsonPath())
                .andExpect(jsonPath("$.organisationId").hasJsonPath())
                .andExpect(jsonPath("$.productName").hasJsonPath())
                .andExpect(jsonPath("$.contactName").hasJsonPath());
    }

    @Test
    void testGetLicenses() {
    }

    @Test
    void testGetLicenseWithClient() {
    }

    @Test
    void testUpdateLicense() {
    }

    @Test
    void testSaveLicense() {
    }

    @Test
    void testDeleteLicense() {
    }
}