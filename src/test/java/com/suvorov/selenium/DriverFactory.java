package com.suvorov.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by vsuvorov on 12/28/16.
 */
public class DriverFactory {

    ThreadLocal<WebDriver> driver;

    public DriverFactory() {
        driver = new ThreadLocal<WebDriver>();
    }

    private WebDriver getInstance() {
        return driver.get();
    }

    public WebDriver setDriver(String browser) {
        String osName = (System.getProperty("os.name").toLowerCase().contains("mac") ? "mac" : "windows");
        String pathToDriver;
        String propertyName;
        String driverFile;
        if (browser.equals("firefox")) {
            pathToDriver = "repo" + File.separator + "MozillaGeckoDriver" + File.separator;
            propertyName = "webdriver.gecko.driver";
            driverFile = (osName.equals("windows") ? "geckodriver.exe" : "geckodriver");

            System.setProperty(propertyName, pathToDriver + driverFile);
            driver.set(new FirefoxDriver());
        } else if (browser.equals("chrome")) {
            pathToDriver = "repo" + File.separator + "ChromeDriver" + File.separator;
            propertyName = "webdriver.chrome.driver";
            driverFile = (osName.equals("windows") ? "chromedriver.exe" : "chromedriver");

            System.setProperty(propertyName, pathToDriver + driverFile);
            driver.set(new ChromeDriver());
        }
        return getInstance();
    }
}