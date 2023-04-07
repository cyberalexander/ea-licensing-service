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

package com.eagle.eye.licensing.client;

import com.eagle.eye.licensing.model.Organisation;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * Created : 20/12/2022 09:44
 * Project : ea-licensing-service
 * IDE : IntelliJ IDEA
 *
 * @author CyberAlexander
 * @version 1.0
 */
@Component
public class OrganisationRestTemplateClient {

    private RestTemplate restTemplate;

    public OrganisationRestTemplateClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Organisation getOrganisation(UUID organisationId) {
        ResponseEntity<Organisation> restExchange = restTemplate.exchange(
                "http://ea-organisation-service/v1/organizations/{organizationId}",
                HttpMethod.GET,
                null, Organisation.class, organisationId);

        return restExchange.getBody();
    }
}
