package com.stepdefinitions.db;

import com.pojos.RoomPojo;
import com.utilities.DBUtils;
import io.cucumber.java.en.*;

import java.sql.*;

import static com.stepdefinitions.ui.RoomCreationStepDefs.roomIdStr;
import static com.stepdefinitions.ui.RoomCreationStepDefs.roomNumber;
import static com.utilities.ObjectMapperUtils.convertJsonToJava;
import static org.junit.Assert.assertEquals;

public class DBStepDefs {

    Connection connection;
    Statement statement;
    ResultSet resultSet;

    RoomPojo expectedData;

    @Given("Admin connects to the database")
    public void admin_connects_to_the_database() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://medunna.com:5432/medunna_db_v2", "select_user", "Medunna_pass_@6");

    }
    @When("admin executes query for the room")
    public void admin_executes_query_for_the_room() throws SQLException {
        statement = connection.createStatement();

        String query = "SELECT * FROM room where id = "+roomIdStr;

        resultSet = statement.executeQuery(query);

    }
    @Then("validate created room from the resultSet")
    public void validate_created_room_from_the_result_set() throws SQLException {

        resultSet.next();
        String actualRoomNumber = resultSet.getString("room_number");
        int actualId = resultSet.getInt("id");
        int actualPrice = resultSet.getInt("price");
        String actualDescription = resultSet.getString("description");
        String actualRoomType = resultSet.getString("room_type");
        String actualStatus = resultSet.getString("status");

//        System.out.println("actualRoomNumber = " + actualRoomNumber);
//        System.out.println("actualId = " + actualId);
//        System.out.println("actualPrice = " + actualPrice);
//        System.out.println("actualDescription = " + actualDescription);


        expectedData = new RoomPojo(roomNumber, "SUITE", true, 1250, "SeaView");

        // Assertions
        assertEquals(expectedData.getRoomNumber()+"", actualRoomNumber+"");
        assertEquals(expectedData.getRoomType(), actualRoomType);
        assertEquals(expectedData.isStatus()+"", actualStatus+"rue");
        assertEquals(expectedData.getPrice(), actualPrice);
        assertEquals(expectedData.getDescription(), actualDescription);

    }


    @And("close the connection")
    public void closeTheConnection() {
        DBUtils.closeMyConnection();
    }
}
