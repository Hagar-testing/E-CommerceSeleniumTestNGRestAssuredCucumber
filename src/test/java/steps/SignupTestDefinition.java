package steps;

import com.demoblaze.factory.DriverFactory;
import com.demoblaze.pages.Header;
import com.demoblaze.pages.HomePage;
import com.demoblaze.pages.SignupPage;
import com.demoblaze.utils.JsonUtils;
import com.google.gson.JsonObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import static com.demoblaze.constants.FilesPathConstants.REGISTER_DATA_FILE_PATH;
import static com.demoblaze.utils.JsonUtils.getTestData;

public class SignupTestDefinition {

    private final JsonObject testData;
    private final String timeStamp = String.valueOf(System.currentTimeMillis());
    WebDriver driver;

    public SignupTestDefinition() {
        testData = JsonUtils.parseJsonFile(REGISTER_DATA_FILE_PATH);
        driver = new DriverFactory().initializeDriver();
    }

    @Given("I am on the DemoBlaze homepage")
    public void navigateToHomePage(){
        new HomePage(driver)
                .load();
    }

    @When("I click on the Sign Up button located in the header")
    public void clickOnSignUpFromHeader(){
        new Header(driver)
                .clickOnRegisterButton();
    }

    @And("I fill in the username and password fields in the sign-up form and submit the sign-up button")
    public void fillUserDataInSignupForm(){
        new SignupPage(driver)
                .signupUser(getTestData(testData, "username") + timeStamp,getTestData(testData, "password"));
    }

    @Then("I should see a success message indicating the account is created")
    public void validateOnAccountCreation(){
        new SignupPage(driver).validateOnRegisterSuccessMessage(getTestData(testData, "messages.user_creation"));
    }
}
