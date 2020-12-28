package users;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Recordset;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.URISyntaxException;

import static io.restassured.config.EncoderConfig.encoderConfig;

public class TestActivities extends BaseTest {

    @DataProvider(name = "data_provider")
    public Object [][] dataProviderMethod() {

        return new Object [][] {{0},{1},{2},{3}};
    }

    @Test(dataProvider = "data_provider")
    public void GetMyActivities(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM GetMyActivities WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPath,strQuery1);

        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        Response resp = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer" + objData.api_token(recordset))
                .body(objData.fillUsername(recordset)).baseUri(baseURI)
                .get(objData.fillBasePath(recordset));

        resp.prettyPrint();
        System.out.println("Bearer" + objData.api_token(recordset));
        int code = resp.getStatusCode();
        int expected = objData.fillCode(recordset);

        Assert.assertEquals(code,expected);
        System.out.println(resp.getStatusLine());

        System.out.println("Status code is " +code);
    }

    @Test(dataProvider = "data_provider")
    public void CreateActivities(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM CreateActivity WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPath,strQuery1);

        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        Response resp = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer" + objData.api_token(recordset))
                .body(objData.fillUsername(recordset)).baseUri(baseURI)
                .post(objData.fillBasePath(recordset));

        resp.prettyPrint();
        System.out.println("Bearer" + objData.api_token(recordset));
        int code = resp.getStatusCode();
        int expected = objData.fillCode(recordset);

        Assert.assertEquals(code,expected);
        System.out.println(resp.getStatusLine());

        System.out.println("Status code is " +code);
    }

    @Test(dataProvider = "data_provider")
    public void UpdateActivities(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM UpdateActivity WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPath,strQuery1);

        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        Response resp = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer" + objData.api_token(recordset))
                .body(objData.fillUsername(recordset)).baseUri(baseURI)
                .post(objData.fillBasePath(recordset));

        resp.prettyPrint();
        System.out.println("Bearer" + objData.api_token(recordset));
        int code = resp.getStatusCode();
        int expected = objData.fillCode(recordset);

        Assert.assertEquals(code,expected);
        System.out.println(resp.getStatusLine());

        System.out.println("Status code is " +code);
    }
}
