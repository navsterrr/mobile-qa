package com.teamsnap;

import config.BrowserStackConfig;
import config.LocalAppiumConfig;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import pages.android.HomePage;
import pages.android.LoginPage;
import pages.android.NewEventPage;

// public class AppTest extends BrowserStackConfig {
public class AppTest extends LocalAppiumConfig {

    private final SoftAssert assertion = new SoftAssert();

    private final String username = "qa-interview+navin@teamsnap.com";
    private final String password = "NavinInterview2024!";

    public final static String teamName = "Navin Tennis Team";

    private LoginPage login;
    private HomePage home;
    private NewEventPage event;

    // @Test(dataProvider = "devices", threadPoolSize = 2)
    @Test
    public void testCreateRepeatingEvent() throws InterruptedException {

        Login(); // initializes HomePage

        // click "Schedule" tab at bottom bar
        home.scheduleTab().click();

        // Click Create (+) button
        home.btnCreate().click();

        // Click Add New Event
        home.btnNewEvent().click();

        event = home.newEventPage();

        // Set event name
        final String eventName = "Monday practice";
        event.txtEventName().sendKeys(eventName);

        // Click Date Dropdown
        event.dateDropdown().click();

        // Wait for Calendar popup
        new WebDriverWait(appiumDriver, 10)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(MobileBy.id("com.teamsnap.teamsnap:id/ok")));

        // Scroll down by one month to next month
        scrollDownOneMonth(event.calendar());

        // Select day of month
        event.startDateEl().click();

        // click OK on calendar to set date
        event.btnOK.click();

        // Click time dropdown
        event.timeDropdown().click();

