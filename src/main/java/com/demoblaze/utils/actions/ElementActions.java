package com.demoblaze.utils.actions;

import com.demoblaze.utils.Logger;
import com.demoblaze.utils.WaitUtils;
import org.openqa.selenium.*;



public class ElementActions {

    // TODO: 7/6/2024 Add logs
    // TODO: 7/6/2024 Wait in wait utils then add action here

    final WebDriver driver;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
    }

    // TODO: 7/8/2024 Move to Assertion file

    public Boolean isElementDisplayed(By locator) {
        logElementActionStep("Check if element is displayed",locator );
        return WaitUtils.waitForElementToBeDisplayed(driver,locator);
    }


    public String getElementText(By locator) {
        WaitUtils.waitForTextToBePresentInElement(driver,locator);
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
        WaitUtils.waitForElementToClick(driver, locator);
        return this;
    }

    public ElementActions waitForTextToBePresentInElement(By locator, String text) {
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
