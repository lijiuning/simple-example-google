package com.suvorov.pages;

import com.suvorov.helpers.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by vsuvorov on 12/5/16.
 */
public class EmailDetailsPage {

    public String getEmailActualSubject(WebDriver driver) {
        return Utils.getElementValue(driver, By.cssSelector("h2[class='hP']"));
    }

    public String getEmailActualBody(WebDriver driver) {
        return Utils.getElementValue(driver, By.cssSelector("div[class='gs'] div div div[dir='ltr']"));
    }
}
