package com.stepdefinitions.ui;

import com.github.javafaker.Faker;
import com.pages.LogInPage;
import com.pages.RoomCreationPage;
import com.utilities.BrowserUtils;
import com.utilities.WaitUtils;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class RoomCreationStepDefs {

    // Page Objects
    LogInPage logInPage = new LogInPage();
    RoomCreationPage roomCreationPage = new RoomCreationPage();

    // Create static Room number variable for further use

    public static int roomNumber;
    public static String roomIdStr;


    @When("Admin clicks on Items&Titles button")
    public void admin_clicks_on_items_titles_button() {
        logInPage.itemsAndTitles.click();

    }
    @When("clicks on room option from the dropdown")
    public void clicks_on_room_option_from_the_dropdown() {
        roomCreationPage.roomOption.click();
    }
    @When("clicks on Create a New Room button")
    public void clicks_on_create_a_new_room_button() {
        roomCreationPage.createANewRoomButton.click();
    }
    @When("enters room number in the Room Number input field")
    public void enters_room_number_in_the_room_number_input_field() {

        roomNumber = Faker.instance().number().numberBetween(1000, 10000);
        System.out.println("roomNumber sent = " + roomNumber);
        roomCreationPage.roomNumberInput.sendKeys(roomNumber+"");
    }
    @When("chooses {string} from the dropdown")
    public void chooses_from_the_dropdown(String roomType) {
//        Select select = new Select(roomCreationPage.roomTypeDropDown);
//        select.selectByVisibleText(roomType);

        // Or use a Reusable method
        BrowserUtils.dropdownSelectByVisibleText(roomCreationPage.roomTypeDropDown, roomType);
    }
    @When("clicks on status box")
    public void clicks_on_status_box() {
        roomCreationPage.statusCheckBox.click();
    }
    @When("enters the {string} in Price input field")
    public void enters_the_in_price_input_field(String price) {

        roomCreationPage.priceInput.sendKeys(price);

    }
    @When("enters a {string} in description field")
    public void enters_a_in_description_field(String description) {

        roomCreationPage.descriptionInput.sendKeys(description);
    }
    @When("clicks on Save button")
    public void clicks_on_save_button() {
        roomCreationPage.saveSubmitButton.click();

    }
    @Then("verify that room is created")
    public void verify_that_room_is_created() {

        WaitUtils.waitFor(2);

        Assert.assertTrue(roomCreationPage.alert.isDisplayed());
        // Or use a Reusable method
        BrowserUtils.verifyElementDisplayed(roomCreationPage.alert);

        roomIdStr = roomCreationPage.alert.getText().replaceAll("[^0-9]", "");
        // roomIdStr = roomCreationPage.alert.getText().replaceAll("\\D", "");


    }

}
