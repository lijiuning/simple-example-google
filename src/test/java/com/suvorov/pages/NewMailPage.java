package com.suvorov.pages;

import com.suvorov.helpers.CommonlyUsedScenario;
import com.suvorov.helpers.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by vsuvorov on 12/6/16.
 */
public class NewMailPage extends PageObject {
    public void typeReceipient(WebDriver driver, String s) {
        setTextBoxValue(driver, By.name("to"), s);
    }

    public String typeRandomSubject(WebDriver driver, int maxStringLength) {
        return setRandomTextBoxValue(driver, By.name("subjectbox"), maxStringLength);
    }

    public String typeRandomEmailBody(WebDriver driver, int maxStringLength) {
        return setRandomTextBoxValue(driver, By.cssSelector("div[aria-label='Message Body']"), maxStringLength);
    }

    public MainPage clickSendBtn(WebDriver driver) {
        clickElement(driver, By.cssSelector("div[aria-label*='Send'"));
        return PageFactory.initElements(driver, MainPage.class);
    }
}
