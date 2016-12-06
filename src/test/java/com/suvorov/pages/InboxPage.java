package com.suvorov.pages;

import com.suvorov.helpers.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by vsuvorov on 12/5/16.
 */
public class InboxPage {

    public EmailDetailsPage clickTopNewMail(WebDriver driver) {
        Utils.clickElement(driver, By.xpath("//div[@class='y6'][1]/span/b"));
        return PageFactory.initElements(driver, EmailDetailsPage.class);
    }
}
