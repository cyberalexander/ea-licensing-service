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
@EnableDiscoveryClient /* Activates the Spring DiscoveryClient for use */
/*@EnableFeignClients*/ /* Needed to use the FeignClient in your code */
public class EaLicensingServiceApplication {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(EaLicensingServiceApplication.class, args);
    }

}
