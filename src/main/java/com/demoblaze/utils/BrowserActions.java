package com.demoblaze.utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import static com.demoblaze.utils.WaitUtils.waitForAlertToPresent;

public class BrowserActions {

    public static String getAlertMessage(WebDriver driver){
        waitForAlertToPresent(driver);
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    public static void dismissAlertDialog(WebDriver driver){
        waitForAlertToPresent(driver);
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public static void navigateBack(WebDriver driver){
        driver.navigate().back();
    }
}
