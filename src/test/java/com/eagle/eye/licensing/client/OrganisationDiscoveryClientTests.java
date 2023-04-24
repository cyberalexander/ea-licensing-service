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

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

/**
 * Created : 24/04/2023 09:23
 * Project : ea-licensing-service
 * IDE : IntelliJ IDEA
 *
 * @author CyberAlexander
 * @version 1.0
 */
@Slf4j
@ActiveProfiles({"test"})
@ExtendWith(MockitoExtension.class)
class OrganisationDiscoveryClientTests {

    @Mock
    private DiscoveryClient discoveryClient;

    @InjectMocks
    @MockBean
    private OrganisationDiscoveryClient organisationDiscoveryClient;

    @Test
    void testGetOrganisation() {
        Mockito.when(discoveryClient.getInstances("ea-organisation-service")).thenReturn(Mockito.anyList());
        organisationDiscoveryClient.getOrganisation(UUID.randomUUID());
        Mockito.verify(discoveryClient).getInstances("ea-organisation-service");
    }
}