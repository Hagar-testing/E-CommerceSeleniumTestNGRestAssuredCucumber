package com.demoblaze.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {
    private static final Duration DEFAULT_WAIT_DURATION = Duration.ofSeconds(10);


    private static WebDriverWait getWebDriverWait(WebDriver driver) {
        return new WebDriverWait(driver, DEFAULT_WAIT_DURATION);
    }

    public static WebElement waitForElementToBeClickable(WebDriver driver, By locator) {
        return getWebDriverWait(driver).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitForVisibilityOfElement(WebDriver driver, By locator) {
        return getWebDriverWait(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForInvisibilityOfElement(WebDriver driver, WebElement element) {
        getWebDriverWait(driver).until(ExpectedConditions.invisibilityOf(element));
    }

    public static WebElement waitForPresenceOfElement(WebDriver driver, By locator) {
        return getWebDriverWait(driver).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void waitForTextToBePresentInElement(WebDriver driver, By locator, String text) {
        getWebDriverWait(driver).until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public static void waitForAlertToPresent(WebDriver driver){
        getWebDriverWait(driver).until(ExpectedConditions.alertIsPresent());
    }
}
