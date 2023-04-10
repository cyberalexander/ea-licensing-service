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

package com.eagle.eye.licensing.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import java.util.Objects;
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
 * @author CyberAlexander
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ea_licenses")
public class License {
    @Id
    @Column(name = "license_id", nullable = false)
    private UUID id;

    @Column(name = "organization_id", nullable = false)
    private UUID organisationId;

    /**
     * {@link Transient} Marks a field to be transient for the mapping framework. Thus, the property
     * will not be persisted and not further inspected by the mapping framework.
     */
    @Transient
    private String organizationName;

    @Transient
    private String contactName;

    @Transient
    private String contactPhone;

    @Transient
    private String contactEmail;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "license_type", nullable = false)
    private String licenseType;

    @Column(name = "license_max", nullable = false)
    private Integer licenseMax;

    @Column(name = "license_allocated", nullable = false)
    private Integer licenseAllocated;

    @Column(name = "comment")
    private String comment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        License license = (License) o;
        return id.equals(license.id) && organisationId.equals(license.organisationId) && organizationName.equals(license.organizationName) && Objects.equals(contactName, license.contactName) && Objects.equals(contactPhone, license.contactPhone) && Objects.equals(contactEmail, license.contactEmail) && Objects.equals(productName, license.productName) && Objects.equals(licenseType, license.licenseType) && Objects.equals(licenseMax, license.licenseMax) && Objects.equals(licenseAllocated, license.licenseAllocated) && Objects.equals(comment, license.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, organisationId);
    }

    @Override
    public String toString() {
        return "License{" +
                "id=" + id +
                ", organisationId=" + organisationId +
                ", organizationName='" + organizationName + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", productName='" + productName + '\'' +
                ", licenseType='" + licenseType + '\'' +
                ", licenseMax=" + licenseMax +
                ", licenseAllocated=" + licenseAllocated +
                ", comment='" + comment + '\'' +
                '}';
    }
}
