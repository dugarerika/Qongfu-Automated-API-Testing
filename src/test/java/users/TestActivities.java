package users;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestActivities {


    @Test
    public void testResponseCode(){
        Response resp= RestAssured.get("https://staging-api.qongfu.com/api/users");

        int code=resp.getStatusCode();

        System.out.println("Status code is" +code);

        Assert.assertEquals(code, 200);

    }

    @Test
    public void testBody(){
        Response resp= RestAssured.get("https://staging-api.qongfu.com/api/users");

        String data=resp.asString();

        System.out.println("Data is " + data);

        System.out.println("Response time" + resp.getTime());
    }
}
