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
/*@EnableFeignClients*/ /* Option 2: Needed to use the FeignClient in your code */
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
