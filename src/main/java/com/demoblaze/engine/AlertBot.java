package com.demoblaze.engine;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import static com.demoblaze.utils.WaitUtils.waitForAlertToPresent;

public class AlertBot {

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