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
package io.github.bonigarcia.webdriver.testng.ch9.data;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataNGTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = WebDriverManager.chromedriver().create();
    }

    @AfterMethod
    public void teardown() throws InterruptedException {
        // FIXME: pause for manual browser inspection
        Thread.sleep(Duration.ofSeconds(3).toMillis());

        driver.quit();
    }

    @Test
    public void testData() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/data-types.html");

        Faker faker = new Faker();

        driver.findElement(By.name("first-name"))
                .sendKeys(faker.name().firstName());
        driver.findElement(By.name("last-name"))
                .sendKeys(faker.name().lastName());
        driver.findElement(By.name("address"))
                .sendKeys(faker.address().fullAddress());
        driver.findElement(By.name("zip-code"))
                .sendKeys(faker.address().zipCode());
        driver.findElement(By.name("city")).sendKeys(faker.address().city());
        driver.findElement(By.name("country"))
                .sendKeys(faker.address().country());
        driver.findElement(By.name("e-mail"))
                .sendKeys(faker.internet().emailAddress());
        driver.findElement(By.name("phone"))
                .sendKeys(faker.phoneNumber().phoneNumber());
        driver.findElement(By.name("job-position"))
                .sendKeys(faker.job().position());
        driver.findElement(By.name("company")).sendKeys(faker.company().name());

        driver.findElement(By.cssSelector("button[type=submit]")).click();

        List<WebElement> successElement = driver
                .findElements(By.className("alert-success"));
        assertThat(successElement).hasSize(10);

        List<WebElement> errorElement = driver
                .findElements(By.className("alert-danger"));
        assertThat(errorElement).isEmpty();
    }

}