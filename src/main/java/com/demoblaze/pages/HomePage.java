package com.demoblaze.pages;

import com.demoblaze.utils.ConfigUtils;
import com.demoblaze.utils.WaitUtils;
import com.demoblaze.utils.actions.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage {
    private final WebDriver driver;
    private final ElementActions elementActions;
    private By getSubCategoryLocator(String categoryName) {
        return By.linkText(categoryName);
    }

    public By getProductTitle(Integer index) {
        return By.xpath("//div[@id='tbodyid']//div["+ index + "]//h4[@class='card-title']");
    }
    public HomePage(WebDriver driver){
        this.driver = driver;
        this.elementActions = new ElementActions(driver);
    }

    @Step("Open Home Page")
    public void load(){
        driver.get(ConfigUtils.getBaseUrl());
    }

    @Step("Select category: {categoryName}")
    public HomePage selectCategory(String categoryName){
        elementActions.click(getSubCategoryLocator(categoryName));
        return this;
    }
    @Step("Select product with index: {productIndex}")
    public HomePage selectProduct(Integer productIndex, String title)  {
        elementActions
                .waitForTextToBePresentInElement(getProductTitle(productIndex),title)
                .click(getProductTitle(productIndex));
        return this;
    }

}
