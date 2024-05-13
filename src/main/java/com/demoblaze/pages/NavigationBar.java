package com.demoblaze.pages;

import com.demoblaze.utils.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationBar {

    ElementActions elementActions;
    private final WebDriver driver;

    final By signup_button =  By.id("signin2");
    public NavigationBar(WebDriver driver){
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }

    public void clickOnRegisterButton(){
        elementActions.click(signup_button);
    }


}
