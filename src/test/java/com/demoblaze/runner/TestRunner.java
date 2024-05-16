package com.demoblaze.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import static com.demoblaze.constants.FilesPathConstants.FEATURES_PATH;
import static com.demoblaze.constants.FilesPathConstants.STEPS_PATH;

@RunWith(Cucumber.class)
@CucumberOptions(features = FEATURES_PATH,
        glue = {STEPS_PATH},
        plugin = {"pretty","html:target/cucumber.html","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"})
public class TestRunner {

}