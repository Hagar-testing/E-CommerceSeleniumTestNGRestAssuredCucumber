package com.demoblaze.pages;

import com.demoblaze.engine.ActionsBot;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.AssertJUnit.assertTrue;

public class Header {

    ActionsBot actionsBot;
    private final By signup_a = By.cssSelector("a[id*='signin']");
    private final By login_a = By.cssSelector("a[id*='login']");
    private final By welcome_button = By.id("nameofuser");
    private final By cart_a = By.cssSelector("a[id*='cart']");

    private final WebDriver driver;


    public Header(WebDriver driver){
        this.driver = driver;
        actionsBot = new ActionsBot(driver);
    }
    @Step("Click on signup button")
    public void clickOnRegisterButton(){
        actionsBot.click(signup_a);
    }

    @Step("Click on login button")
    public LoginPage clickOnLoginButton(){
        actionsBot.click(login_a);
        return new LoginPage(driver);
    }

    @Step("Click on cart button")
    public CartPage clickOnCartButton(){
        actionsBot.click(cart_a);
        return new CartPage(driver);
    }

    //region Validations
    @Step("Check if welcome message is displayed")
    public Header validateOnAccountIsOpenedSuccessfully(){
        assertTrue(actionsBot.isElementDisplayed(welcome_button));
        return this;
    }
    //endregion

}
