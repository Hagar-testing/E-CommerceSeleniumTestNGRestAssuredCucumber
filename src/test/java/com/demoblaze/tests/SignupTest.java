package com.demoblaze.tests;

import com.demoblaze.api.ApisAuthentications;
import com.demoblaze.factory.DriverFactory;
import com.demoblaze.pages.HomePage;
import com.demoblaze.pages.Header;
import com.demoblaze.pages.SignupPage;
import com.demoblaze.utils.JsonUtils;
import com.google.gson.JsonObject;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Objects;

import static com.demoblaze.utils.JsonUtils.getTestData;
import static com.demoblaze.constants.FilesPathConstants.REGISTER_DATA_FILE_PATH;

@Feature("Signup Feature")
public class SignupTest {

    private WebDriver driver;
    private final String timeStamp = String.valueOf(System.currentTimeMillis());

    JsonObject data ;

    @BeforeClass
    public void beforeClass(){
        data = JsonUtils.parseJsonFile(REGISTER_DATA_FILE_PATH);
    }
    @BeforeMethod
    public void beforeMethod() {
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
                .validateObRegisterSuccessMessage(getTestData(data, "messages.user_creation"));
    }


    @Test
    public void test(){
        new ApisAuthentications()
                .registerUser(getTestData(data, "user.name")+ timeStamp,
                        Objects.requireNonNull(getTestData(data, "user.password")));

        new SignupPage(driver)
                .signupUser(getTestData(data, "username") + timeStamp,getTestData(data, "password"))
                .validateObRegisterFailureMessage(getTestData(data, "messages.already_exist_user"));

    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }

}
