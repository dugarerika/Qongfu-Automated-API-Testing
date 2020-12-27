package users;

import io.restassured.http.Headers;
import org.json.simple.JSONObject;
import pom.FillData;
import data.MyFillo;
import org.testng.Assert;
import io.restassured.RestAssured;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import com.codoid.products.fillo.Recordset;
import org.testng.annotations.DataProvider;
import com.codoid.products.exception.FilloException;
import io.restassured.specification.RequestSpecification;

import java.net.URISyntaxException;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

public class TestUsers extends BaseTest{

    FillData objData = new FillData();
    MyFillo objDataDriven = new MyFillo();
    String strPath = "src/test/resources/data/data.xlsx";

    @DataProvider(name = "data_provider")
    public Object [][] dataProviderMethod() {
        return new Object [][] {{0},{1}};
    }

    @Test(dataProvider = "data_provider")
    public void authentication(int ID) throws FilloException, URISyntaxException {

        RestAssured.baseURI = "https://staging-api.qongfu.com/api";

        String strQuery1 = "SELECT * FROM Authenticate WHERE ID = '" + ID + "'";

        RequestSpecification req = given();
        req.header("Content-Type", "application/json");

        Recordset recordset = objDataDriven.select(strPath,strQuery1);
        String info = objData.fillAuth(recordset);
        req.body(info);

        Response resp = req.post(objData.fillURL(recordset));
        resp.prettyPrint();

        int code = resp.getStatusCode();
        int expected = objData.fillCode(recordset);

        Assert.assertEquals(code,expected);

        String jsonString = resp.asString();
        System.out.println(jsonString);

        System.out.println("Status code is " +code);

        if( code == 202){

            String api_token = JsonPath.from(jsonString).get("api_token");
            System.out.println("It is the token" +" "+ api_token);
        }
    }


    @Test(dataProvider = "data_provider")
    public void Register(int ID) throws FilloException, URISyntaxException {



        String strQuery2 = "SELECT * FROM Register WHERE ID = '" + ID + "'";

        RequestSpecification req = given();
        req.header("Content-Type", "application/json");


        Recordset recordset = objDataDriven.select(strPath,strQuery2);
        String info = objData.fillAuth(recordset);
        req.body(info);

        Response resp = req.post(objData.fillURL(recordset));

        resp.prettyPrint();

        int code = resp.getStatusCode();
        int expected = objData.fillCode(recordset);

        Assert.assertEquals(code,expected);

        String jsonString = resp.asString();
        System.out.println(jsonString);

        System.out.println("Status code is " +code);

        if( code == 201){

            String api_token = JsonPath.from(jsonString).get("api_token");
            System.out.println("It is the token" +" "+ api_token);
        }
    }

    @Test(dataProvider = "data_provider")
    public void Register2(int ID) throws FilloException, URISyntaxException {

        RestAssured.baseURI = "https://staging-api.qongfu.com/api";
        String strQuery2 = "SELECT * FROM Register WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPath,strQuery2);

        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        Response response = given()
                .header("X-Requested-With", "XMLHttpRequest")
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .body(objData.fillAuth(recordset))
                .baseUri("https://staging-api.qongfu.com/api")
                .post(objData.fillURL(recordset));
        System.out.println(response.body().asString());


        response.prettyPrint();

        int code = response.getStatusCode();
        String msg = response.getStatusLine();
        int expected = objData.fillCode(recordset);

        Assert.assertEquals(code,expected);

        String jsonString = response.asString();
        System.out.println(jsonString);

        System.out.println(msg);

        if( code == 201){

            String api_token = JsonPath.from(jsonString).get("api_token");
            System.out.println("It is the token" +" "+ api_token);
        }
    }


//    @Test
//    public void authenticationEmptyPWD(){
//        RequestSpecification req = RestAssured.given();
//
//        req.header("Content-Type", "application/json");
//
//        JSONObject json= new JSONObject();
//        json.put("email","@gmail.com");
//        json.put("password"," ");
//        json.put("for_mobile", true);
//
//        req.body(json.toJSONString());
//        System.out.println(json);
//
//        Response resp = req.post("https://staging-api.qongfu.com/api/users/authenticate");
//
//        resp.prettyPrint();
//
//        int code=resp.getStatusCode();
//        System.out.println("Status code is" +code);
//
//        Assert.assertEquals(code, 400);
//
//    }


//    @Test
//    public void authenticationUnsuccesfully(){
//        RequestSpecification req = RestAssured.given();
//
//        req.header("Content-Type", "application/json");
//
//        JSONObject json= new JSONObject();
//        json.put("email","dugarerika@gmail.com");
//        json.put("password","Marassi2018*");
//        json.put("for_mobile", true);
//
//        req.body(json.toJSONString());
//
//        Response resp = req.post("https://staging-api.qongfu.com/api/users/authenticate");
//
//        int code=resp.getStatusCode();
//
//        resp.prettyPrint();
//
//        System.out.println("Response time" + resp.getTime());
//    }
}
