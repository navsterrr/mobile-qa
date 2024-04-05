package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.logging.log4j.LogManager;


public class Actions {
    private final AppiumDriver<?> appiumDriver;
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Actions.class);

    public Actions(AppiumDriver<?> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void click(MobileElement element) {
        element.click();
    }

}
