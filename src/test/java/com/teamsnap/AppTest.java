package com.teamsnap;

import config.BrowserStackConfig;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;


public class AppTest extends BrowserStackConfig {

    private final SoftAssert assertion = new SoftAssert();

    @Test(description = "Android - Click on login button")
    public void clickLoginButtonTest() {
        LoginPage loginPage = new LoginPage(appiumDriver);

        loginPage
                .clickLogin();
    }
}

