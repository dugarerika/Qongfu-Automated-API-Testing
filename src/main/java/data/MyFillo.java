package data;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class MyFillo {

    Fillo fillo=new Fillo();

    //    String strPath, String ID
    public Recordset select(String strPath, String strQuery) throws FilloException {
        Connection connection = fillo.getConnection(strPath);
        Recordset recordset = connection.executeQuery(strQuery);

        while(recordset.next()){
            return recordset;
        }

        recordset.close();
        connection.close();

        return null;
    }

    public Recordset update(String strPath, String strQuery) throws FilloException {
        Connection connection = fillo.getConnection(strPath);
        connection.executeUpdate(strQuery);

        connection.close();
        return null;
    }


}
