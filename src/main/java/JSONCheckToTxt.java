import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONCheckToTxt {
    public JSONCheckToTxt(int AID, int CID, double balance, boolean hasBackup, int backupID){
        JSONObject obj = new JSONObject();
        obj.put("AccountID", AID);
        obj.put("CustomerID", CID);
        obj.put("Balance", balance);
        obj.put("hasBackup", backupID);
        obj.put("backupID", backupID);

//        JSONArray company = new JSONArray();
//        company.add("Compnay: eBay");
//        company.add("Compnay: Paypal");
//        company.add("Compnay: Google");
//        obj.put("Company List", company);

        try (PrintWriter file = new PrintWriter(AID +".txt")) {
            file.write(obj.toJSONString());
            System.out.println("Successfully wrote checkings "+AID+" to file");
            System.out.println("\nJSON Object: " + obj);
        } catch (FileNotFoundException e) {
            System.out.println("Unsuccessful checking Account Write for "+AID);
        }
    }
}
