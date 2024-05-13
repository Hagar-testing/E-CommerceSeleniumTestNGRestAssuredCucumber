package com.demoblaze.pages;

import com.demoblaze.utils.BrowserActions;
import com.demoblaze.utils.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.demoblaze.utils.BrowserActions.getAlertMessage;

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

    public SignupPage signupUser(String userName, String password) {
        elementActions
                .type(username_input,userName)
                .type(password_input, password)
                .click(signup_button);
        return this;

    }

    public String getSuccessMessage(){
        return getAlertMessage(driver);
    }

}