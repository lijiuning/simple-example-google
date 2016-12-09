package com.suvorov;

import com.suvorov.categories.HighPriority;
import com.suvorov.helpers.CommonlyUsedScenario;
import com.suvorov.pages.GoogleInitPage;
import com.suvorov.pages.GoogleResultPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by vsuvorov on 12/6/16.
 */
public class googleSearchTests {
    WebDriver driver;

    @Before
    public void setDriver() {
        String browserName = System.getenv("browser");
        System.setProperty("webdriver.gecko.driver", "./repo/MozillaGeckoDriver/geckodriver");
        if (browserName != null && browserName.equalsIgnoreCase("Chrome")) {
            //driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }
    }

    @Category({HighPriority.class})
    @Test
    public void simpleSearch() {

        //User visits google.com
        GoogleInitPage googleInitPage = CommonlyUsedScenario.openGoogle(driver);

        //The user enters the search term `batman`
        googleInitPage.typeString(driver, "batman");

        //The user clicks ‘search’
        GoogleResultPage googleResultPage = googleInitPage.clickSearchBtn(driver);

        //Validate that there are results of the search
        boolean areResults = googleResultPage.areResultsShown(driver);
        Assert.assertTrue("Some results must be shown on the page", areResults);

        //Validate that the text appears on the page (somewhere)
        String expectedText = "Batman is a fictional superhero appearing in American comic books " +
                "published by DC Comics. The character was created by artist Bob Kane and writer " +
                "Bill Finger, and first appeared in Detective Comics #27";
        boolean isTextPresent = googleResultPage.isTextPresentOnPage(driver, expectedText);
        Assert.assertTrue("Text appears on the page", isTextPresent);

        //Validate that the text appears on the page (expected place - side column)
        String textFromSideColumn = googleResultPage.getDescriptionFromSideColumn(driver);
        Assert.assertTrue("Text is present in column on the right", textFromSideColumn.contains(expectedText));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
