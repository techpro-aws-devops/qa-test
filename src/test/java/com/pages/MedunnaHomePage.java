package com.pages;

import com.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MedunnaHomePage {

    public MedunnaHomePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    // HomePage elements
    @FindBy(id = "account-menu")
    public WebElement userIcon;

    @FindBy(xpath = "//span[.='Sign in']")
    public WebElement signInOption;

    @FindBy(id = "username")
    public WebElement usernameInput;

    @FindBy(id = "password")
    public WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement signInSubmitButton;
}
