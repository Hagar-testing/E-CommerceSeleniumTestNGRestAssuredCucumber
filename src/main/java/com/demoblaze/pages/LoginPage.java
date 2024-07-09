package com.demoblaze.pages;

import com.demoblaze.utils.actions.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final ElementActions elementActions;
    private final By username_input = By.id("loginusername");
    private final By password_input = By.id("loginpassword");
    private final By login_button = By.cssSelector("button[onclick='logIn()']");
    public LoginPage(WebDriver driver){
        this.elementActions = new ElementActions(driver);
    }

    @Step("User Signup with Correct UserName: {userName} and Password: {password}")
    public LoginPage loginUser(String userName, String password) {
        elementActions
                .type(username_input,userName)
                .type(password_input, password)
                .click(login_button);
        return this;

    }
}
