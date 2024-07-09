package com.demoblaze.engine;

import com.demoblaze.utils.Logger;
import com.demoblaze.utils.WaitUtils;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ActionsBot {

    // TODO: Create assertion file
    final WebDriver driver;

    public ActionsBot(WebDriver driver) {
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

    public ActionsBot type(By locator, String text, boolean clearBeforeTyping) {
        logElementActionStep("Typing text into element", locator);
        WaitUtils.waitForTyping(driver, locator, text, clearBeforeTyping);
        return this;
    }

    public ActionsBot type(By locator, String text) {
        return type(locator, text, true);
    }

    public ActionsBot click(By locator) {
        logElementActionStep("Click on element", locator);
        WaitUtils.waitForElementToBeClickable(driver, locator);
        driver.findElement(locator).click();
        return this;
    }

    public ActionsBot waitForTextToBePresentInElement(By locator, String text) {
        logElementActionStep("Wait for text to present in element", locator);
        WaitUtils.waitForTextToBe(driver, locator, text);
        return this;
    }

    private void logElementActionStep(String action, By locator) {
        try {
            String elementName = driver.findElement(locator).getAccessibleName();
            if (elementName != null && !elementName.isEmpty()) {
                Logger.logStep("[Actions Bot] " + action + " [" + elementName + "] element");
            } else {
                Logger.logStep("[Actions Bot] " + action + " [" + locator + "] element");
            }
        } catch (Exception e) {
            Logger.logStep("[Actions Bot] " + action + " [" + locator + "] element");
        }
    }
}
