package com.demoblaze.pages;

import com.demoblaze.utils.actions.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.demoblaze.utils.StringUtils.getNumberFromText;
import static org.testng.Assert.assertEquals;

public class PlaceOrderPage {
    private final ElementActions elementActions;
    final By name_input = By.id("name");
    final By country_input = By.id("country");
    final By city_input = By.id("city");
    final By month_input = By.id("month");
    final By card_input = By.id("card");
    final By year_input = By.id("year");
    final By purchase_button = By.cssSelector("button[onclick*='purchase']");
    private final By totalPrice_label = By.id("totalm");
    private final By successMessage_h3 = By.cssSelector("div[class*='alert'] h2");


    public PlaceOrderPage(WebDriver driver){
        this.elementActions = new ElementActions(driver);
    }

    @Step("Fill user order information and purchase")
    public PlaceOrderPage fillOrderInformationAndPurchase(String name,
                                                          String country,
                                                          String city,
                                                          String month,
                                                          String card,
                                                          String year){
        elementActions
                .type(name_input,name)
                .type(country_input,country)
                .type(city_input,city)
                .type(month_input,month)
                .type(card_input,card)
                .type(year_input,year)
                .click(purchase_button);

        return this;
    }

    //region Validations

    @Step("Validate on total price in place order page")
    public PlaceOrderPage validateOnTotalPriceInPlaceOrder(String totalPrice){
        String currentTotalPrice = getNumberFromText(elementActions.locateElement(totalPrice_label).getText());
        assertEquals(currentTotalPrice,totalPrice);
        return this;
    }
    @Step("Validate is {message} shown successfully!")
    public PlaceOrderPage validateOnSuccessMessageOfPurchaseOrder(String message){
        String currentMessage = elementActions.getElementText(successMessage_h3);
        assertEquals(currentMessage,message);
        return this;
    }
    //endregion
}
