package users;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;


public class BaseTest {

    @BeforeClass
    public static void Setup() {

        baseURI = "https://staging-api.qongfu.com/api";
        String api_token = "";

    }


}