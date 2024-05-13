package com.demoblaze.pages;

import com.demoblaze.utils.ConfigUtils;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void load(){
        driver.get(ConfigUtils.getBaseUrl());
    }
}
