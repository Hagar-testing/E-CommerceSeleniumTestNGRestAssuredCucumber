package tests;

import com.demoblaze.factory.DriverFactory;
import com.demoblaze.pages.HomePage;
import com.demoblaze.pages.NavigationBar;
import com.demoblaze.pages.SignupPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignupTest {

    public HomePage homePage;
    public SignupPage signupPage;

    public NavigationBar navigationBar;

    protected final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    public void beforeMethod() {
        driver.set(new DriverFactory().initializeDriver());
        new HomePage(driver.get())
                .load();
        new NavigationBar(driver.get())
                .clickOnRegisterButton();
    }

    @Test
    public void registerUser() {
        String message = new SignupPage(driver.get())
                .signupUser("88kk8uuuu8","2uuuuuuuujjjj233k44")
                .getSuccessMessage();
        Assert.assertEquals(message,"Sign up successful.");

    }



}
