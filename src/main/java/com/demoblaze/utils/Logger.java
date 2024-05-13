package com.demoblaze.utils;


import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class Logger {

    private static final org.apache.log4j.Logger log = LogManager.getLogger(Logger.class);
    @Step("{message}")
    public static void logStep(String message) {
        System.out.println("<" +   "> " + message);
        log.info("<" +   "> " + message);
        ExtentReport.info(message);
    }

    @Attachment(value = "Full Page Screenshot", type = "image/png")
    public static void attachScreenshotToAllureReport(WebDriver driver) {
         ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public static Media attachScreenshotToExtentReport(WebDriver driver) {
        return MediaEntityBuilder.createScreenCaptureFromBase64String(
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64), "Full Page Screenshot").build();
    }

}
