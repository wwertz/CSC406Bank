import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Checking extends  Account{

    //********************************still needs interest****************************************8

    protected char type; //t = TMB and g = Gold/Diamond
    protected boolean hasBackup;
    protected String backupID; //saving account ID
    protected LocalDate dateOpened;
    protected int overdrafts;

    //constructor
    public Checking(String custID, String accountID, double balance, String dateOpened, boolean hasBackup, String backupID, int overdrafts) {
        super(accountID, custID, balance);
        if(dateOpened.length()>4)
            this.dateOpened = LocalDate.of(Integer.parseInt(dateOpened.substring(6)),
                    Integer.parseInt(dateOpened.substring(0,2)), Integer.parseInt(dateOpened.substring(3,5)));
        this.hasBackup = hasBackup;
        this.backupID = backupID;
        this.overdrafts = overdrafts;
        checkType();
    }

    //deposit and check to see if upgrade or downgrade
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

    @Override
    public String toString() {
        String date;
        if(dateOpened != null){
            date = dateOpened.toString();
            date = date.substring(5,7) +"/"+ date.substring(8) +"/"+ date.substring(0,4);
        }else {
            date = null;
        }

        return
                custID + ',' +
                        accountID + ',' +
                        balance + ',' +
                        date + ',' +
                        hasBackup + ',' +
                        backupID + ',' +
                        overdrafts;
    }

    public char getType() {return type;}
    public void setType(char type) {this.type = type;}
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