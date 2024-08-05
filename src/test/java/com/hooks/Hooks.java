package com.hooks;

import com.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;

import static com.baseurl.Medunna_BaseUrl.setUp;

public class Hooks {
    @Before("@api")
    public void beforeApi(){
        setUp();
    }



    @After
    public void afterTearDown(Scenario scenario) throws IOException {
        System.out.println("After Hooks...Runs after each Scenario.");
//        if a test scenario fails, then capture the screenshot, AND ATTACH IT TO OUR CUCUMBER HTML REPORT
        if (scenario.isFailed()){
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "failed_scenario");
            try {
                Driver.closeDriver();
            } catch (Exception e) {
                scenario.log("Exception occurred while closing driver: " + e.getMessage());
            }
        }
    }



}
