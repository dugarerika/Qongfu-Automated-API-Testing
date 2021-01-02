package pom;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Recordset;
import org.json.simple.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class FillData {

    public void fillHeader(Recordset objRecordset) throws FilloException {

        System.out.println(objRecordset.getField("caseNumber"));
        System.out.println(objRecordset.getField("case"));
    }

    public String fillAuth(Recordset objRecordset) throws FilloException {

        JSONObject json= new JSONObject();
        json.put("email",objRecordset.getField("email"));
        json.put("password",objRecordset.getField("password"));
        json.put("for_mobile", true);

        String input = json.toJSONString();
        return input;
    }

    public String fillReg(Recordset objRecordset) throws FilloException {

        JSONObject json= new JSONObject();
        json.put("fullname", objRecordset.getField("fullname"));
        json.put("email",objRecordset.getField("email"));
        json.put("password",objRecordset.getField("password"));

        String input = json.toJSONString();
        return input;
    }

    public String fillUsername(Recordset objRecordset) throws FilloException {

        JSONObject json= new JSONObject();
        json.put("username", objRecordset.getField("username"));

        String input = json.toJSONString();
        return input;
    }

    public String fillActivity2() throws FilloException {

        JSONObject json= new JSONObject();
        json.put("name", "Outdoor Testing Activity");
        json.put("qongfu_id", "3");
        json.put("date_from", "2020-12-31");
        json.put("date_to", "2021-01-17");
        json.put("time_from", "03:27:00");
        json.put("time_to", "15:27:00");
        json.put("location_type", "1");

        ArrayList<Integer> list = new ArrayList<>();
        int x = 1, y=3, t=2;
        list.add(x);
        list.add(y);
        json.put("days", list);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("user_id", "add2afb0-0bce-42c7-bbf4-2212565bde6c");
        jsonObject1.put("is_admin", false);

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("user_id", "d4f32149-e1b4-4f62-a1e2-3c84b792e01a");
        jsonObject2.put("is_admin", false);

        List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
        jsonObjects.add(jsonObject1);
        jsonObjects.add(jsonObject2);

        json.put("participants",jsonObjects);

        String input = json.toJSONString();
        System.out.println(input);

        return input;
    }

    public String fillActivity1() throws FilloException {

        JSONObject json= new JSONObject();
        json.put("name", "Outdoor Pruebas two person activity ");
        json.put("qongfu_id", "3");
        json.put("date_from", "2020-12-31");
        json.put("date_to", "2021-01-17");
        json.put("time_from", "03:27:00");
        json.put("time_to", "15:27:00");
        json.put("location_type", "1");

        ArrayList<Integer> list = new ArrayList<>();
        int x = 1, y=3, t=2;
        list.add(x);
        list.add(y);
        json.put("days", list);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("user_id", "add2afb0-0bce-42c7-bbf4-2212565bde6c");
        jsonObject1.put("is_admin", false);

        List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
        jsonObjects.add(jsonObject1);

        json.put("participants",jsonObjects);

        String input = json.toJSONString();
        System.out.println(input);

        return input;
    }

    public String fillActivity() throws FilloException {

        JSONObject json= new JSONObject();
        json.put("name", "Outdoor Pruebas two person activity ");
        json.put("qongfu_id", "3");
        json.put("date_from", "2020-12-31");
        json.put("date_to", "2021-01-17");
        json.put("time_from", "03:27:00");
        json.put("time_to", "15:27:00");
        json.put("location_type", "1");

        ArrayList<Integer> list = new ArrayList<>();
        int x = 1, y=3, t=2;
        list.add(x);
        list.add(y);
        json.put("days", list);

        String input = json.toJSONString();
        System.out.println(input);

        return input;
    }

    public String fillSession() throws FilloException {

        JSONObject json= new JSONObject();
        json.put("subtitle", " ");
        json.put("date", "2020-12-31");
        json.put("from", "03:27:00");
        json.put("to", "15:27:00");
        json.put("location_type", "1");

        JSONObject attachment= new JSONObject();
        attachment.put("attachment_type", 2);
        attachment.put("attachment_id", 2);
        attachment.put("attachment_url", "/qongfus");

        json.put("attachment",attachment);

        json.put("active", false);
        json.put("fullday", true);
        json.put("notes", "Notes");

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("user_id", "add2afb0-0bce-42c7-bbf4-2212565bde6c");
        jsonObject1.put("is_admin", false);

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("user_id", "d4f32149-e1b4-4f62-a1e2-3c84b792e01a");
        jsonObject2.put("is_admin", false);

        List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
        jsonObjects.add(jsonObject1);
        jsonObjects.add(jsonObject2);

        json.put("participants",jsonObjects);

        String input = json.toJSONString();
        System.out.println(input);

        return input;
    }

    public String fillSchedule() throws FilloException {

        JSONObject json= new JSONObject();

        json.put("date_from", "2022-12-31");
        json.put("date_to", "2022-01-17");
        json.put("timezone", "999");
        json.put("country", "1");

        String input = json.toJSONString();
        System.out.println(input);

        return input;
    }

    public String fillActivityID(Recordset objRecordset) throws FilloException {

        String info = objRecordset.getField("activityID");
        return info;
    }

    public String fillSessionID(Recordset objRecordset) throws FilloException {

        String info = objRecordset.getField("sessionID");
        return info;
    }

    public Integer fillCode(Recordset objRecordset) throws FilloException {

         String info = objRecordset.getField("code");
         int code = Integer.parseInt(info);
        return code;
    }

    public URI fillBasePath(Recordset objRecordset) throws FilloException, URISyntaxException {
        String s = objRecordset.getField("basePath");
        URI url = new URI(s);
        return url;
    }

    public String api_token(Recordset objRecordset) throws FilloException {

        String input = objRecordset.getField("api_token");
        return input;
    }
}
