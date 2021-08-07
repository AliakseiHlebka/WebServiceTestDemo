package com.epam.webservices.demo.tests;

import com.epam.webservices.demo.model.user.User;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class RestAssuredTests {

    @BeforeTest
    public void initTest() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/users";
    }

    @Test
    public void verifyStatusCode() {
        Response response = RestAssured.when().get().andReturn();
        Assert.assertTrue(response.getStatusLine().contains("200 OK"),
            "Status code differs from the expected one");
    }

    @Test
    public void verifyResponseHeader() {
        Response response = RestAssured.when().get().andReturn();
        String responseContentTypeHeader = response.getHeader("Content-Type");
        Assert.assertEquals(responseContentTypeHeader, "application/json; charset=utf-8",
            "Value of Content-Type header differs from the expected one");
    }

    @Test
    public void verifyResponseBody() {
        Response response = RestAssured.when().get().andReturn();
        ResponseBody responseBody = response.getBody();
        User[] users = responseBody.as(User[].class);
        Assert.assertEquals(users.length, 10,
            "Number of users in the response body differs from the expected one");
    }
}
