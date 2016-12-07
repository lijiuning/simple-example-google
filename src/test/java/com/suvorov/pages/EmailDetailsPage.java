package com.suvorov.pages;

import com.suvorov.helpers.CommonlyUsedScenario;
import com.suvorov.helpers.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by vsuvorov on 12/5/16.
 */
public class EmailDetailsPage extends PageObject {

    public String getEmailActualSubject(WebDriver driver) {
        return getElementValue(driver, By.cssSelector("h2[class='hP']"));
    }

    public String getEmailActualBody(WebDriver driver) {
        return getElementValue(driver, By.cssSelector("div[class='gs'] div div div[dir='ltr']"));
    }
}
