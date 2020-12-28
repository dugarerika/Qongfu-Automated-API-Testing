package pom;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Recordset;
import java.net.URISyntaxException;
import org.json.simple.JSONObject;
import java.net.URI;

public class FillData {

    public String fillAuth(Recordset objRecordset) throws FilloException {

        System.out.println(objRecordset.getField("caseNumber"));
        System.out.println(objRecordset.getField("case"));

        JSONObject json= new JSONObject();
        json.put("email",objRecordset.getField("email"));
        json.put("password",objRecordset.getField("password"));
        json.put("for_mobile", true);

        String input = json.toJSONString();
        return input;
    }

    public String fillReg(Recordset objRecordset) throws FilloException {

        System.out.println(objRecordset.getField("caseNumber"));
        System.out.println(objRecordset.getField("case"));

        JSONObject json= new JSONObject();
        json.put("fullname", objRecordset.getField("fullname"));
        json.put("email",objRecordset.getField("email"));
        json.put("password",objRecordset.getField("password"));


        String input = json.toJSONString();
        return input;
    }

    public String fillUsername(Recordset objRecordset) throws FilloException {

        System.out.println(objRecordset.getField("caseNumber"));
        System.out.println(objRecordset.getField("case"));

        JSONObject json= new JSONObject();
        json.put("username", objRecordset.getField("username"));

        String input = json.toJSONString();
        return input;
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

    public String api_token(Recordset objRecordset) throws FilloException, URISyntaxException {

        String input = objRecordset.getField("api_token");
        return input;
    }
}
