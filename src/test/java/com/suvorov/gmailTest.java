package com.suvorov;

import com.suvorov.categories.HighPriority;
import com.suvorov.categories.MediumPriority;
import com.suvorov.helpers.Utils;
import com.suvorov.pages.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by vsuvorov on 12/5/16.
 */
public class gmailTest {

    WebDriver driver;

    @Before
    public void setDriver() {
        String browserName = System.getenv("browser");
        System.setProperty("webdriver.gecko.driver",
                "/Users/vsuvorov/Documents/Development/MozillaGeckoDriver/geckodriver");
        if (browserName != null && browserName.equalsIgnoreCase("Chrome")) {
            //driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }
    }

    @Category({HighPriority.class})
    @Test
    public void gmailLogin() {
        MainPage mainPage = loginToGmail();
        Assert.assertTrue("User should be logged in now", mainPage.isUserLoggedIn(driver));
    }

    @Category({MediumPriority.class})
    @Test
    public void sendEmail() {
        MainPage mainPage = loginToGmail();
        NewMailPage newMailPage = mainPage.clickComposeBtn(driver);
        newMailPage.typeReceipient(driver, "vsautotesting@gmail.com");
        String expectedSubj = newMailPage.typeRandomSubject(driver, 50);
        String expectedBody = newMailPage.typeRandomEmailBody(driver, 350);
        mainPage = newMailPage.clickSendBtn(driver);
        InboxPage inboxPage = mainPage.clickInboxFolder(driver);
        EmailDetailsPage emailDetailsPage = inboxPage.clickTopNewMail(driver);
        String actualSubj=emailDetailsPage.getEmailActualSubject(driver);
        String actualBody=emailDetailsPage.getEmailActualBody(driver);
        Assert.assertEquals("Email should have our subject typed before", expectedSubj, actualSubj);
        Assert.assertEquals("Email should have our body typed before", expectedBody, actualBody);
    }

    @Category({MediumPriority.class})
    @Test
    public void gmailLogout() {
        MainPage mainPage = loginToGmail();
        mainPage.clickProfileBtn(driver);
        LoginPage loginPage = mainPage.clickLogOutBtn(driver);
        Assert.assertTrue("User should be NOT logged in", loginPage.isUserLoggedout(driver));
    }

    private MainPage loginToGmail() {
        LoginPage loginPage = Utils.openGmailPage(driver);
        loginPage.typeUserName(driver, "vsautotesting@gmail.com");
        loginPage.clickNextBtn(driver);
        loginPage.typeUserPass(driver, "vsautotesting123");
        return loginPage.clickSignInBtn(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}