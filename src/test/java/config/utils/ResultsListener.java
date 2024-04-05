package config.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.JavascriptExecutor;

public class ResultsListener {
    private JavascriptExecutor js;

    public ResultsListener(JavascriptExecutor js) {
        this.js = js;
    }

    private JavascriptExecutor execJS(AppiumDriver<?> appiumDriver) {
        js = (JavascriptExecutor) appiumDriver;
        return js;
    }

    public void passResult(AppiumDriver<?> appiumDriver) {
        execJS(appiumDriver).executeScript("browserstack_executor:" +
                " {\"action\": \"setSessionStatus\"," +
                " \"arguments\": {\"status\": \"passed\", \"reason\": \"Successful test\"}}");
    }

    public void failResult(AppiumDriver<?> appiumDriver) {
        execJS(appiumDriver).executeScript("browserstack_executor:" +
                " {\"action\": \"setSessionStatus\"," +
                " \"arguments\": {\"status\":\"failed\", \"reason\": \"Results not found\"}}");
    }
}
