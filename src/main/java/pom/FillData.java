package pom;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Recordset;
import org.json.simple.JSONObject;
import org.openqa.selenium.net.Urls;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class FillData {

    public String fillAuth(Recordset objRecordset) throws FilloException {

        System.out.println(objRecordset.getField("caso"));

        JSONObject json= new JSONObject();
        json.put("email",objRecordset.getField("email"));
        json.put("password",objRecordset.getField("password"));
        json.put("for_mobile", true);

        String input = json.toJSONString();
        return input;
    }

    public String fillReg(Recordset objRecordset) throws FilloException {

        System.out.println(objRecordset.getField("caso"));

        JSONObject json= new JSONObject();
        json.put("fullname", objRecordset.getField("fullname"));
        json.put("email",objRecordset.getField("email"));
        json.put("password",objRecordset.getField("password"));


        String input = json.toJSONString();
        return input;
    }

    public Integer fillCode(Recordset objRecordset) throws FilloException {

         String info = objRecordset.getField("code");
         int code = Integer.parseInt(info);
        return code;
    }

    public URI fillURL(Recordset objRecordset) throws FilloException, URISyntaxException {
        String s = objRecordset.getField("URL");
        URI url = new URI(s);
        return url;
    }
}
