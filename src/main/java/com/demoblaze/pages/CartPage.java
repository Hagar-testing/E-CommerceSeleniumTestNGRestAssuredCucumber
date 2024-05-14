package com.demoblaze.pages;

import com.demoblaze.utils.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static org.testng.AssertJUnit.assertEquals;

public class CartPage {

    private final ElementActions elementActions;

    private final By placeOrder_button = By.cssSelector("button[data-target*='order']");
    private final By totalPrice_h3 = By.id("totalp");

    private By productName(String itemName) {
        return By.xpath("//tr[@class='success']//td[contains(text(), '" + itemName + "')]");
    }

    private By productPrice(String itemName) {
        return By.xpath("//tr[@class='success']//td[contains(text(), '" + itemName + "')]/following-sibling::td");
    }
    public CartPage(WebDriver driver){
        this.elementActions = new ElementActions(driver);
    }
    @Step("Click on place order button")
    public void clickOnPlaceOrderButton(){
        elementActions.click(placeOrder_button);
    }

    //region Validations
    @Step("Validate product: {itemTitle} is added to cart")
    public CartPage validateOnItemAddedInCart(String itemTitle) {
        String productText = elementActions.locateElement(productName(itemTitle)).getText();
        Assert.assertEquals(productText,itemTitle);
        return this;
    }

    @Step("Validate on the price: {itemPrice} of product: {itemTitle} ")
    public CartPage validateOnProductPrices(String itemTitle, String itemPrice) {
        String productText = elementActions.locateElement(productPrice(itemTitle)).getText();
        Assert.assertEquals(productText,itemPrice);
        return this;
    }

    @Step("Validate on the total price in cart ")
    public CartPage validateOnTotalProductPrice(String totalPrice) {
        assertEquals(elementActions.locateElement(totalPrice_h3).getText(),totalPrice);
        return this;
    }
    //endregion

}
