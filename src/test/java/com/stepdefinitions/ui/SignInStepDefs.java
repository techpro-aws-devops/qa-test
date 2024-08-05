package com.stepdefinitions.ui;

import com.pages.MedunnaHomePage;
import com.utilities.ConfigReader;
import com.utilities.Driver;
import io.cucumber.java.en.*;

public class SignInStepDefs {

    MedunnaHomePage homePage = new MedunnaHomePage();

    @Given("Admin goes to {string} homepage")
    public void admin_goes_to_homepage(String url) {
        Driver.getDriver().get(url);
    }
    @Given("Admin clicks on userIcon")
    public void admin_clicks_on_user_icon() {
        homePage.userIcon.click();

    }
    @Given("Admin clicks on sign in option")
    public void admin_clicks_on_sign_in_option() {
        homePage.signInOption.click();
    }
    @Given("Admin enters username in Username input field")
    public void admin_enters_username_in_username_input_field() {

        homePage.usernameInput.sendKeys(ConfigReader.getProperty("admin_username"));
    }
    @Given("Admin enters password in password input field")
    public void admin_enters_password_in_password_input_field() {
        homePage.passwordInput.sendKeys(ConfigReader.getProperty("admin_password"));
    }
    @Given("Admin clicks on Sign In button")
    public void admin_clicks_on_sign_in_button() {
        homePage.signInSubmitButton.click();
    }


}
