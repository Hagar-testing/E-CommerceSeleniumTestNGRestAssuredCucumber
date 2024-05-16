package com.demoblaze.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import static com.demoblaze.constants.FilesPathConstants.FEATURES_PATH;

@RunWith(Cucumber.class)
@CucumberOptions(features = FEATURES_PATH,
        glue = {"steps"},
        plugin = {"html:target/cucumber.html"})
public class TestRunner {

}