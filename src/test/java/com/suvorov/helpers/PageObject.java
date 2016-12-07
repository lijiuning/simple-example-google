package com.suvorov.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

/**
 * Created by vsuvorov on 12/6/16.
 */
public class PageObject {
    private final int TIME_OUT_IN_SECONDS = 15;

    public void waitUntilVisible(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public String setTextBoxValue(WebDriver driver, By by, String s) {
        waitUntilVisible(driver, by);
        WebElement e = driver.findElement(by);
//        e.clear();
        e.sendKeys(s);
        return s;
    }

    public String setRandomTextBoxValue(WebDriver driver, By by, int maxStringLength) {
        waitUntilVisible(driver, by);
        WebElement e = driver.findElement(by);
        String typedString = randomString(maxStringLength);
        e.sendKeys(typedString);
        return typedString;
    }

    private String randomString(int maxStringLength) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz !№;%:?*()_=+/*-.,`".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length, isRandomStringValid;
        String resultString = "";

        do {
            //length should not be equal to 0
            length = random.nextInt(maxStringLength) + 1;
            for (int i = 0; i < length; i++) {
                sb.append(chars[random.nextInt(chars.length)]);
            }
            resultString = sb.toString();

            int counter = 0;
            //we consider that the string is valid by default
            isRandomStringValid = 1;
            for (int i = 0; i < resultString.length(); i++) {
                if (resultString.charAt(i) == ' ') {
                    counter++;
                }
            }
            if (resultString.length() == counter) {
                //but if the generated string contains only spaces then regenerate it
                isRandomStringValid = 0;
            }
        } while (isRandomStringValid < 1);
        return resultString.trim();
    }

    public void clickElement(WebDriver driver, By by) {
        waitUntilVisible(driver, by);
        WebElement e = driver.findElement(by);
        e.click();
    }

    public boolean isElementVisible(WebDriver driver, By by) {
        waitUntilVisible(driver, by);
        return driver.findElements(by).size() > 0;
    }

    public String getElementText(WebDriver driver, By by) {
        waitUntilVisible(driver, by);
        return driver.findElement(by).getText();
    }

    public void waitForRefresh(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(by)));
    }

    public boolean isElementPresent(WebDriver driver, By by) {
        return !driver.findElements(by).isEmpty();
    }
}
