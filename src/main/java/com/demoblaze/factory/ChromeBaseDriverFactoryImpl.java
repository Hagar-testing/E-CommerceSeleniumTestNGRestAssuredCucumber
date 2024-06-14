package com.demoblaze.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class ChromeBaseDriverFactoryImpl implements BaseDriverFactory {

    @Override
    public WebDriver getDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(options);

//        ChromeOptions chromeOptions = new ChromeOptions();
//        try {
//
//            return new RemoteWebDriver(new URL("http://192.168.1.6:4444"), chromeOptions);
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }

    }
}
