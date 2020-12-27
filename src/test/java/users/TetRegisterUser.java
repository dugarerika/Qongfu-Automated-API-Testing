package users;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TetRegisterUser {

    @Test
    public void testResponseCode(){

        Response resp= RestAssured.get("https://staging-api.qongfu.com/users/authenticate");

        int code=resp.getStatusCode();

        String data=resp.asString();

       resp.prettyPrint();

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

    @Test
    public void authenticationWrongPWD(){

        RequestSpecification req = RestAssured.given();

        req.header("Content-Type", "application/json");

        JSONObject json= new JSONObject();
        json.put("email","dugarerika@gmail.com");
        json.put("password","Marassi*");
        json.put("for_mobile", true);

        req.body(json.toJSONString());
        System.out.println(json.toJSONString());
        Response resp = req.post("https://staging-api.qongfu.com/api/users/authenticate");

        resp.prettyPrint();

        int code=resp.getStatusCode();
        System.out.println("Status code is" +code);


        Assert.assertEquals(code, 302);

    }
}
