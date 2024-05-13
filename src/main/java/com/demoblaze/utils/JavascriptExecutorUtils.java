package com.demoblaze.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavascriptExecutorUtils {


    public static JavascriptExecutor getJavascriptExecutor(WebDriver driver) {
        return (JavascriptExecutor) driver;
    }

    public static void executeJavaScriptClick(WebDriver driver,WebElement element) {
        getJavascriptExecutor(driver).executeScript("arguments[0].click();", element);
    }

    public static void sendInput(WebDriver driver, By elementLocator, String text){
        getJavascriptExecutor(driver).executeScript("arguments[0].setAttribute('value', '" + text + "')",
                driver.findElement(elementLocator));
    }

    public static void findElement(WebDriver driver,By elementLocator){
        getJavascriptExecutor(driver).executeScript("arguments[0].scrollIntoView(false);",  driver.findElement(elementLocator));

    }

}
