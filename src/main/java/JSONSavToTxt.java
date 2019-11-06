import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONSavToTxt {
    public JSONSavToTxt(int AID, int CID, double balance, char type, LocalDate CDdate, double interestRate){
        JSONObject obj = new JSONObject();
        obj.put("AccountID", AID);
        obj.put("CustomerID", CID);
        obj.put("Balance", balance);
        obj.put("type", type);
        obj.put("CDdate", CDdate);
        obj.put("interestRate", interestRate);

        try (PrintWriter file = new PrintWriter("/Savings/"+AID +".txt")) {
            file.write(obj.toJSONString());
            System.out.println("Successfully wrote savings "+AID+" to file");
            System.out.println("\nJSON Object: " + obj);
        } catch (FileNotFoundException e) {
            System.out.println("Unsuccessful savings Account Write for "+AID);
        }
    }
}
