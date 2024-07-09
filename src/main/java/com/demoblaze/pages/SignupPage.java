package com.demoblaze.pages;

import com.demoblaze.engine.ActionsBot;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.demoblaze.engine.AlertBot.getAlertMessage;
import static org.testng.Assert.assertEquals;

public class SignupPage {
    private final WebDriver driver;
    private final ActionsBot actionsBot;
    private final By username_input = By.id("sign-username");
    private final By password_input = By.id("sign-password");
    private final By signup_button = By.cssSelector("button[onclick='register()']");

    public SignupPage(WebDriver driver) {
        this.driver = driver;
        this.actionsBot = new ActionsBot(driver);
    }

    @Step("User Signup with Correct UserName: {userName} and Password: {password}")
    public SignupPage signupUser(String userName, String password) {
        actionsBot
                .type(username_input,userName)
                .type(password_input, password)
                .click(signup_button);
        return this;

    }

    //region Validation
    @Step("Validate is {message} shown successfully!")
    public SignupPage validateOnRegisterSuccessMessage(String message){
        assertEquals(getAlertMessage(driver),message);
        return this ;
    }

    @Step("Validate is {message} shown successfully!")
    public SignupPage validateObRegisterFailureMessage(String message){
        assertEquals(getAlertMessage(driver),message);
        return this ;
    }
    //endregion

}
