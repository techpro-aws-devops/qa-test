package com.stepdefinitions.api;

import com.pojos.RoomPojo;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static com.baseurl.Medunna_BaseUrl.spec;
import static com.stepdefinitions.ui.RoomCreationStepDefs.roomIdStr;
import static com.stepdefinitions.ui.RoomCreationStepDefs.roomNumber;
import static com.utilities.ObjectMapperUtils.convertJsonToJava;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequestsStepDefs {

    Response response;
    RoomPojo expectedData;

    @Given("Admin sends GET request for all rooms")
    public void admin_sends_get_request_for_all_rooms() {

        //Set the url
        spec.pathParams("first", "api", "second", "rooms").queryParam("sort", "createdDate,Desc");

        // Set the expected data (payload)


        // Send the request and get the response
        response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

    }
    @Then("verify the room is created")
    public void verify_the_room_is_created() {
        // Assertion
        response.then().statusCode(200);

        Object actualRoomId = response.jsonPath().getList("findAll{it.roomNumber == "+roomNumber+"}.id").get(0);

        System.out.println("actualRoomId = " + actualRoomId);
        assertEquals(Integer.valueOf(roomIdStr), actualRoomId);

    }


    // TC03 Steps

    @Given("Admin sends GET request for a room by its id")
    public void admin_sends_get_request_for_a_room_by_its_id() {

        //Set the url
        spec.pathParams("first", "api", "second", "rooms", "third", Integer.valueOf(roomIdStr));

        // Set the expected data (payload)
        expectedData = new RoomPojo(roomNumber, "SUITE", true, 1250, "SeaView");


        // Send the request and get the response
        response = given(spec).when().get("{first}/{second}/{third}");
        response.prettyPrint();
    }
    @Then("verify response body of the created room")
    public void verify_response_body_of_the_created_room() {
        // Assertion
        response.then().statusCode(200);

        // De-serialisation
        // Best practice => ObjectMapper + Pojo

        // 1st way = new ObjectMapper().readValue(response.asString(), RoomPojo.class);
        // 2nd way
         //RoomPojo actualData = response.as(RoomPojo.class);

        // Best Practice  => Reusable method
        RoomPojo actualData = convertJsonToJava(response.asString(), RoomPojo.class);

        assertEquals(expectedData.getRoomType(), actualData.getRoomType());
        assertEquals(expectedData.getRoomNumber(), actualData.getRoomNumber());
        assertEquals(expectedData.getDescription(), actualData.getDescription());
        assertEquals(expectedData.getPrice()+ ".0", actualData.getPrice()+"");
        assertEquals(expectedData.isStatus(), actualData.isStatus());


    }






}
