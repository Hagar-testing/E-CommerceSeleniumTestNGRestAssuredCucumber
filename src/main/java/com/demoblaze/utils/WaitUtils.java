package com.demoblaze.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class WaitUtils {

    public static Wait<WebDriver> createWait(WebDriver driver) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoAlertPresentException.class);
    }

    public static boolean waitForTyping(WebDriver driver, By locator, String text, boolean clearBeforeTyping) {
        Wait<WebDriver> wait = createWait(driver);
        return wait.until(d -> {
            WebElement element = driver.findElement(locator);
            if (element.isDisplayed() && element.isEnabled()) {
                if (clearBeforeTyping) element.clear();
                element.sendKeys(text);
                return element.getAttribute("value").equals(text);
            }
            return false;
        });
    }

    public static boolean waitForElementToBeClickable(WebDriver driver, By locator) {
        Wait<WebDriver> wait = createWait(driver);
        return wait.until(d -> {
            WebElement element = driver.findElement(locator);
            return element.isDisplayed() && element.isEnabled();
        });
    }
    public static boolean waitForElementToBeDisplayed(WebDriver driver, By locator) {
        Wait<WebDriver> wait = createWait(driver);
        return wait.until(d -> driver.findElement(locator).isDisplayed());
    }
    public static boolean waitForTextToBePresentInElement(WebDriver driver, By locator) {
        Wait<WebDriver> wait = createWait(driver);
        return wait.until(f -> !driver.findElement(locator).getText().isEmpty());
    }

    public static Boolean waitForTextToBe(WebDriver driver, By locator, String text){
        Wait<WebDriver> wait = createWait(driver);
        return wait.until(f -> driver.findElement(locator).getText().equals(text));
    }

    public static void waitForAlertToPresent(WebDriver driver){
        Wait<WebDriver> wait = createWait(driver);
        wait.until(d -> {
            driver.switchTo().alert();
            return true;
        });
    }
}
