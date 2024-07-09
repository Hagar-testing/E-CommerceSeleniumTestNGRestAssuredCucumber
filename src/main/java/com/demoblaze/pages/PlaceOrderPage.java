package com.demoblaze.pages;

import com.demoblaze.engine.ActionsBot;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.demoblaze.utils.StringUtils.getNumberFromText;
import static org.testng.Assert.assertEquals;

public class PlaceOrderPage {
    private final ActionsBot actionsBot;
    private final By name_input = By.id("name");
    private final By country_input = By.id("country");
    private final By city_input = By.id("city");
    private final By month_input = By.id("month");
    private final By card_input = By.id("card");
    private final By year_input = By.id("year");
    private final By purchase_button = By.cssSelector("button[onclick*='purchase']");
    private final By totalPrice_label = By.id("totalm");
    private final By successMessage_h3 = By.cssSelector("div[class*='alert'] h2");


    public PlaceOrderPage(WebDriver driver){
        this.actionsBot = new ActionsBot(driver);
    }

    @Step("Fill user order information and purchase")
    public PlaceOrderPage fillOrderInformationAndPurchase(String name,
                                                          String country,
                                                          String city,
                                                          String month,
                                                          String card,
                                                          String year){
        actionsBot
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
        String currentTotalPrice = getNumberFromText(actionsBot.getElementText(totalPrice_label));
        assertEquals(currentTotalPrice,totalPrice);
        return this;
    }
    @Step("Validate is {message} shown successfully!")
    public PlaceOrderPage validateOnSuccessMessageOfPurchaseOrder(String message){
        String currentMessage = actionsBot.getElementText(successMessage_h3);
        assertEquals(currentMessage,message);
        return this;
    }
    //endregion
}
