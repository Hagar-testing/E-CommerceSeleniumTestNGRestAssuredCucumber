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
import java.util.concurrent.atomic.AtomicReference;


public class ElementActions {

    // TODO: 7/6/2024 Add logs
    final WebDriver driver;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
    }

    // TODO: 7/8/2024 Move to Assertion file
    public Boolean isElementDisplayed(By locator) {
        logElementActionStep(driver,"" , locator);
        Wait<WebDriver> wait =
                new FluentWait<>(driver)
                        .withTimeout(Duration.ofSeconds(10))
                        .pollingEvery(Duration.ofMillis(300))
                        .ignoring(ElementNotInteractableException.class)
                        .ignoring(NoSuchElementException.class)
                        .ignoring(StaleElementReferenceException.class);

        AtomicReference<Boolean> actualText = new AtomicReference<>(false);
        wait.until(f -> {
            boolean isDisplayed = driver.findElement(locator).isDisplayed();
            actualText.set(isDisplayed);
            return isDisplayed;
        });
        return actualText.get();
    }


    // TODO: 7/6/2024  check name
    public WebElement locateElement(By locator) {
        AtomicReference<WebElement> webElement = new AtomicReference<>();

        Wait<WebDriver> wait =
                new FluentWait<>(driver)
                        .withTimeout(Duration.ofSeconds(10))
                        .pollingEvery(Duration.ofMillis(300))
                        .ignoring(NoSuchElementException.class);

        wait.until(f -> {
            webElement.set(driver.findElement(locator));
            return true;
        });
        return webElement.get();

    }


    // TODO: 7/6/2024 Check waiting
    public ElementActions waitForElementToBeVisible(By targetElementLocator) {
        locateElement(targetElementLocator);
        return this;
    }


    public String getElementText(By locator) {
        Wait<WebDriver> wait =
                new FluentWait<>(driver)
                        .withTimeout(Duration.ofSeconds(10))
                        .pollingEvery(Duration.ofMillis(300))
                        .ignoring(NoSuchElementException.class)
                        .ignoring(ElementNotInteractableException.class);

        wait.until(f -> {
            return !driver.findElement(locator).getText().isEmpty();
        });
        return driver.findElement(locator).getText();
    }
    
    public ElementActions type(WebDriver driver, By elementLocator, String text, boolean clearBeforeTyping) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(ElementNotInteractableException.class)
                .ignoring(IllegalArgumentException.class);


        wait.until(f -> {
            WebElement element = driver.findElement(elementLocator);
            if (element.isDisplayed() && element.isEnabled()) {
                if (clearBeforeTyping) element.clear();
                element.sendKeys(text);
                return true;
            }
            return false;
        });


        return this;
    }

    public ElementActions type(By elementLocator, String text) {
        return type(driver, elementLocator, text, true);
    }

    public ElementActions click(By locator) {

        Wait<WebDriver> wait =
                new FluentWait<>(driver)
                        .withTimeout(Duration.ofSeconds(10))
                        .pollingEvery(Duration.ofMillis(300))
                        .ignoring(NoSuchElementException.class)
                        .ignoring(ElementNotInteractableException.class);

        wait.until(f -> {
            driver.findElement(locator).click();
            return true;
        });
        return this;

    }

    public ElementActions waitForTextToBePresentInElement(By locator, String text) {
        WaitUtils.waitForTextToBePresentInElement(driver, locator, text);
        return this;
    }

    private void logElementActionStep(WebDriver driver, String action, By elementLocator) {
        try {
            String elementName = driver.findElement(elementLocator).getAccessibleName();
            if ((elementName != null && !elementName.isEmpty())) {
                Logger.logStep("[Element Action] " + action + " [" + elementName + "] element");
            } else {
                Logger.logStep("[Element Action] " + action + " [" + elementLocator + "] element");
            }
        } catch (Exception e) {
            Logger.logStep("[Element Action] " + action + " [" + elementLocator + "] element");
        }

    }
}
