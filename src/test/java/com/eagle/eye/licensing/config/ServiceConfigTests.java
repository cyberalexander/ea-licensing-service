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

package com.eagle.eye.licensing.config;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Created : 06/04/2023 09:05
 * Project : ea-licensing-service
 * IDE : IntelliJ IDEA
 *
 * @author CyberAlexander
 * @version 1.0
 */
@Slf4j
@SpringBootTest
@ActiveProfiles({"test"})
class ServiceConfigTests {

    @Autowired
    private ServiceConfig serviceConfig;

    @Test
    void testGetExampleProperty() {
        log.info("ExampleProperty: [{}]", serviceConfig.getExampleProperty());
        Assertions.assertThat(serviceConfig.getExampleProperty()).isNotNull();
    }

    @Test
    void testGetExampleProperty_ValueTakenFromTestProperties() {
        log.info("ExampleProperty: [{}]", serviceConfig.getExampleProperty());
        Assertions.assertThat(serviceConfig.getExampleProperty()).isEqualTo("The value taken from test properties.");
    }
}