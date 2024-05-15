package com.demoblaze.pages;

import com.demoblaze.utils.actions.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.demoblaze.utils.actions.AlertActions.getAlertMessage;
import static org.testng.Assert.assertEquals;

public class SignupPage {
    private final WebDriver driver;
    private final ElementActions elementActions;
    final By username_input = By.id("sign-username");
    final By password_input = By.id("sign-password");
    final By signup_button = By.cssSelector("button[onclick='register()']");

    public SignupPage(WebDriver driver) {
        this.driver = driver;
        this.elementActions = new ElementActions(driver);
    }

    @Step("User Signup with Correct UserName: {userName} and Password: {password}")
    public SignupPage signupUser(String userName, String password) {
        elementActions
                .type(username_input,userName)
                .type(password_input, password)
                .click(signup_button);
        return this;

    }

    //region Validation
    @Step("Validate is {message} shown successfully!")
    public SignupPage validateObRegisterSuccessMessage(String message){
        assertEquals(getAlertMessage(driver),message);
        return this ;
    }
    //endregion

}
