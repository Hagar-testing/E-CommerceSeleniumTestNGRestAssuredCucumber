package com.demoblaze.pages;

import com.demoblaze.utils.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.AssertJUnit.assertTrue;

public class Header {

    ElementActions elementActions;
    final By signup_button = By.cssSelector("a[id*='signin']");
    final By login_button = By.cssSelector("a[id*='login']");
    final By welcome_button = By.id("nameofuser");
    final By home_button = By.cssSelector(".nav-link[href='index.html']");
    final By cart_button = By.cssSelector("a[id*='cart']");

    public Header(WebDriver driver){
        elementActions = new ElementActions(driver);
    }
    @Step("Click on signup button")
    public void clickOnRegisterButton(){
        elementActions.click(signup_button);
    }

    @Step("Click on login button")
    public void clickOnLoginButton(){
        elementActions.click(login_button);
    }

    @Step("Click on home button")
    public void clickOnHomeButton(){
        elementActions.click(home_button);
    }

    @Step("Click on cart button")
    public void clickOnCartButton(){
        elementActions.click(cart_button);
    }

    //region Validations
    @Step("Check if welcome message is displayed")
    public Header validateOnAccountIsOpenedSuccessfully(){
        assertTrue(elementActions.isElementDisplayed(welcome_button));
        return this;
    }
    //endregion

}
