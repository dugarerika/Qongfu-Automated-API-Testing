package users;

import static io.restassured.config.EncoderConfig.encoderConfig;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Recordset;
import org.testng.annotations.DataProvider;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.net.URISyntaxException;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import org.testng.Assert;
import data.MyFillo;
import pom.FillData;

public class BaseTest {

    FillData objData = new FillData();
    MyFillo objDataDriven = new MyFillo();
    String strPath = "src/test/resources/data/users.xlsx";
    String baseURI = "https://staging-api.qongfu.com/api";
    String api_token = " ";

    @DataProvider(name = "cero_three")
    public Object [][] cerothree() {

        return new Object [][] {{0},{1},{2},{3}};
    }

    @DataProvider(name = "cero_one")
    public Object [][] dataProviderMethod() {

        return new Object [][] {{0},{1}};
    }

    @Test(priority = 0, dataProvider = "cero_three")
    public void register(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM Register WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPath,strQuery1);

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
        Recordset recordset = objDataDriven.select(strPath,strQuery1);

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
            String strQuery2 = "UPDATE CheckUsername set api_token = '" + api_token +"' WHERE ID = 0";
            String strQuery3 = "UPDATE CheckUsername set api_token = '" + api_token +"' WHERE ID = 2";
            objDataDriven.update(strPath,strQuery2);
            objDataDriven.update(strPath,strQuery3);
            String strQuery4 = "UPDATE UpdateUsername set api_token = '" + api_token +"' WHERE ID = 0";
            String strQuery5 = "UPDATE UpdateUsername set api_token = '" + api_token +"' WHERE ID = 2";
            objDataDriven.update(strPath,strQuery4);
            objDataDriven.update(strPath,strQuery5);
            String strQuery6 = "UPDATE LogOut set api_token = '" + api_token +"' WHERE ID = 0";
            objDataDriven.update(strPath,strQuery6);
        }
    }


}