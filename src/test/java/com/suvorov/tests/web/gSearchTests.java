package com.suvorov.tests.web;

import com.suvorov.categories.HighPriority;
import com.suvorov.helpers.CommonlyUsedScenario;
import com.suvorov.pages.BaiduInitPage;
import com.suvorov.pages.BaiduResultPage;
import com.suvorov.pages.GoogleInitPage;
import com.suvorov.pages.GoogleResultPage;
import com.suvorov.selenium.DriverFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by vsuvorov on 12/6/16.
 */
public class gSearchTests {
    WebDriver driver;

    @Before
    public void setDriver() {
        driver = new DriverFactory().setDriver("chrome");
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

    @Category({HighPriority.class})
    @Test
    public void simpleBaiduSearch() {

        //User visits baidu.com
        BaiduInitPage baiduInitPage = CommonlyUsedScenario.openBaidu(driver);

        //The user enters the search term `batman`
        baiduInitPage.typeString(driver, "12306");

        //The user clicks ‘search’
        BaiduResultPage baiduResultPage = baiduInitPage.clickSearchBtn(driver);

        //Validate that there are results of the search
        boolean areResults = baiduResultPage.areResultsShown(driver);
        Assert.assertTrue("找到结果", areResults);

        //Validate that the text appears on the page (somewhere)
        String expectedText = "全国铁路统一电话订票号码--官方唯一电话订票渠道";
        boolean isTextPresent = baiduResultPage.isTextPresentOnPage(driver, expectedText);
        Assert.assertTrue("找到官网了", isTextPresent);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
