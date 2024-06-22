
package pages.android;

import static org.junit.Assert.assertTrue;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class NewEventPage extends LoadableComponent<NewEventPage> {

    private final AppiumDriver<MobileElement> appiumDriver;

    @AndroidFindBy(id = "com.teamsnap.teamsnap:id/ok")
    public MobileElement btnOK; // OK button on Date & Time widgets

    @AndroidFindBy(id = "com.teamsnap.teamsnap:id/action_save")
    public MobileElement btnSaveCheckmark; //Save checkmark on Event & Location pages

    @AndroidFindBy(id = "com.teamsnap.teamsnap:id/validationTextInputLayout_schedule_repeat_frequency")
    public MobileElement repeatFrequency;
    
    final String startDate = "01 July 2024";
    final String endDate = "29 July 2024";

    public NewEventPage(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;

        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }

    public MobileElement txtEventName() {
        return appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Event Name\")"));
    }

    public MobileElement dateDropdown() {
        return appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Date\")"));
    }

    public MobileElement calendar() {
        return appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.view.View\").instance(0)"));
    }

    public MobileElement startDateEl() {
        return appiumDriver
                .findElement(MobileBy.AndroidUIAutomator("new UiSelector().description(\"" + startDate + "\")"));
    }

    public MobileElement timeDropdown() {
        return appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Time\")"));
    }

    public MobileElement repeatUntil() {
        return appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Repeats Until\")"));
    }

    public MobileElement endDateEl() {
        return appiumDriver
                .findElement(MobileBy.AndroidUIAutomator("new UiSelector().description(\"" + endDate + "\")"));
    }

    public MobileElement location() {
        return appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Location\")"));
    }

    public MobileElement locationName() {
        return appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Location Name\")"));
    }

    public MobileElement locationAddress() {
        return appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Address\")"));
    }

    @Override
    protected void load() {
        // navigate to New Event Page
    }

    @Override
    protected void isLoaded() throws Error {
        assertTrue("Event Page is visible", txtEventName().isDisplayed());
    }

}
