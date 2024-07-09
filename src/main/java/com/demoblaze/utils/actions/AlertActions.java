package com.demoblaze.utils.actions;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import static com.demoblaze.utils.WaitUtils2.waitForAlertToPresent;

public class AlertActions {

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

}