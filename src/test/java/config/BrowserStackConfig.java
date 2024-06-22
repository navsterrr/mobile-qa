package config;

import config.utils.ConfigReader;
import config.utils.ResultsListener;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BrowserStackConfig {
    protected AppiumDriver<MobileElement> appiumDriver;

    @BeforeMethod(alwaysRun = true)
    @Parameters("platform")
    public void setUp(String platform, ITestResult testMethod) throws Exception {

        Map<String, Object> config = readConfigFromFile();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        ConfigReader reader = new ConfigReader();

        String device;
        String os;
        String app;

        switch (platform) {
            case "android":
                app = (String) config.get("androidApp");
                device = (String) config.get("androidDevice");
                os = (String) config.get("androidVersion");
                break;
            case "ios":
                device = (String) config.get("iOSDevice");
                os = (String) config.get("iOSVersion");
                app = (String) config.get("iosApp");
                break;
            default:
                throw new IllegalArgumentException("Unsupported OS: " + platform);
        }

        // capabilities.setCapability("device", device);
        // capabilities.setCapability("os_version", os);
        // capabilities.setCapability("platformName", platform);
        capabilities.setCapability("app", app);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
        // capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 3 * 60); //extra time for DEBUGGING
        capabilities.setCapability("idleTimeout", "1000");
        capabilities.setCapability("settings[enableMultiWindows]", true);
        capabilities.setCapability("interactiveDebugging", true);
        capabilities.setCapability("browserstack.debug", true);
        capabilities.setCapability("video", true);  //must be true for interactiveDebugging=true

        
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platform);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, os);
        // // final String udid = "emulator-5554";
        // capabilities.setCapability(MobileCapabilityType.UDID, udid);
        // capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
        // capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);

        String url = reader.urlBrowserStack(testMethod); // Browserstack URL for Appium server

        if ("android".equals(platform)) {
            appiumDriver = new AppiumDriver<>(new URL(url), capabilities);
        } else {
            appiumDriver = new IOSDriver<>(new URL(url), capabilities);
        }
        appiumDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    private Map<String, Object> readConfigFromFile() throws IOException {
        Yaml yaml = new Yaml();
        try (FileReader fileReader = new FileReader("browserstack.yml")) {
            return yaml.load(fileReader);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult testResult) throws Exception {
        ResultsListener result = new ResultsListener(appiumDriver);
        if (ITestResult.SUCCESS == testResult.getStatus()) {
            result.passResult(appiumDriver);
            Thread.sleep(3000);
        } else {
            result.failResult(appiumDriver);
            Thread.sleep(3000);
        }
        appiumDriver.quit();
    }
}
