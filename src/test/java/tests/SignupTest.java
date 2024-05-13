package tests;

import com.demoblaze.factory.DriverFactory;
import com.demoblaze.pages.HomePage;
import com.demoblaze.pages.NavigationBar;
import com.demoblaze.pages.SignupPage;
import data.DataProviders;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.HashMap;
import static data.DataFilesPathConstants.REGISTER_USER_DATA;
import static org.testng.AssertJUnit.assertTrue;

@Feature("Signup Feature")
public class SignupTest {

    public HomePage homePage;
    public SignupPage signupPage;

    public NavigationBar navigationBar;

    protected final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private final String timeStamp = String.valueOf(System.currentTimeMillis());

    @BeforeMethod
    public void beforeMethod() {
        driver.set(new DriverFactory().initializeDriver());
        new HomePage(driver.get())
                .load();
        new NavigationBar(driver.get())
                .clickOnRegisterButton();
    }

    @Story("Registration Process")
    @Description("Given that I register with new user, When I enter valid data, Then I should be registered successfully")
    @Test(dataProvider = REGISTER_USER_DATA, dataProviderClass = DataProviders.class)
    public void registerUser(HashMap<String, String> data) {
        boolean isCorrectMsg = new SignupPage(driver.get())
                .signupUser(data.get("username") + timeStamp,data.get("password"))
                .verifyExpectedSuccessMessage(data.get("success_message"));
        assertTrue(isCorrectMsg);

    }



}
