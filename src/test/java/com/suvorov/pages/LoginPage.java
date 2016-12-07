package com.suvorov.pages;

import com.suvorov.helpers.CommonlyUsedScenario;
import com.suvorov.helpers.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by vsuvorov on 12/5/16.
 */
public class LoginPage extends PageObject {
    public void typeUserName(WebDriver driver, String s) {
        setTextBoxValue(driver, By.id("Email"), s);
    }

    public void clickNextBtn(WebDriver driver) {
        clickElement(driver, By.id("next"));
    }

    public void typeUserPass(WebDriver driver, String s) {
        setTextBoxValue(driver, By.id("Passwd"), s);
    }

    public MainPage clickSignInBtn(WebDriver driver) {
        clickElement(driver, By.id("signIn"));
        return PageFactory.initElements(driver, MainPage.class);
    }

    public boolean isUserLoggedout(WebDriver driver) {
        return isElementVisible(driver, By.cssSelector("div[class*='google-header-bar'][class*='centered']"));
    }
}
