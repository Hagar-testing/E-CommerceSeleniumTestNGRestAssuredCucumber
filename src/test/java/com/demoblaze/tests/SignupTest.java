package com.demoblaze.tests;

import com.demoblaze.api.ApisAuthentications;
import com.demoblaze.factory.DriverFactory;
import com.demoblaze.listener.TestngListener;
import com.demoblaze.pages.HomePage;
import com.demoblaze.pages.Header;
import com.demoblaze.pages.SignupPage;
import com.demoblaze.utils.JsonUtils;
import com.google.gson.JsonObject;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.Objects;

import static com.demoblaze.utils.JsonUtils.getTestData;
import static com.demoblaze.constants.FilesPathConstants.REGISTER_DATA_FILE_PATH;

@Listeners(TestngListener.class)
@Feature("Signup Feature")
public class SignupTest {

    private WebDriver driver;
    private String timeStamp;

    JsonObject data ;

    @BeforeClass
    public void beforeClass(){
        data = JsonUtils.parseJsonFile(REGISTER_DATA_FILE_PATH);
    }
    @BeforeMethod
    public void beforeMethod() {
        timeStamp = String.valueOf(System.currentTimeMillis());
        driver = new DriverFactory().initializeDriver();
        new HomePage(driver)
                .load();
        new Header(driver)
                .clickOnRegisterButton();
    }

    @Story("Registration Process")
    @Description("Given that I register with new user, When I enter valid data, Then I should be registered successfully")
    @Test
    public void registerUser() {
        new SignupPage(driver)
                .signupUser(getTestData(data, "username") + timeStamp,getTestData(data, "password"))
                .validateOnRegisterSuccessMessage(getTestData(data, "messages.user_creation"));
    }


    @Test
    public void registerUserWithExistingEmailGui(){
        new ApisAuthentications()
                .registerUser(getTestData(data, "username")+ timeStamp,
                        Objects.requireNonNull(getTestData(data, "password")));

        new SignupPage(driver)
                .signupUser(getTestData(data, "username") + timeStamp,getTestData(data, "password"))
                .validateOnRegisterSuccessMessage(getTestData(data, "messages.already_exist_user"));
    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }

}
