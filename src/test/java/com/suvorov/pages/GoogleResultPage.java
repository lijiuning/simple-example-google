package com.suvorov.pages;

import com.suvorov.helpers.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by vsuvorov on 12/6/16.
 */
public class GoogleResultPage extends PageObject {

    private static Logger LOGGER = LoggerFactory.getLogger(GoogleResultPage.class);

    public boolean areResultsShown(WebDriver driver) {
        waitUntilVisible(driver, By.xpath("//h3[@class='r']/a"));
        LOGGER.info("Quantity of results - " + driver.findElements(By.xpath("//h3[@class='r']/a")).size());
        return !driver.findElements(By.xpath("//h3[@class='r']/a")).isEmpty();
    }

    public String getDescriptionFromSideColumn(WebDriver driver) {
        waitUntilVisible(driver, By.xpath("//div[@class='_cgc']/div/div/span[1]"));
        return getElementText(driver, By.xpath("//div[@class='_cgc']/div/div/span[1]"));
    }

    public boolean isTextPresentOnPage(WebDriver driver, String expectedText) {
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + expectedText + "')]"));
        return (list.size() > 0 ? true : false);
    }
}
