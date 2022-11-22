package com.eagle.eye.licensing.service;

import com.eagle.eye.licensing.model.License;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created : 22/11/2022 10:03
 * Project : ea-licensing-service
 * IDE : IntelliJ IDEA
 *
 * @author Aliaksandr_Leanovich
 * @version 1.0
 */
@Service
public class LicenseService {

    public License getLicense(UUID licenseId) {
        return new License(licenseId, UUID.randomUUID(), "Test Product Name", "PerSeat");
    }
}