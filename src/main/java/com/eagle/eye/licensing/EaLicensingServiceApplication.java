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

package com.eagle.eye.licensing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * The {@link EnableDiscoveryClient} annotation is the trigger for Spring Cloud to enable the application to use the
 * DiscoveryClient and Ribbon libraries.
 * <p>
 * Note:
 * >>> Should be used {@link EnableDiscoveryClient} or {@link EnableFeignClients}
 */
@SpringBootApplication
/*
  Option 1: Activates the Spring DiscoveryClient for use. See OrganisationDiscoveryClient.java
 */
@EnableDiscoveryClient
/*
   Option 2: Needed to use the FeignClient in your code. See OrganisationFeignClient.java
*/
@EnableFeignClients
public class EaLicensingServiceApplication {

    /**
     * Option 3:
     * The {@link LoadBalanced} annotation tells Spring Cloud to create a Ribbon backed RestTemplate class.
     * The {@link EnableDiscoveryClient} and {@link EnableFeignClients} annotations aren’t needed when using
     * the Ribbon backed RestTemplate and can be removed.
     * See {@link com.eagle.eye.licensing.client.OrganisationRestTemplateClient}
     *
     * @return instance of Spring Web backed {@link RestTemplate}
     */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(EaLicensingServiceApplication.class, args);
    }

}
