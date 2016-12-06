package com.suvorov.pages;

//import com.sun.org.apache.xpath.internal.operations.String;
import com.suvorov.helpers.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by vsuvorov on 12/5/16.
 */
public class MainPage {


    public boolean isUserLoggedIn(WebDriver driver) {
        return Utils.isElementVisible(driver, By.cssSelector("div[role='button'][gh='cm']"));
    }

    public void clickComposeBtn(WebDriver driver) {
        Utils.clickElement(driver, By.cssSelector("div[role='button'][gh='cm']"));
    }

    public void typeReceipient(WebDriver driver, String s) {
        Utils.setTextBoxValue(driver, By.name("to"), s);
    }

    public String typeRandomSubject(WebDriver driver, int maxStringLength) {
        return Utils.setRandomTextBoxValue(driver, By.name("subjectbox"), maxStringLength);
    }

    public String typeRandomEmailBody(WebDriver driver, int maxStringLength) {
        return Utils.setRandomTextBoxValue(driver, By.cssSelector("div[aria-label='Message Body']"), maxStringLength);
    }

    public void clickSendBtn(WebDriver driver) {
        Utils.clickElement(driver, By.cssSelector("div[aria-label*='Send'"));
    }

    public InboxPage clickInboxFolder(WebDriver driver) {
        Utils.clickElement(driver, By.partialLinkText("Inbox"));
        return PageFactory.initElements(driver, InboxPage.class);
    }

    public void clickProfileBtn(WebDriver driver) {
        Utils.clickElement(driver, By.cssSelector("a[href*='SignOut'] span"));
    }

    public LoginPage clickLogOutBtn(WebDriver driver) {
        Utils.clickElement(driver, By.cssSelector("a[href*='Logout']"));
        return PageFactory.initElements(driver, LoginPage.class);
    }
}
