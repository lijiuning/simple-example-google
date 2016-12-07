package com.suvorov.pages;

import com.suvorov.helpers.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by vsuvorov on 12/5/16.
 */
public class MainPage extends PageObject {

    private static Logger LOGGER = LoggerFactory.getLogger(MainPage.class);

    public boolean isUserLoggedIn(WebDriver driver) {
        return isElementVisible(driver, By.cssSelector("div[role='button'][gh='cm']"));
    }

    public NewMailPage clickComposeBtn(WebDriver driver) {
        clickElement(driver, By.cssSelector("div[role='button'][gh='cm']"));
        return PageFactory.initElements(driver, NewMailPage.class);
    }

    public InboxPage clickInboxFolder(WebDriver driver) {
        waitForRefresh(driver, By.xpath("//a[contains(@title,'Inbox')]"));
        clickElement(driver, By.xpath("//a[contains(@title,'Inbox')]"));
        return PageFactory.initElements(driver, InboxPage.class);
    }

    public void clickProfileBtn(WebDriver driver) {
        clickElement(driver, By.cssSelector("a[href*='SignOut'] span"));
    }

    public LoginPage clickLogOutBtn(WebDriver driver) {
        clickElement(driver, By.cssSelector("a[href*='Logout']"));
        return PageFactory.initElements(driver, LoginPage.class);
    }

    public int getMailsQty(WebDriver driver) {
        int qty;
        By by = By.xpath("//div[@role]/span[@class='Dj'][1]/span[3]");
        try {
            waitForRefresh(driver, by);
            qty = Integer.parseInt(driver.findElement(By.xpath("//div[@role]/span[@class='Dj'][1]/span[3]")).getText());
        } catch (Exception e) {
            qty = 0;
        }
        LOGGER.info("Quantity of messages - " + qty);
        return qty;
    }
}
