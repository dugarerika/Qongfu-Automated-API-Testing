package users;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestsChat {

    @Test
    public void testAuthenticationSuccesfully() {

        RequestSpecification req = RestAssured.given();

        req.header("Content-Type", "application/json");

        JSONObject json= new JSONObject();
        json.put("email","dugarerika@gmail.com");
        json.put("password","Marassi2018*");
        json.put("for_mobile", true);

        req.body(json.toJSONString());

        Response resp = req.post("https://staging-api.qongfu.com/api/users/authenticate");

        int code=resp.getStatusCode();

        String data=resp.asString();

        System.out.println("Data is " + data);

        System.out.println("Response time" + resp.getTime());

        System.out.println("Status code is" +code);

        Assert.assertEquals(code, 202);

    }

    @Test
    public void testAuthenticationSuccesfullyBody() {
        RequestSpecification req = RestAssured.given();

        req.header("Content-Type", "application/json");

        JSONObject json= new JSONObject();
        json.put("email","dugarerika@gmail.com");
        json.put("password","Marassi2018*");
        json.put("for_mobile", true);

        req.body(json.toJSONString());

        Response resp = req.post("https://staging-api.qongfu.com/api/users/authenticate");

        int code=resp.getStatusCode();

        String data=resp.asString();

        System.out.println("Data is " + data);

        System.out.println("Response time" + resp.getTime());
    }
}
