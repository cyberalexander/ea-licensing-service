package com.eagle.eye.licensing.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Transient;

import java.util.UUID;

/**
 * Notes:
 * 1) The Java Persistence API (JPA), in 2019 renamed to Jakarta Persistence, is a Java application programming
 * interface specification that describes the management of relational data in applications using Java Platform,
 * Standard Edition and Java Platform, Enterprise Edition/Jakarta EE.
 * <p>
 * 2) Java's transient keyword is used to denote that a field is not to be serialized, whereas JPA's @Transient
 * annotation is used to indicate that a field is not to be persisted in the database, i.e. their semantics are different.
 * <p>
 * Created : 22/11/2022 09:49
 * Project : ea-licensing-service
 * IDE : IntelliJ IDEA
 *
 * @author Aliaksandr_Leanovich
 * @version 1.0
 */
@Entity
@Table(name = "ea_licenses")
public record License(
        @Id
        @Column(name = "license_id", nullable = false)
        UUID id,

        @Column(name = "organization_id", nullable = false)
        UUID organisationId,

        @Transient
        String organizationName,

        @Transient
        String contactName,

        @Transient
        String contactPhone,

        @Transient
        String contactEmail,

        @Column(name = "product_name", nullable = false)
        String productName,

        @Column(name = "license_type", nullable = false)
        String licenseType,

        @Column(name = "license_max", nullable = false)
        Integer licenseMax,

        @Column(name = "license_allocated", nullable = false)
        Integer licenseAllocated,

        @Column(name = "comment")
        String comment
) {
}
