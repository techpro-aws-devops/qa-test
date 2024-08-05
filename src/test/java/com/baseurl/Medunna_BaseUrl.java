package com.baseurl;

import com.utilities.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static com.utilities.MedunnaAuthentication.generateToken;

public class Medunna_BaseUrl {

    public static RequestSpecification spec;

    public static void setUp(){
        spec = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("medunna_url"))
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer "+generateToken())
                .build();
    }




}
