package com.demoblaze.pages;

import com.demoblaze.utils.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header {

    ElementActions elementActions;

    final By signup_button = By.id("signin2");
    public Header(WebDriver driver){
        elementActions = new ElementActions(driver);
    }
    @Step("Click on signup button")
    public void clickOnRegisterButton(){
        elementActions.click(signup_button);
    }


}
