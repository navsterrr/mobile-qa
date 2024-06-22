package pages.android;

import static org.junit.Assert.assertTrue;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage extends LoadableComponent<LoginPage> {

    private final AppiumDriver<MobileElement> appiumDriver;

    @AndroidFindBy(id = "com.teamsnap.teamsnap:id/button_log_in")
    public MobileElement btnLogin;

    @AndroidFindBy(id = "com.teamsnap.teamsnap:id/login_email_input")
    // redundant if element name is named after the ID or NAME attribute
    public MobileElement username;

    @AndroidFindBy(id = "com.teamsnap.teamsnap:id/login_password_input")
    public MobileElement password;

    @AndroidFindBy(id = "com.teamsnap.teamsnap:id/fontTextView_button_right_label")
    public MobileElement btnNext;

    // Notifications popup button
    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
    public MobileElement btnAllowNotifications;

    public LoginPage(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;

        // This call sets the MobileElement fields
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
        
    }


    public HomePage doLogin(String txtUsername, String txtPassword) {
        btnLogin.click();
        username.sendKeys(txtUsername);
        btnNext.click();
        password.sendKeys(txtPassword);
        btnNext.click();

        return new HomePage(appiumDriver).get();
    }

    @Override
    protected void load() {
        btnLogin.click();
    }

    @Override
    protected void isLoaded() throws Error {
        assertTrue(btnLogin.isDisplayed());

    }

}
