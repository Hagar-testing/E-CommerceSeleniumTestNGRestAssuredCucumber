package com.demoblaze.utils;

import com.demoblaze.enums.SelectType;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;



public class ElementActions {

    final WebDriver driver;
    public ElementActions(WebDriver driver){
        this.driver = driver;
    }

    public Boolean isElementDisplayed (By element){
        return locateElement(element).isDisplayed();
    }

    public WebElement locateElement(By targetElementLocator) {
        try {
            // Attempt to find the element directly
            return driver.findElement(targetElementLocator);
        } catch (Exception e) {
            System.out.println("RRRRRRRRRRetry " + targetElementLocator);
            // Retry finding the element with an explicit wait and scroll
            return waitForElementToBePresent(targetElementLocator);
        }
    }

    private WebElement waitForElementToBePresent(By targetElementLocator) {
        try {
            WebElement element = WaitUtils.waitForVisibilityOfElement(driver,targetElementLocator);
            if (!element.isDisplayed()) {
                JavascriptExecutorUtils.findElement(driver, targetElementLocator); // Scroll if needed
            }
            return element;
        } catch (TimeoutException toe) {
            Logger.logStep("Element not found after waiting for seconds with locator: " + targetElementLocator.toString());
            throw toe;
        } catch (Exception exception) { // Catch more specific exceptions if needed
            Logger.logStep("Error finding element: " + exception.getMessage() + ", Locator: " + targetElementLocator.toString());
            throw exception;
        }
    }


    public ElementActions type(WebDriver driver, By elementLocator, String text, boolean clearBeforeTyping) {
        WebElement element = locateElement(elementLocator);
        try {
            // Clear before typing condition
            if (!element.getAttribute("value").isBlank() && clearBeforeTyping) {
                logElementActionStep(driver, "Clear and Type [" + text + "] on", elementLocator);
                element.clear();
                // We type here! :D
                element.sendKeys(text);
                // Type using JavascriptExecutor in case of the data is not typed successfully
                // using the Selenium sendKeys method
                if (!element.getAttribute("value").equals(text)) {
                    JavascriptExecutorUtils.sendInput(driver, elementLocator, text);
                }
            } else {
                logElementActionStep(driver, "Type [" + text + "] on", elementLocator);
                // We type here! :D
                element.sendKeys(text);
                // Type using JavascriptExecutor in case of the data is not typed successfully
                // using the Selenium sendKeys method
                if (!element.getAttribute("value").contains(text)) {
                    String currentValue = element.getAttribute("value");
                    JavascriptExecutorUtils.sendInput(driver, elementLocator, currentValue + text);
                }
            }
        } catch (Exception e) {
            Logger.logStep(e.getMessage());
            throw e;
        }
        return this;
    }

    public ElementActions type( By elementLocator, String text) {
        return type(driver, elementLocator, text, true);
    }

    public ElementActions click(By locator) {
        logElementActionStep(driver,"Click on", locator);
        locateElement(locator).click();
        return this;
    }

    public ElementActions clickUsingJavascript(By locator) {
        logElementActionStep(driver,"click using javascript",locator);
        JavascriptExecutorUtils.executeJavaScriptClick(driver,locateElement(locator));

        return this;
    }

    public ElementActions select(By elementLocator, SelectType selectType, String option) {
        try {
            Select select = new Select(driver.findElement(elementLocator));
            logElementActionStep(driver, "Select [" + option + "] from", elementLocator);
            switch (selectType) {
                case TEXT -> select.selectByVisibleText(option);
                case VALUE -> select.selectByValue(option);
                default -> Logger.logStep("Unexpected value: " + selectType);
            }
        } catch (Exception e) {
            Logger.logStep(e.getMessage());
            throw e;
        }
        return this;
    }

    private static void logElementActionStep(WebDriver driver, String action, By elementLocator) {
        String elementName = driver.findElement(elementLocator).getAccessibleName();
        if ((elementName != null && !elementName.isEmpty())) {
            Logger.logStep("[Element Action] " + action + " [" + elementName + "] element");
        } else {
            Logger.logStep("[Element Action] " + action + " [" + elementLocator + "] element");
        }

    }
}
