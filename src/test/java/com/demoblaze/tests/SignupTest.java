package com.demoblaze.tests;

import com.demoblaze.api.ApisAuthentication;
import com.demoblaze.factory.DriverFactory;
import com.demoblaze.listener.TestngListener;
import com.demoblaze.pages.Header;
import com.demoblaze.pages.HomePage;
import com.demoblaze.pages.SignupPage;
import com.demoblaze.utils.JsonUtils;
import com.google.gson.JsonObject;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.LabelAnnotation;
import io.qameta.allure.Story;
import io.qameta.allure.testng.Tag;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.Objects;

import static com.demoblaze.constants.FilesPathConstants.REGISTER_DATA_FILE_PATH;
import static com.demoblaze.utils.JsonUtils.getTestData;


@Tag("TestNG")
@Listeners(TestngListener.class)
@Feature("Signup Feature")
public class SignupTest {

    private String timeStamp;
    private JsonObject data ;
    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @Story("Registration Process")
    @Description("Given that I register with new user, When I enter valid data, Then I should be registered successfully")
    @Test(description = "Register New User Successfully - GUI")
    public void registerUser() {
        new HomePage(getDriver())
                .load()
                .navigateToHeader()
                .clickOnRegisterButton()
                .signupUser(getTestData(data, "username") + timeStamp,getTestData(data, "password"))
                .validateOnRegisterSuccessMessage(getTestData(data, "messages.user_creation"));
    }


    @Story("Registration Process")
    @Description("Given that I register with new user, When I enter an existing email, Then I should not be registered and an error message should appear")
    @Test(description = "Register User with Existing Email - GUI")
    public void registerUserWithExistingEmail(){
        new ApisAuthentication()
                .registerUser(getTestData(data, "username")+ timeStamp,
                        Objects.requireNonNull(getTestData(data, "password")));
        new HomePage(getDriver())
                .load()
                .navigateToHeader()
                .clickOnRegisterButton()
                .signupUser(getTestData(data, "username") + timeStamp,getTestData(data, "password"))
                .validateOnRegisterSuccessMessage(getTestData(data, "messages.already_exist_user"));
    }

    //region WebDriver
    public void setDriver(WebDriver driver){
        this.driver.set(driver);
    }

    public WebDriver getDriver(){
        return driver.get();
    }
    //endregion

    //region Configurations
    @BeforeClass
    public void beforeClass(){
        data = JsonUtils.parseJsonFile(REGISTER_DATA_FILE_PATH);
    }
    @AfterMethod
    public void afterMethod(){
        getDriver().quit();
    }
    @BeforeMethod
    public void beforeMethod() {
        timeStamp = String.valueOf(System.currentTimeMillis());
        setDriver(new DriverFactory().initializeDriver());
    }
    //endregion

}
