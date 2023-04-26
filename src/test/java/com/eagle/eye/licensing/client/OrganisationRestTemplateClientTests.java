/*
 *
 *  * MIT License
 *  *
 *  * Copyright (c) 2022-2023 CyberAlexander
 *  *
 *  * Permission is hereby granted, free of charge, to any person obtaining a copy
 *  * of this software and associated documentation files (the "Software"), to deal
 *  * in the Software without restriction, including without limitation the rights
 *  * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  * copies of the Software, and to permit persons to whom the Software is
 *  * furnished to do so, subject to the following conditions:
 *  *
 *  * The above copyright notice and this permission notice shall be included in all
 *  * copies or substantial portions of the Software.
 *  *
 *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  * SOFTWARE.
 *
 */

package com.eagle.eye.licensing.client;

import com.eagle.eye.licensing.model.Organisation;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * Created : 25/04/2023 09:14
 * Project : ea-licensing-service
 * IDE : IntelliJ IDEA
 *
 * @author alexanderleonovich
 * @version 1.0
 */
@Slf4j
@ActiveProfiles({"test"})
@ExtendWith(MockitoExtension.class)
class OrganisationRestTemplateClientTests {

    private static final EasyRandom R = new EasyRandom();

    @Mock
    private RestTemplate restTemplate;

    @MockBean
    @InjectMocks
    private OrganisationRestTemplateClient organisationRestTemplateClient;

    @Test
    void testGetOrganisation() {
        Organisation expected = new Organisation(UUID.randomUUID(), R.nextObject(String.class),
                R.nextObject(String.class), R.nextObject(String.class), R.nextObject(String.class));
        UUID organisationId = expected.id();

        Mockito.when(restTemplate.exchange(
                "http://ea-organisation-service/v1/organizations/{organizationId}",
                HttpMethod.GET,
                null, Organisation.class, organisationId))
                .thenReturn(new ResponseEntity<>(expected, HttpStatusCode.valueOf(200)));

        Organisation organisation = organisationRestTemplateClient.getOrganisation(organisationId);

        log.info("{}", organisation);
    }
}