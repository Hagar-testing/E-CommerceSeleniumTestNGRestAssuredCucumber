package com.demoblaze.utils.actions;

import com.demoblaze.enums.SelectType;
import com.demoblaze.utils.Logger;
import com.demoblaze.utils.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.Objects;

import static com.demoblaze.utils.JavascriptExecutorUtils.*;

public class ElementActions {

    final WebDriver driver;
    public ElementActions(WebDriver driver){
        this.driver = driver;
    }

    public Boolean isElementDisplayed (By element){
        WebElement element1 = locateElement(element);
        return element1.isDisplayed();
    }

    public WebElement locateElement(By targetElementLocator) {
        try {
            WebElement element =  driver.findElement(targetElementLocator);
            if(Objects.equals(element.getCssValue("display"), "none")) {
                return WaitUtils.waitForVisibilityOfElement(driver, targetElementLocator);
            } else {
                return element;
            }

        } catch (Exception e){
            return WaitUtils.waitForVisibilityOfElement(driver, targetElementLocator);
        }
    }



    public ElementActions waitForElementToBeVisible(By targetElementLocator){
        locateElement(targetElementLocator);
        return this;
    }


    public String getElementText(By by) {
        WebElement element = locateElement(by);
        String text = element.getText();
        if (text.isEmpty()) {
            return waitForNonEmptyText(by);
        }
        return text;
    }

    // Method to wait for element text
    private String waitForNonEmptyText(By by) {
        Wait<WebDriver> wait =
                new FluentWait<>(driver)
                        .withTimeout(Duration.ofSeconds(15))
                        .pollingEvery(Duration.ofMillis(300));
        return wait.until(
                d -> {
                    WebElement element = locateElement(by);
                    String text = element.getText();
                    return !text.isEmpty() ? text : null;
                });
    }
    public ElementActions type(WebDriver driver, By elementLocator, String text, boolean clearBeforeTyping) {
        WebElement element = locateElement(elementLocator);
        try {
            // Clear before typing condition
            if (!element.getAttribute("value").isBlank() && clearBeforeTyping) {
                element.clear();
                // We type here! :D
                element.sendKeys(text);
                logElementActionStep(driver, "Clear and Type [" + text + "] on", elementLocator);

                // Type using JavascriptExecutor in case of the data is not typed successfully
                // using the Selenium sendKeys method
                if (!element.getAttribute("value").equals(text)) {
                    sendInput(driver, elementLocator, text);
                }
            } else {
                // We type here! :D
                element.sendKeys(text);
                // logElementActionStep(driver, "Type [" + text + "] on", elementLocator);
            }
        }  catch (ElementNotInteractableException e){
            // Type using JavascriptExecutor in case of the data is not typed successfully
            // using the Selenium sendKeys method
            if (!element.getAttribute("value").contains(text)) {
                String currentValue = element.getAttribute("value");
                sendInput(driver, elementLocator, currentValue + text);
            }
        }
        catch (Exception e) {
            Logger.logStep(e.getMessage());
            throw e;
        }
        return this;
    }

    public ElementActions type( By elementLocator, String text) {
        return type(driver, elementLocator, text, true);
    }

    public ElementActions click(By locator) {
        try{
            locateElement(locator).click();
        } catch (ElementNotInteractableException e){
            clickUsingJavascript(locator);
        }
        logElementActionStep(driver,"Click on", locator);
        return this;
    }

    public ElementActions clickUsingJavascript(By locator) {
        logElementActionStep(driver,"click using javascript",locator);
        executeJavaScriptClick(driver,locator);

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

    public ElementActions waitForTextToBePresentInElement(By locator, String text){
        WaitUtils.waitForTextToBePresentInElement(driver,locator,text);
        return this;
    }

    private  void logElementActionStep(WebDriver driver, String action, By elementLocator) {
        try {
            String elementName = driver.findElement(elementLocator).getAccessibleName();
            if ((elementName != null && !elementName.isEmpty())) {
                Logger.logStep("[Element Action] " + action + " [" + elementName + "] element");
            } else {
                Logger.logStep("[Element Action] " + action + " [" + elementLocator + "] element");
            }
        } catch (Exception e){
            Logger.logStep("[Element Action] " + action + " [" + elementLocator + "] element");
        }

    }
}
