/*
 * (C) Copyright 2021 Boni Garcia (https://bonigarcia.github.io/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.github.bonigarcia.webdriver.jupiter.ch4.cookies;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

class ReadCookiesJupiterTest {

    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void teardown() throws InterruptedException {
        // FIXME: active wait for manual browser inspection
        Thread.sleep(Duration.ofSeconds(3).toMillis());

        driver.quit();
    }

    @Test
    void testReadCookies() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/cookies.html");

        Set<Cookie> cookies = driver.manage().getCookies();
        assertThat(cookies).hasSize(2);

        Cookie username = driver.manage().getCookieNamed("username");
        assertThat(username.getValue()).isEqualTo("John Doe");
        assertThat(username.getPath()).isEqualTo("/");

        driver.findElement(By.id("refresh-cookies")).click();
    }

}
