package com.eagle.eye.licensing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class EaLicensingServiceApplication {

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID());
        System.out.println(UUID.randomUUID());
        SpringApplication.run(EaLicensingServiceApplication.class, args);
    }

}
