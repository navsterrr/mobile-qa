
package pages.android;

import static org.junit.Assert.assertTrue;
import java.util.List;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import com.teamsnap.AppTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage extends LoadableComponent<HomePage> {

    private final AppiumDriver<MobileElement> appiumDriver;
    
    public HomePage(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }

    public MobileElement scheduleTab() {
        return appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Schedule\")"));
    }

    public MobileElement scheduleGrid() {
        return appiumDriver.findElement(
                MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.view.View\").instance(4)"));
    }

    public List<MobileElement> scheduleRows() {
        return scheduleGrid().findElements(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"schedule_row_container\")"));
    }

    public MobileElement btnCreate() {
        return appiumDriver.findElement(
                MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(2)"));
    }

    public MobileElement btnNewEvent() {
        return appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"New Event\")"));
    }

    public NewEventPage newEventPage() {
        return new NewEventPage(appiumDriver).get();
    }

    public MobileElement teamNameEl(String teamName) {
        return appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + teamName + "\")"));
    }

    @Override
    protected void load() {
        // navigate to Home Page
    }

    @Override
    protected void isLoaded() throws Error {
        assertTrue("Home Page is visible", teamNameEl(AppTest.teamName).isDisplayed());
    }

}
