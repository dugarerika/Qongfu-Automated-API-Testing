package tests;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Recordset;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URISyntaxException;

import static io.restassured.config.EncoderConfig.encoderConfig;

public class TestUsers extends BaseTest{



    @Test(priority = 0, dataProvider = "cero_three")
    public void register(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM Register WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPathUsers,strQuery1);

        objData.fillHeader(recordset);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        Response response = RestAssured.given()
                .header("X-Requested-With", "XMLHttpRequest")
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .body(objData.fillReg(recordset)).baseUri(baseURI)
                .post(objData.fillBasePath(recordset));

        int code = response.getStatusCode();
        int expected = objData.fillCode(recordset);

        response.prettyPrint();
        System.out.println(response.getStatusLine());
        Assert.assertEquals(code,expected);


        if( code == 201){

            JsonPath jsonPathValidator = response.jsonPath();
            String info = jsonPathValidator.getString("user.api_token");
            System.out.println("It is the token" +" "+ info);
        }
    }

    @Test(priority = 1, dataProvider = "cero_three")
    public void authentication(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM Authenticate WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPathUsers,strQuery1);

        objData.fillHeader(recordset);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        Response resp = RestAssured.given()
                .header("X-Requested-With", "XMLHttpRequest")
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .body(objData.fillAuth(recordset)).baseUri(baseURI)
                .post(objData.fillBasePath(recordset));

        resp.prettyPrint();

        int code = resp.getStatusCode();
        int expected = objData.fillCode(recordset);

        Assert.assertEquals(code,expected);
        System.out.println(resp.getStatusLine());

        System.out.println("Status code is " +code);

        if( code == 202){

            api_token = JsonPath.from(resp.asString()).get("api_token");
            System.out.println("It is the token" +" "+ api_token);
            queries();
        }
    }

    @Test(priority = 2, dataProvider = "cero_three")
    public void checkUsername(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM CheckUsername WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPathUsers,strQuery1);

        objData.fillHeader(recordset);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        Response resp = RestAssured.given()
                .header("X-Requested-With", "XMLHttpRequest")
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer " + objData.api_token(recordset))
                .body(objData.fillUsername(recordset)).baseUri(baseURI)
                .post(objData.fillBasePath(recordset));

        resp.prettyPrint();
        System.out.println("Bearer" + objData.api_token(recordset));
        int code = resp.getStatusCode();
        int expected = objData.fillCode(recordset);

        Assert.assertEquals(code,expected);
        System.out.println(resp.getStatusLine());
        System.out.println("Status code is " +code);

        if( code == 200){
            System.out.println("It is the token" +" "+ api_token);
        }
    }

    @Test(priority = 3,dataProvider = "cero_three")
    public void updateUsername(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM UpdateUsername WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPathUsers,strQuery1);

        objData.fillHeader(recordset);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        Response resp = RestAssured.given()
                .header("X-Requested-With", "XMLHttpRequest")
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer " + objData.api_token(recordset))
                .body(objData.fillUsername(recordset)).baseUri(baseURI)
                .post(objData.fillBasePath(recordset));

        resp.prettyPrint();
        System.out.println("Bearer" + objData.api_token(recordset));
        int code = resp.getStatusCode();
        int expected = objData.fillCode(recordset);

        Assert.assertEquals(code,expected);
        System.out.println(resp.getStatusLine());

        if( code == 200){
            System.out.println("It is the token" +" "+ api_token);
        }
    }

    @Test(priority = 4, dataProvider = "cero_two")
    public void userDetails(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM UserDetails WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPathUsers,strQuery1);

        objData.fillHeader(recordset);
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer " + objData.api_token(recordset))
                .baseUri(baseURI)
                .get(objData.fillBasePath(recordset));

        response.prettyPrint();
        int code = response.getStatusCode();
        int expected = objData.fillCode(recordset);


        System.out.println(response.getStatusLine());
        System.out.println("Bearer" + objData.api_token(recordset));
        Assert.assertEquals(code,expected);
    }

//    @Test(priority = 5, dataProvider = "cero_two")
//    public void logOut(int ID) throws FilloException, URISyntaxException {
//
//        String strQuery1 = "SELECT * FROM LogOut WHERE ID = '" + ID + "'";
//        Recordset recordset = objDataDriven.select(strPathUsers,strQuery1);
//
//        objData.fillHeader(recordset);
//        Response response = RestAssured.given()
//                .header("Accept", "application/json")
//                .header("Content-Type","application/json")
//                .header("Authorization", "Bearer " + objData.api_token(recordset))
//                .baseUri(baseURI)
//                .get(objData.fillBasePath(recordset));
//
//        response.prettyPrint();
//        int code = response.getStatusCode();
//        int expected = objData.fillCode(recordset);
//
//        System.out.println(response.getStatusLine());
//        System.out.println("Bearer" + objData.api_token(recordset));
//        Assert.assertEquals(code,expected);
//    }
}
