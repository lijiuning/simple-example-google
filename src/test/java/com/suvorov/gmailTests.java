package com.suvorov;

import com.suvorov.categories.HighPriority;
import com.suvorov.categories.MediumPriority;
import com.suvorov.helpers.CommonlyUsedScenario;
import com.suvorov.pages.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by vsuvorov on 12/5/16.
 */
public class gmailTests {

    WebDriver driver;

    @Before
    public void setDriver() {
        String browserName = System.getenv("browser");
        System.setProperty("webdriver.gecko.driver",
                "/Users/vsuvorov/Documents/Programming/MozillaGeckoDriver/geckodriver");
        if (browserName != null && browserName.equalsIgnoreCase("Chrome")) {
            //driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }
    }

    @Category({HighPriority.class})
    @Test
    public void gmailLogin() {
        MainPage mainPage = CommonlyUsedScenario.loginToGmail(driver);
        Assert.assertTrue("User should be logged in now", mainPage.isUserLoggedIn(driver));
    }

    @Category({MediumPriority.class})
    @Test
    public void sendEmail() {

        //login and check quantity of mails in box
        MainPage mainPage = CommonlyUsedScenario.loginToGmail(driver);
        int mailsQty = mainPage.getMailsQty(driver);

        //compose and send a new message
        NewMailPage newMailPage = mainPage.clickComposeBtn(driver);
        newMailPage.typeReceipient(driver, "vsautotesting@gmail.com");
        String expectedSubj = newMailPage.typeRandomSubject(driver, 50);
        String expectedBody = newMailPage.typeRandomEmailBody(driver, 350);
        mainPage = newMailPage.clickSendBtn(driver);

        //click Inbox button until we get new message
        InboxPage inboxPage;
        int newMailsQty;
        do {
            inboxPage = mainPage.clickInboxFolder(driver);
            newMailsQty = mainPage.getMailsQty(driver);
        } while (newMailsQty == mailsQty);

        //check the details of new message
        EmailDetailsPage emailDetailsPage = inboxPage.clickTopNewMail(driver);
        String actualSubj = emailDetailsPage.getEmailActualSubject(driver);
        String actualBody = emailDetailsPage.getEmailActualBody(driver);
        Assert.assertEquals("Email should have a subject typed before", expectedSubj, actualSubj);
        Assert.assertEquals("Email should have a body message typed before", expectedBody, actualBody);
    }

    @Category({MediumPriority.class})
    @Test
    public void gmailLogout() {
        MainPage mainPage = CommonlyUsedScenario.loginToGmail(driver);
        mainPage.clickProfileBtn(driver);
        LoginPage loginPage = mainPage.clickLogOutBtn(driver);
        Assert.assertTrue("User should be NOT logged in", loginPage.isUserLoggedout(driver));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}