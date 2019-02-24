package com.archer.selenium.demo;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class SeleniumDemo {

    private static final Logger logger = LoggerFactory.getLogger(SeleniumDemo.class);

    private static final String DRIVER_PATH = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "drvier\\chromedriver.exe";

    private static final String BROWSER_PATH = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";

    public static void main(String[] args) {

        ChromeDriverService chromeDriverService = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(DRIVER_PATH))
                .usingAnyFreePort()
                .build();

        ChromeOptions chromeOptions = new ChromeOptions().setBinary(new File(BROWSER_PATH));

        WebDriver webDriver = new ChromeDriver(chromeDriverService, chromeOptions);

        webDriver.get("http://www.baidu.com");

        WebElement element = webDriver.findElement(By.id("kw"));

        element.sendKeys("你好");

        element.submit();

        new WebDriverWait(webDriver, 10).until(new ExpectedCondition<Boolean>() {
            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                return webDriver.getTitle().toLowerCase().startsWith("你好");
            }
        });

        logger.info("Page is :{}", webDriver.getTitle());

        webDriver.close();

    }

}
