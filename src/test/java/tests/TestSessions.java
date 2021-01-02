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

public class TestSessions extends BaseTest {

    @Test(priority = 2, dataProvider = "cero_two")
    public void getMySessions(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM GetMySession WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPathSessions,strQuery1);

        objData.fillHeader(recordset);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        Response resp = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer " + objData.api_token(recordset))
                .baseUri(baseURI)
                .get(objData.fillBasePath(recordset));

        resp.prettyPrint();
        System.out.println("Bearer " + objData.api_token(recordset));
        int code = resp.getStatusCode();
        int expected = objData.fillCode(recordset);

        Assert.assertEquals(code,expected);
        System.out.println(resp.getStatusLine());

        System.out.println("Status code is " +code);

        if( code == 200) {

            JsonPath jsonPathValidator = resp.jsonPath();
            String info = jsonPathValidator.getString("data.id");
            String vect2[]= info.split(",");
            int total_sessions = vect2.length;
            System.out.println("Session ID's " + info);
            System.out.println("vect Session ID's" +" "+ total_sessions );

            String strQuery2 = "UPDATE DeleteSession set sessionID = '" + vect2[1] +"' WHERE ID = 0";
            objDataDriven.update(strPathSessions,strQuery2);
            String strQuery4 = "UPDATE LeaveSession set sessionID = '" + vect2[2] +"' WHERE ID = 0";
            String strQuery5 = "UPDATE DeclineSession set sessionID = '" + vect2[3] +"' WHERE ID = 0";
            objDataDriven.update(strPathSessions,strQuery4);
            objDataDriven.update(strPathSessions,strQuery5);
            String strQuery6 = "UPDATE AcceptSession set sessionID = '" + vect2[4] +"' WHERE ID = 0";
            String strQuery7 = "UPDATE MarkSessionasCompleted set sessionID = '" + vect2[5] +"' WHERE ID = 0";
            objDataDriven.update(strPathSessions,strQuery6);
            objDataDriven.update(strPathSessions,strQuery7);
        }
    }

    @Test(priority = 3, dataProvider = "cero_two")
    public void createSession(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM CreateSession WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPathSessions,strQuery1);

        objData.fillHeader(recordset);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        Response resp = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer " + objData.api_token(recordset))
                .body(objData.fillSession()).baseUri(baseURI)
                .post(objData.fillBasePath(recordset));

        resp.prettyPrint();
        System.out.println("Bearer " + objData.api_token(recordset));
        int code = resp.getStatusCode();
        int expected = objData.fillCode(recordset);

        Assert.assertEquals(code,expected);
        System.out.println(resp.getStatusLine());

        System.out.println("Status code is " +code);
    }

    @Test(priority = 4, dataProvider = "cero_two")
    public void updateSession(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM UpdateSession WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPathSessions,strQuery1);

        objData.fillHeader(recordset);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        Response resp = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer " + objData.api_token(recordset))
                .body(objData.fillSession()).baseUri(baseURI)
                .post(objData.fillBasePath(recordset));

        resp.prettyPrint();
        System.out.println("Bearer " + objData.api_token(recordset));
        int code = resp.getStatusCode();
        int expected = objData.fillCode(recordset);

        Assert.assertEquals(code,expected);
        System.out.println(resp.getStatusLine());

        System.out.println("Status code is " +code);
    }

    @Test(priority = 5, dataProvider = "cero_two")
    public void getSessionByID(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM GetSessionByID WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPathSessions,strQuery1);

        objData.fillHeader(recordset);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        Response resp = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer " + objData.api_token(recordset))
                .baseUri(baseURI).get(objData.fillBasePath(recordset) + objData.fillSessionID(recordset));

        resp.prettyPrint();
        System.out.println("Bearer" + objData.api_token(recordset));
        int code = resp.getStatusCode();
        int expected = objData.fillCode(recordset);

        Assert.assertEquals(code,expected);
        System.out.println(resp.getStatusLine());

        System.out.println("Status code is " +code);
    }

    @Test(priority = 6, dataProvider = "cero_two")
    public void deleteSession(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM DeleteSession WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPathSessions,strQuery1);

        System.out.println(objData.fillBasePath(recordset) + objData.fillSessionID(recordset) + "/delete");
        objData.fillHeader(recordset);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        Response resp = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer" + objData.api_token(recordset))
                .baseUri(baseURI)
                .delete(objData.fillBasePath(recordset) + objData.fillSessionID(recordset));

        resp.prettyPrint();
        System.out.println("Bearer" + objData.api_token(recordset));
        int code = resp.getStatusCode();
        int expected = objData.fillCode(recordset);

        Assert.assertEquals(code,expected);
        System.out.println(resp.getStatusLine());

        System.out.println("Status code is " +code);
    }

    @Test(priority = 7, dataProvider = "cero_two")
    public void leaveSession(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM LeaveSession WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPathSessions,strQuery1);

        System.out.println(objData.fillBasePath(recordset) + objData.fillSessionID(recordset) + "/leave");
        objData.fillHeader(recordset);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        Response resp = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer" + objData.api_token(recordset))
                .baseUri(baseURI)
                .delete(objData.fillBasePath(recordset) + objData.fillSessionID(recordset) + "/leave");

        resp.prettyPrint();
        System.out.println("Bearer" + objData.api_token(recordset));
        int code = resp.getStatusCode();
        int expected = objData.fillCode(recordset);

        Assert.assertEquals(code,expected);
        System.out.println(resp.getStatusLine());

        System.out.println("Status code is " +code);
    }

    @Test(priority = 8, dataProvider = "cero_two")
    public void removeFromSession(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM RemoveFromSession WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPathSessions,strQuery1);

        objData.fillHeader(recordset);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        Response resp = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer" + objData.api_token(recordset))
                .body(objData.fillSession()).baseUri(baseURI)
                .post(objData.fillBasePath(recordset));

        resp.prettyPrint();
        System.out.println("Bearer" + objData.api_token(recordset));
        int code = resp.getStatusCode();
        int expected = objData.fillCode(recordset);

        Assert.assertEquals(code,expected);
        System.out.println(resp.getStatusLine());

        System.out.println("Status code is " +code);
    }

    @Test(priority = 9, dataProvider = "cero_two")
    public void acceptSession(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM AcceptSession WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPathSessions,strQuery1);

        System.out.println(objData.fillBasePath(recordset) + objData.fillSessionID(recordset) + "/accept");
        objData.fillHeader(recordset);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        Response resp = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer" + objData.api_token(recordset))
                .body(objData.fillSession()).baseUri(baseURI)
                .post(objData.fillBasePath(recordset) + objData.fillSessionID(recordset) + "/accept");

        resp.prettyPrint();
        System.out.println("Bearer" + objData.api_token(recordset));
        int code = resp.getStatusCode();
        int expected = objData.fillCode(recordset);

        Assert.assertEquals(code,expected);
        System.out.println(resp.getStatusLine());

        System.out.println("Status code is " +code);
    }

    @Test(priority = 10, dataProvider = "cero_two")
    public void markSessionasCompleted(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM MarkSessionasCompleted WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPathSessions,strQuery1);

        objData.fillHeader(recordset);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        Response resp = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer" + objData.api_token(recordset))
                .body(objData.fillSession()).baseUri(baseURI)
                .post(objData.fillBasePath(recordset));

        resp.prettyPrint();
        System.out.println("Bearer" + objData.api_token(recordset));
        int code = resp.getStatusCode();
        int expected = objData.fillCode(recordset);

        Assert.assertEquals(code,expected);
        System.out.println(resp.getStatusLine());

        System.out.println("Status code is " +code);
    }

    @Test(priority = 11, dataProvider = "cero_two")
    public void declineSession(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM DeclineSession WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPathSessions,strQuery1);

        System.out.println(objData.fillBasePath(recordset) + objData.fillSessionID(recordset) + "/decline");
        objData.fillHeader(recordset);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        Response resp = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer" + objData.api_token(recordset))
                .body(objData.fillSession()).baseUri(baseURI)
                .post(objData.fillBasePath(recordset) + objData.fillSessionID(recordset) + "/decline");

        resp.prettyPrint();
        System.out.println("Bearer" + objData.api_token(recordset));
        int code = resp.getStatusCode();
        int expected = objData.fillCode(recordset);

        Assert.assertEquals(code,expected);
        System.out.println(resp.getStatusLine());

        System.out.println("Status code is " +code);
    }

    @Test(priority = 12, dataProvider = "cero_two")
    public void scheduleRescheduleSession(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM ScheduleRescheduleSession WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPathSessions,strQuery1);

        objData.fillHeader(recordset);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        Response resp = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer" + objData.api_token(recordset))
                .body(objData.fillSchedule()).baseUri(baseURI)
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
