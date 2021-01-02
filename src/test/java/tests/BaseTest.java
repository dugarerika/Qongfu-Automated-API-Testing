package tests;

import com.codoid.products.exception.FilloException;
import data.MyFillo;
import org.testng.annotations.DataProvider;
import pom.FillData;

public class BaseTest {

    FillData objData = new FillData();
    MyFillo objDataDriven = new MyFillo();
    String strPathUsers = "src/test/resources/data/users.xlsx";
    String strPathActivities = "src/test/resources/data/activities.xlsx";
    String strPathSessions = "src/test/resources/data/sessions.xlsx";
    String baseURI = "https://staging-api.qongfu.com/api";
    String api_token = " ";

    @DataProvider(name = "cero_three")
    public Object [][] cerothree() {

        return new Object [][] {{0},{1},{2},{3}};
    }

    @DataProvider(name = "cero_two")
    public Object [][] dataProviderMethod() {

        return new Object [][] {{0},{1},{2}};
    }

    public  void queries()throws FilloException{

        String strQuery2 = "UPDATE CheckUsername set api_token = '" + api_token +"' WHERE ID = 0";
        String strQuery3 = "UPDATE CheckUsername set api_token = '" + api_token +"' WHERE ID = 2";
        objDataDriven.update(strPathUsers,strQuery2);
        objDataDriven.update(strPathUsers,strQuery3);
        String strQuery4 = "UPDATE UpdateUsername set api_token = '" + api_token +"' WHERE ID = 0";
        String strQuery5 = "UPDATE UpdateUsername set api_token = '" + api_token +"' WHERE ID = 2";
        objDataDriven.update(strPathUsers,strQuery4);
        objDataDriven.update(strPathUsers,strQuery5);
        String strQuery6 = "UPDATE LogOut set api_token = '" + api_token +"' WHERE ID = 0";
        objDataDriven.update(strPathUsers,strQuery6);
        String strQuery7 = "UPDATE UserDetails set api_token = '" + api_token +"' WHERE ID = 0";
        objDataDriven.update(strPathUsers,strQuery7);
        String strQuery8 = "UPDATE GetMyActivity set api_token = '" + api_token +"' WHERE ID = 0";
        objDataDriven.update(strPathActivities,strQuery8);
        String strQuery9 = "UPDATE GetMySession set api_token = '" + api_token +"' WHERE ID = 0";
        objDataDriven.update(strPathSessions,strQuery9);
        String strQuery10 = "UPDATE CreateActivity set api_token = '" + api_token +"' WHERE ID = 0";
        String strQuery11 = "UPDATE CreateSession set api_token = '" + api_token +"' WHERE ID = 0";
        objDataDriven.update(strPathActivities,strQuery10);
        objDataDriven.update(strPathSessions,strQuery11);
        String strQuery12 = "UPDATE UpdateActivity set api_token = '" + api_token +"' WHERE ID = 0";
        String strQuery13 = "UPDATE UpdateSession set api_token = '" + api_token +"' WHERE ID = 0";
        objDataDriven.update(strPathActivities,strQuery12);
        objDataDriven.update(strPathSessions,strQuery13);
        String strQuery14 = "UPDATE GetActivityByID set api_token = '" + api_token +"' WHERE ID = 0";
        String strQuery15 = "UPDATE GetSessionByID set api_token = '" + api_token +"' WHERE ID = 0";
        objDataDriven.update(strPathActivities,strQuery14);
        objDataDriven.update(strPathSessions,strQuery15);
        String strQuery16 = "UPDATE DeleteActivity set api_token = '" + api_token +"' WHERE ID = 0";
        String strQuery17 = "UPDATE DeleteSession set api_token = '" + api_token +"' WHERE ID = 0";
        objDataDriven.update(strPathActivities,strQuery16);
        objDataDriven.update(strPathSessions,strQuery17);
        String strQuery18 = "UPDATE LeaveActivity set api_token = '" + api_token +"' WHERE ID = 0";
        String strQuery19 = "UPDATE LeaveSession set api_token = '" + api_token +"' WHERE ID = 0";
        objDataDriven.update(strPathActivities,strQuery18);
        objDataDriven.update(strPathSessions,strQuery19);
        String strQuery20 = "UPDATE RemoveFromActivity set api_token = '" + api_token +"' WHERE ID = 0";
        String strQuery21 = "UPDATE RemoveFromSession set api_token = '" + api_token +"' WHERE ID = 0";
        objDataDriven.update(strPathActivities,strQuery20);
        objDataDriven.update(strPathSessions,strQuery21);
        String strQuery22 = "UPDATE AcceptActivity set api_token = '" + api_token +"' WHERE ID = 0";
        String strQuery23 = "UPDATE AcceptSession set api_token = '" + api_token +"' WHERE ID = 0";
        objDataDriven.update(strPathActivities,strQuery22);
        objDataDriven.update(strPathSessions,strQuery23);
        String strQuery24 = "UPDATE DeclineActivity set api_token = '" + api_token +"' WHERE ID = 0";
        String strQuery25 = "UPDATE DeclineSession set api_token = '" + api_token +"' WHERE ID = 0";
        objDataDriven.update(strPathActivities,strQuery24);
        objDataDriven.update(strPathSessions,strQuery25);
        String strQuery26 = "UPDATE ScheduleRescheduleActivity set api_token = '" + api_token +"' WHERE ID = 0";
        String strQuery27 = "UPDATE ScheduleRescheduleSession set api_token = '" + api_token +"' WHERE ID = 0";
        objDataDriven.update(strPathActivities,strQuery26);
        objDataDriven.update(strPathSessions,strQuery27);
    }



}