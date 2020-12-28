package users;

import com.codoid.products.exception.FilloException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.URISyntaxException;

public class TestSessions extends BaseTest{



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
