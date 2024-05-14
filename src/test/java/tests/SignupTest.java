package tests;

import com.demoblaze.factory.DriverFactory;
import com.demoblaze.pages.HomePage;
import com.demoblaze.pages.Header;
import com.demoblaze.pages.SignupPage;
import data.DataProviders;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.HashMap;
import static data.DataFilesPathConstants.REGISTER_USER_DATA;

@Feature("Signup Feature")
public class SignupTest {

    private WebDriver driver;
    private final String timeStamp = String.valueOf(System.currentTimeMillis());

//    @BeforeMethod
//    public void beforeMethod() {
//        driver = new DriverFactory().initializeDriver();
//        new HomePage(driver)
//                .load();
//        new Header(driver)
//                .clickOnRegisterButton();
//    }
//
//    @Story("Registration Process")
//    @Description("Given that I register with new user, When I enter valid data, Then I should be registered successfully")
//    @Test(dataProvider = REGISTER_USER_DATA, dataProviderClass = DataProviders.class)
//    public void registerUser(HashMap<String, String> data) {
//        new SignupPage(driver)
//                .signupUser(data.get("username") + timeStamp,data.get("password"))
//                .verifyExpectedSuccessMessage(data.get("success_message"));
//    }
//
//
//    @AfterMethod
//    public void afterMethod(){
//        driver.quit();
//    }

}
