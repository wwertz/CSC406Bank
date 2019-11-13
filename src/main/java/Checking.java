import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalDate;

public class Checking extends  Account{

    //********************************still needs interest****************************************8

    char type; //t = TMB and g = Gold/Diamond
    boolean hasBackup;
    int backupID; //saving account ID
    LocalDate dateOpened;

    //constructor
    public Checking(int accountID, int custID, double balance, LocalDate dateOpened, boolean hasBackup, int backupID) {
        super(accountID, custID, balance);
        this.dateOpened = dateOpened;
        this.hasBackup = hasBackup;
        this.backupID = backupID;
        checkType();
    }

    //deposit and check to see if upgrade or down grade
    public void deposit(double amount){
        balance += amount;
        checkType();

        //check for fees
        if(type=='t')
            balance -= .50;
    }

    //withdrawal
    public void withdrawal(double amount){
        //check amount vs balance
        if(amount>=balance){
            System.out.println("not enough in balance, checking for backup");
            if(hasBackup==true){
                //****************************needs update after savings finished*******************************
            }
        }else if(amount<balance){
            balance-= amount;
        }

        //check for fees
        if(type=='t')
            balance -= .50;
    }

    //check type
    public char checkType(){
        if(balance>=1000){
            return type = 'g';
        }else{
            return type = 't';
        }
    }

    public char getType() {return type;}
    public void setType(char type) {
        if(balance>=1000)
            type = 'g';
        else
            type = 's';
    }
    public boolean isHasBackup() {return hasBackup;}
    public void setHasBackup(boolean hasBackup) {this.hasBackup = hasBackup;}
    public double getBalance() {return balance;}
    public void setBalance(int balance) {this.balance = balance;}

    public void JSONCheckToTxt(int AID, int CID, double balance, boolean hasBackup, int backupID){
        JSONObject obj = new JSONObject();
        obj.put("AccountID", AID);
        obj.put("CustomerID", CID);
        obj.put("Balance", balance);
        obj.put("hasBackup", hasBackup);
        obj.put("backupID", backupID);

        try (PrintWriter file = new PrintWriter("/Checkings/"+AID +".txt")) {
            file.write(obj.toJSONString());
            System.out.println("Successfully wrote checkings "+AID+" to file");
            System.out.println("\nJSON Object: " + obj);
        } catch (FileNotFoundException e) {
            System.out.println("Unsuccessful checking Account Write for "+AID);
        }
    }

    @SuppressWarnings("unchecked")
    public void JSONTxtToCheck(int AID) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(
                    "/Checkings/"+AID +".txt"));

            JSONObject CHECKjsonObject = (JSONObject) obj;

            int AccountID = (int) CHECKjsonObject.get("AccountID");
            int CustomerID = (int) CHECKjsonObject.get("CustomerID");
            double Balance = (double) CHECKjsonObject.get("Balance");
            boolean hasBackup = (boolean) CHECKjsonObject.get("hasBackup");
            int backupID = (int) CHECKjsonObject.get("backupID");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}//end of Checking
