package com.demoblaze.pages;

import com.demoblaze.utils.actions.BrowserActions;
import com.demoblaze.utils.actions.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static com.demoblaze.utils.actions.BrowserActions.dismissAlertDialog;
import static com.demoblaze.utils.actions.BrowserActions.getAlertMessage;
import static org.testng.AssertJUnit.assertEquals;

public class ProductDetailsPage {
    private final WebDriver driver;
    private final ElementActions elementActions;
    final By addToCart_a = By.cssSelector("a[onclick*='addToCart']");

    public ProductDetailsPage(WebDriver driver){
        this.driver = driver;
        this.elementActions = new ElementActions(driver);
    }

    @Step("Click on add to cart button")
    public ProductDetailsPage clickOnAddToCartButton() throws InterruptedException {
        elementActions.click(addToCart_a);
        return this;
    }

    @Step("Hide add to cart alert dialog")
    public ProductDetailsPage hideAlertDialog(){
       dismissAlertDialog(driver);
        return this;
    }
    @Step("Navigate back")
    public ProductDetailsPage navigateBack(){
        BrowserActions.navigateBack(driver);
        return this;
    }

    //region Validations
    @Step("Validate that {message} of add product to cart is shown successfully!")
    public ProductDetailsPage validateOnSuccessMessageOfAddProductToCart(String message){
        assertEquals(getAlertMessage(driver),message);
        return this;
    }
    //endregion

}
