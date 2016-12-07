package com.suvorov.pages;

import com.suvorov.helpers.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by vsuvorov on 12/6/16.
 */
public class NewMailPage {
    public void typeReceipient(WebDriver driver, String s) {
        Utils.setTextBoxValue(driver, By.name("to"), s);
    }

    public String typeRandomSubject(WebDriver driver, int maxStringLength) {
        return Utils.setRandomTextBoxValue(driver, By.name("subjectbox"), maxStringLength);
    }

    public String typeRandomEmailBody(WebDriver driver, int maxStringLength) {
        return Utils.setRandomTextBoxValue(driver, By.cssSelector("div[aria-label='Message Body']"), maxStringLength);
    }

    public MainPage clickSendBtn(WebDriver driver) {
        Utils.clickElement(driver, By.cssSelector("div[aria-label*='Send'"));
        return PageFactory.initElements(driver, MainPage.class);
    }
}
