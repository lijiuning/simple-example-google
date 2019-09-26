package com.suvorov.helpers;

import com.suvorov.pages.BaiduInitPage;
import com.suvorov.pages.GoogleInitPage;
import com.suvorov.pages.LoginPage;
import com.suvorov.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by vsuvorov on 12/5/16.
 */
public class CommonlyUsedScenario {

    public static MainPage loginToGmail(WebDriver driver) {
        driver.get("http://gmail.com");
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.typeUserName(driver, "vsautotesting@gmail.com");
        loginPage.clickNextBtn(driver);
        loginPage.typeUserPass(driver, "vsautotesting123");
        return loginPage.clickSignInBtn(driver);
    }

    public static GoogleInitPage openGoogle(WebDriver driver) {
        driver.get("http://google.com");
        return PageFactory.initElements(driver, GoogleInitPage.class);
    }

    public static BaiduInitPage openBaidu(WebDriver driver) {
        driver.get("http://baidu.com");
        return PageFactory.initElements(driver, BaiduInitPage.class);
    }
}
