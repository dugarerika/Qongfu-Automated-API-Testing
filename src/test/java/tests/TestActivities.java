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

public class TestActivities extends BaseTest {

    @Test(priority = 2, dataProvider = "cero_two")
    public void getMyActivities(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM GetMyActivity WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPathActivities,strQuery1);

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

        if( code == 200){

            JsonPath jsonPathValidator = resp.jsonPath();
            String info = jsonPathValidator.getString("data.id");
            String vect1[]= info.split(",");
            int total_activities = vect1.length;
            System.out.println("Activity ID's " + info);


            System.out.println("vect Activity ID's" +" "+ total_activities );
            String strQuery2 = "UPDATE DeleteActivity set activityID = '" + vect1[1] +"' WHERE ID = 0";
            objDataDriven.update(strPathActivities,strQuery2);
            String strQuery4 = "UPDATE LeaveActivity set activityID = '" + vect1[2] +"' WHERE ID = 0";
            String strQuery5 = "UPDATE DeclineActivity set activityID = '" + vect1[3] +"' WHERE ID = 0";
            objDataDriven.update(strPathActivities,strQuery4);
            objDataDriven.update(strPathActivities,strQuery5);
            String strQuery6 = "UPDATE AcceptActivity set activityID = '" + vect1[4] +"' WHERE ID = 0";
            String strQuery7 = "UPDATE MarkActivityasCompleted set activityID = '" + vect1[4] +"' WHERE ID = 0";
            objDataDriven.update(strPathActivities,strQuery6);
            objDataDriven.update(strPathActivities,strQuery7);

        }
    }

    @Test(priority = 3, dataProvider = "cero_two")
    public void createActivities(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM CreateActivity WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPathActivities,strQuery1);

        objData.fillHeader(recordset);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        Response resp = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer " + objData.api_token(recordset))
                .body(objData.fillActivity1()).baseUri(baseURI)
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
    public void updateActivities(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM UpdateActivity WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPathActivities,strQuery1);

        objData.fillHeader(recordset);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        Response resp = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer " + objData.api_token(recordset))
                .body(objData.fillActivity2()).baseUri(baseURI)
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
    public void getActivityByID(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM GetActivityByID WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPathActivities,strQuery1);

        objData.fillHeader(recordset);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        Response resp = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer " + objData.api_token(recordset))
                .baseUri(baseURI)
                .get(objData.fillBasePath(recordset) + objData.fillActivityID(recordset));

        resp.prettyPrint();
        System.out.println("Bearer" + objData.api_token(recordset));
        int code = resp.getStatusCode();
        int expected = objData.fillCode(recordset);

        Assert.assertEquals(code,expected);
        System.out.println(resp.getStatusLine());

        System.out.println("Status code is " +code);
    }

    @Test(priority = 6, dataProvider = "cero_two")
    public void deleteActivity(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM DeleteActivity WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPathActivities,strQuery1);

        objData.fillHeader(recordset);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        Response resp = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer" + objData.api_token(recordset))
                .body(objData.fillActivity()).baseUri(baseURI)
                .delete(objData.fillBasePath(recordset) + objData.fillActivityID(recordset));

        resp.prettyPrint();
        System.out.println("Bearer" + objData.api_token(recordset));
        int code = resp.getStatusCode();
        int expected = objData.fillCode(recordset);

        Assert.assertEquals(code,expected);
        System.out.println(resp.getStatusLine());

        System.out.println("Status code is " +code);
    }

    @Test(priority = 7, dataProvider = "cero_two")
    public void leaveActivity(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM LeaveActivity WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPathActivities,strQuery1);

        System.out.println(objData.fillBasePath(recordset) + objData.fillActivityID(recordset) + "/leave");
        objData.fillHeader(recordset);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        Response resp = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer" + objData.api_token(recordset))
                .body(objData.fillActivity()).baseUri(baseURI)
                .delete(objData.fillBasePath(recordset) + objData.fillActivityID(recordset) + "/leave");

        resp.prettyPrint();
        System.out.println("Bearer" + objData.api_token(recordset));
        int code = resp.getStatusCode();
        int expected = objData.fillCode(recordset);

        Assert.assertEquals(code,expected);
        System.out.println(resp.getStatusLine());

        System.out.println("Status code is " +code);
    }

    @Test(priority = 8, dataProvider = "cero_two")
    public void removeFromActivity(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM RemoveFromActivity WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPathActivities,strQuery1);

        objData.fillHeader(recordset);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        Response resp = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer" + objData.api_token(recordset))
                .body(objData.fillActivity()).baseUri(baseURI)
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
    public void acceptActivity(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM AcceptActivity WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPathActivities,strQuery1);

        objData.fillHeader(recordset);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        Response resp = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer" + objData.api_token(recordset))
                .body(objData.fillActivity()).baseUri(baseURI)
                .post(objData.fillBasePath(recordset) + objData.fillActivityID(recordset) + "/accept");

        resp.prettyPrint();
        System.out.println("Bearer" + objData.api_token(recordset));
        int code = resp.getStatusCode();
        int expected = objData.fillCode(recordset);

        Assert.assertEquals(code,expected);
        System.out.println(resp.getStatusLine());

        System.out.println("Status code is " +code);
    }

    @Test(priority = 10, dataProvider = "cero_two")
    public void markActivityasCompleted(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM MarkActivityasCompleted WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPathActivities,strQuery1);
        System.out.println(objData.fillBasePath(recordset) + objData.fillActivityID(recordset) + "/completed");
        objData.fillHeader(recordset);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        Response resp = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer" + objData.api_token(recordset))
                .body(objData.fillActivity()).baseUri(baseURI)
                .post(objData.fillBasePath(recordset) + objData.fillActivityID(recordset) + "/completed");

        resp.prettyPrint();
        System.out.println("Bearer" + objData.api_token(recordset));
        int code = resp.getStatusCode();
        int expected = objData.fillCode(recordset);

        Assert.assertEquals(code,expected);
        System.out.println(resp.getStatusLine());

        System.out.println("Status code is " +code);
    }

    @Test(priority = 11, dataProvider = "cero_two")
    public void declineActivity(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM DeclineActivity WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPathActivities,strQuery1);

        System.out.println(objData.fillBasePath(recordset) + objData.fillActivityID(recordset) + "/decline");
        objData.fillHeader(recordset);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        Response resp = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer" + objData.api_token(recordset))
                .body(objData.fillActivity()).baseUri(baseURI)
                .post(objData.fillBasePath(recordset) + objData.fillActivityID(recordset) + "/decline");

        resp.prettyPrint();
        System.out.println("Bearer" + objData.api_token(recordset));
        int code = resp.getStatusCode();
        int expected = objData.fillCode(recordset);

        Assert.assertEquals(code,expected);
        System.out.println(resp.getStatusLine());

        System.out.println("Status code is " +code);
    }

    @Test(priority = 12, dataProvider = "cero_two")
    public void scheduleReschedule(int ID) throws FilloException, URISyntaxException {

        String strQuery1 = "SELECT * FROM ScheduleRescheduleActivity WHERE ID = '" + ID + "'";
        Recordset recordset = objDataDriven.select(strPathActivities,strQuery1);

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
