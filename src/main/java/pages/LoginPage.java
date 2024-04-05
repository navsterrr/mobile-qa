package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import utils.Actions;

public class LoginPage {
    private final AppiumDriver<MobileElement> appiumDriver;
    private final Actions action;

    private MobileElement loginButton;

    public LoginPage(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        this.action = new Actions(appiumDriver);
        initElements();
    }

    private void initElements() {
        loginButton = appiumDriver.findElementById("button_log_in");
    }

    public LoginPage clickLogin() {
        action.click(loginButton);
        return this;
    }

}
