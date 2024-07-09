package com.demoblaze.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.ElementNotInteractableException;

import java.time.Duration;
import java.util.function.Function;

public class WaitUtils {

    public static Wait<WebDriver> createWait(WebDriver driver) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(StaleElementReferenceException.class);
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

    public static boolean waitForElementToClick(WebDriver driver, By locator) {
        Wait<WebDriver> wait = createWait(driver);
        return wait.until(d -> {
            WebElement element = driver.findElement(locator);
            if (element.isDisplayed() && element.isEnabled()) {
                element.click();
                return true;
            }
            return false;
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
}
