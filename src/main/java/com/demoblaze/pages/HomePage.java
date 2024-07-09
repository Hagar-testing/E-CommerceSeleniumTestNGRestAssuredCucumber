package com.demoblaze.pages;

import com.demoblaze.utils.ConfigUtils;
import com.demoblaze.engine.ActionsBot;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage {
    private final WebDriver driver;
    private final ActionsBot actionsBot;
    private By getSubCategoryLocator(String categoryName) {
        return By.linkText(categoryName);
    }

    public By getProductTitle(Integer index) {
        return By.xpath("//div[@id='tbodyid']//div["+ index + "]//h4[@class='card-title']");
    }
    public HomePage(WebDriver driver){
        this.driver = driver;
        this.actionsBot = new ActionsBot(driver);
    }

    @Step("Open Home Page")
    public HomePage load(){
        driver.get(ConfigUtils.getBaseUrl());
        return this;
    }

    @Step("Select category: {categoryName}")
    public HomePage selectCategory(String categoryName){
        actionsBot.click(getSubCategoryLocator(categoryName));
        return this;
    }
    @Step("Select product with index: {productIndex}")
    public ProductDetailsPage selectProduct(Integer productIndex, String title)  {
        actionsBot
                .waitForTextToBePresentInElement(getProductTitle(productIndex),title)
                .click(getProductTitle(productIndex));
        return new ProductDetailsPage(driver);
    }

    public Header navigateToHeader(){
        return new Header(driver);
    }
}
