package com.suvorov.pages;

import com.suvorov.helpers.CommonlyUsedScenario;
import com.suvorov.helpers.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by vsuvorov on 12/5/16.
 */
public class InboxPage extends PageObject {

    public EmailDetailsPage clickTopNewMail(WebDriver driver) {
        clickElement(driver, By.xpath("//div[@class='y6'][1]/span/b"));
        return PageFactory.initElements(driver, EmailDetailsPage.class);
    }
}
