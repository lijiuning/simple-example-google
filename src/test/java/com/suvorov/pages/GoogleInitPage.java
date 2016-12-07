package com.suvorov.pages;

import com.suvorov.helpers.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by vsuvorov on 12/6/16.
 */
public class GoogleInitPage extends PageObject {

    public void typeString(WebDriver driver, String s) {
        setTextBoxValue(driver, By.id("lst-ib"), s);
    }

    public GoogleResultPage clickSearchBtn(WebDriver driver) {
        clickElement(driver, By.name("btnG"));
        return PageFactory.initElements(driver, GoogleResultPage.class);
    }
}
