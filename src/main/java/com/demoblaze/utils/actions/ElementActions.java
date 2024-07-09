package com.demoblaze.utils.actions;

import com.demoblaze.utils.Logger;
import com.demoblaze.utils.WaitUtils;
import org.openqa.selenium.*;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementActions {

    // TODO: Create assertion file
    final WebDriver driver;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
    }

    public Boolean isElementDisplayed(By locator) {
        logElementActionStep("Check if element is displayed", locator);
        WaitUtils.waitForElementToBeDisplayed(driver, locator);
        return driver.findElement(locator).isDisplayed();
    }

    public String getElementText(By locator) {
        logElementActionStep("Get element text", locator);
        WaitUtils.waitForTextToBePresentInElement(driver, locator);
        return driver.findElement(locator).getText();
    }

    public ElementActions type(By locator, String text, boolean clearBeforeTyping) {
        logElementActionStep("Typing text into element", locator);
        WaitUtils.waitForTyping(driver, locator, text, clearBeforeTyping);
        return this;
    }

    public ElementActions type(By locator, String text) {
        return type(locator, text, true);
    }

    public ElementActions click(By locator) {
        logElementActionStep("Click on element", locator);
        WaitUtils.waitForElementToBeClickable(driver, locator);
        driver.findElement(locator).click();
        return this;
    }

    public ElementActions waitForTextToBePresentInElement(By locator, String text) {
        logElementActionStep("Wait for text to present in element", locator);
        WaitUtils.waitForTextToBe(driver, locator, text);
        return this;
    }

    private void logElementActionStep(String action, By locator) {
        try {
            String elementName = driver.findElement(locator).getAccessibleName();
            if (elementName != null && !elementName.isEmpty()) {
                Logger.logStep("[Element Action] " + action + " [" + elementName + "] element");
            } else {
                Logger.logStep("[Element Action] " + action + " [" + locator + "] element");
            }
        } catch (Exception e) {
            Logger.logStep("[Element Action] " + action + " [" + locator + "] element");
        }
    }
}
