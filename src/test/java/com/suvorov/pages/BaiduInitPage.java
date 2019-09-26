package com.suvorov.pages;

import com.suvorov.helpers.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by vsuvorov on 12/6/16.
 */
public class BaiduInitPage extends PageObject {

    public void typeString(WebDriver driver, String s) {
        setTextBoxValue(driver, By.id("kw"), s);
    }

    public BaiduResultPage clickSearchBtn(WebDriver driver) {
        clickElement(driver, By.id("su"));
        return PageFactory.initElements(driver, BaiduResultPage.class);
    }
}
