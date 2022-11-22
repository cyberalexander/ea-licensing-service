package com.eagle.eye.licensing.model;

import java.util.UUID;

/**
 * Created : 22/11/2022 09:49
 * Project : ea-licensing-service
 * IDE : IntelliJ IDEA
 *
 * @author Aliaksandr_Leanovich
 * @version 1.0
 */
public record License(UUID id, UUID organisationId, String productName, String licenseType) {
}
