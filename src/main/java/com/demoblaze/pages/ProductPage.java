package com.demoblaze.pages;

import com.demoblaze.utils.BrowserActions;
import com.demoblaze.utils.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static com.demoblaze.utils.BrowserActions.dismissAlertDialog;
import static com.demoblaze.utils.BrowserActions.getAlertMessage;
import static org.testng.AssertJUnit.assertEquals;

public class ProductPage {
    private final WebDriver driver;
    private final ElementActions elementActions;

    final By addToCart_button = By.cssSelector("a[onclick*='addToCart']");

    public ProductPage(WebDriver driver){
        this.driver = driver;
        this.elementActions = new ElementActions(driver);
    }

    public ProductPage clickOnAddToCartButton() throws InterruptedException {
        elementActions.click(addToCart_button);
        return this;
    }

    public ProductPage hideAlertDialog(){
       dismissAlertDialog(driver);
        return this;
    }

    //region Validations
    @Step("Validate that {message} of add product to cart is shown successfully!")
    public ProductPage validateOnSuccessMessageOfAddProductToCart(String message){
        assertEquals(getAlertMessage(driver),message);
        return this;
    }
    //endregion

}