        // Wait for Time popup
        new WebDriverWait(appiumDriver, 10)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(MobileBy.id("com.teamsnap.teamsnap:id/ok")));

        // Time Widget interaction works locally, but failing in Browserstack with
        // "UnsupportedCommand actions"

        // set hour = 6
        // appiumDriver.findElement(MobileBy.id("com.teamsnap.teamsnap:id/hours"))
        tapXY(539, 1355);

        Thread.sleep(1 * 1000);

        // set minutes = 00
        // appiumDriver.findElement(MobileBy.id("com.teamsnap.teamsnap:id/minutes"))
        tapXY(539, 869);

        Thread.sleep(1 * 1000);

        // set AM/PM
        // appiumDriver.findElement(MobileBy.id("com.teamsnap.teamsnap:id/ampm_label"))
        tapXY(769, 1485);

        // Click OK btn to save time
        event.btnOK.click();

        // click Repeat frequency dropdown to expand list options
        event.repeatFrequency.click();

        Thread.sleep(1 * 1000);

        // select Weekly from dropdown
        tapXY(163, 1547);

        // set Repeats Until date
        event.repeatUntil().click();

        // Select day in current month (no scrolling)
        event.endDateEl().click();

        // click OK on calendar to set date
        event.btnOK.click();

        // click Location dropdown
        event.location().click();

        Thread.sleep(1 * 1000);

        // select New Location from list
        tapXY(342, 1639);

        // set Location Name
        event.locationName().sendKeys("SMHS Soccer field");

        // set Location Address
        event.locationAddress().sendKeys("123 Main St, Los Angeles, CA");

        // click Save checkmark button on Location screen
        event.btnSaveCheckmark.click();

        // wait for Location page to save and close
        Thread.sleep(1000);

        // click Save checkmark button on New Event screen
        event.btnSaveCheckmark.click();

        // Wait for Schedule tab to appear
        new WebDriverWait(appiumDriver, 10)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(
                                MobileBy.AndroidUIAutomator("new UiSelector().text(\"Schedule\")")));

        // click "Schedule" tab at bottom bar
        home.scheduleTab().click();

    }

    @Test(dependsOnMethods = { "testCreateRepeatingEvent" })
    void testSchedule() {

        Login(); // initializes HomePage

        // click "Schedule" tab at bottom bar
        home.scheduleTab().click();

        assertion.assertEquals(home.scheduleRows().size(), 5, "Expected 5 weekly events repeating");

        List<Integer> daysExp = Arrays.asList(1, 8, 15, 22, 29); // event repeats on these dates
        List<Integer> daysAct = new ArrayList<>();

        for (MobileElement row : home.scheduleRows()) {

            List<MobileElement> cells = row.findElements(MobileBy.className("android.widget.TextView"));

            daysAct.add(Integer.parseInt(cells.get(0).getText()));

            assertion.assertEquals(cells.get(1).getText(), "MON",
                    "Day " + cells.get(0).getText() + ": Expected day to be MON");
            assertion.assertEquals(cells.get(2).getText(), "6:00 PM  (CDT)",
                    "Day " + cells.get(0).getText() + ": Expected time to be 6:00 PM  (CDT)");
            assertion.assertEquals(cells.get(3).getText(), "Monday practice",
                    "Day " + cells.get(0).getText() + ": Expected title to be Monday practice");
            assertion.assertEquals(cells.get(4).getText(), "SMHS Soccer field",
                    "Day " + cells.get(0).getText() + ": Expected location to contain SMHS Soccer field");

        }
        assertion.assertEquals(daysAct, daysExp, "Expected days to be " + daysExp);

        assertion.assertAll();
    }

    void Login() {
        login = new LoginPage(appiumDriver).get();

        home = login.doLogin(username, password);

        assertion.assertTrue(home.teamNameEl(teamName).isDisplayed(), "Successful login");

        // click on Team Name
        home.teamNameEl(teamName).click();

        // Handle Allow notifications popup
        login.btnAllowNotifications.click();
    }

    void tapXY(int x, int y) {
        // works locally. fails on Browserstack with "UnsupportedCommand actions"

        final PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Point tapPoint = new Point(x, y);
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        appiumDriver.perform(Arrays.asList(tap));
    }

    void scrollDownOneMonth(MobileElement el) {
        // Using Appium command. One can provide arguments such as direction, element,
        // and velocity
        // Note: Available since Appium v1.19
        JavascriptExecutor js = (JavascriptExecutor) appiumDriver;
        Map<String, Object> params = new HashMap<>();
        params.put("elementId", el.getId());
        params.put("direction", "down");
        params.put("percent", 0.5);
        js.executeScript("mobile: scrollGesture", params);

    }
    
    /*
     * void scrollDownOneMonth() {
     * // works locally. fails on Browserstack with "UnsupportedCommand actions"
     * 
     * final PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,
     * "finger");
     * Point start = new Point(489, 1633);
     * Point end = new Point(524, 910);
     * Sequence swipe = new Sequence(finger, 1);
     * swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
     * PointerInput.Origin.viewport(), start.getX(), start.getY()));
     * swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg(
     * )));
     * swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000),
     * PointerInput.Origin.viewport(), end.getX(), end.getY()));
     * swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())
     * );
     * appiumDriver.perform(Arrays.asList(swipe));
     * 
     * }
     * 
     * //     https://www.browserstack.com/docs/app-automate/appium/advanced-features/appium-gestures
     * void scrollDownOneMonth() {
     * // UnsupportedCommand The requested resource could not be found, or a request
     * was received using an HTTP method that is not supported by the mapped
     * resource
     * 
     * // Get screen dimensions
     * org.openqa.selenium.Dimension dimensions =
     * appiumDriver.manage().window().getSize();
     * 
     * // Set the X coordinate for the scroll element
     * int location_x = (int) (dimensions.width * 0.5);
     * 
     * // Set start and end Y coordinates for the scroll direction (up or down)
     * int location_start_y = (int) (dimensions.height * 0.6);
     * int location_end_y = (int) (dimensions.height * 0.3);
     * 
     * // Perform vertical scroll gesture using TouchAction API
     * TouchAction<?> touchAction = new TouchAction<>(appiumDriver);
     * touchAction.press(PointOption.point(location_x, location_start_y))
     * .waitAction(WaitOptions.waitOptions(java.time.Duration.ofMillis(1000)))
     * .moveTo(PointOption.point(location_x, location_end_y)).release().perform();
     * 
     * }
     */

}
