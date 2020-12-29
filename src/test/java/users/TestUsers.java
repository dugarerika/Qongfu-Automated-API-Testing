package users;

import static io.restassured.config.EncoderConfig.encoderConfig;
import com.codoid.products.exception.FilloException;
import org.testng.annotations.DataProvider;
import com.codoid.products.fillo.Recordset;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.net.URISyntaxException;
import io.restassured.RestAssured;
import org.testng.Assert;

public class TestUsers extends BaseTest{


    @Override
    public void register(int ID) throws FilloException, URISyntaxException {
        super.register(ID);
    }

    @Override
    public void authentication(int ID) throws FilloException, URISyntaxException {
        super.authentication(ID);
    }

    @Test(priority = 2, dataProvider = "cero_three")
    public void checkUsername(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM CheckUsername WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPath,strQuery1);

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
        Recordset recordset = objDataDriven.select(strPath,strQuery1);

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

    @Test(priority = 4, dataProvider = "cero_one")
    public void logOut(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM LogOut WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPath,strQuery1);

        objData.fillHeader(recordset);
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer " + objData.api_token(recordset))
                .baseUri(baseURI)
                .get(objData.fillBasePath(recordset));

        int code = response.getStatusCode();
        int expected = objData.fillCode(recordset);

        response.prettyPrint();
        System.out.println(response.getStatusLine());
        System.out.println("Bearer" + objData.api_token(recordset));
        Assert.assertEquals(code,expected);
    }
}
