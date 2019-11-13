import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.*;

//********************************still needs interest****************************************8

public class Saving extends  Account{

    protected char type; // s = simple and c = CD
    protected double interestRate;
    protected LocalDate CDdate; //date CD matures
    protected LocalDate dateOpened;


    public Saving(String accountID, String custID, double balance, LocalDate dateOpened, char type, LocalDate CDdate, double interestRate) {
        super(accountID, custID, balance);
        this.dateOpened = dateOpened;
        this.type = type;
        this.CDdate = CDdate;
        this.interestRate = interestRate;
    }

    //deposit
    public void deposit(double amount){
        balance += amount;
    }

    //withdrawal
    public void withdrawal(double amount){
        //check amount vs balance
        if(type=='t'){
            if(amount<=balance)
                balance -= amount;
            else if(amount>balance){
                System.out.println("not enough cash");
            }
        }else if(type=='c'){
            //is CD and before mature date
            if(LocalDate.now().isBefore(CDdate)){
                balance -= amount;
            }else {
                balance -= amount;
            }
        }
    }

    //getters and setters
    public char getType() {return type;}
    public void setType(char type) {this.type = type;}
    public LocalDate getCDdate() {return CDdate;}
    public void setCDdate(LocalDate CDdate) {this.CDdate = CDdate;}
    public double getInterestRate() {return interestRate;}
    public void setInterestRate(double interestRate) {this.interestRate = interestRate;}
    public double getBalance() {return balance;}
    public void setBalance(int balance) {this.balance = balance;}

    public void JSONSavToTxt(int AID, int CID, double balance, char type, LocalDate CDdate, double interestRate){

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

    @SuppressWarnings("unchecked")
    public void JSONTxtToSav(int AID) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(
                    "/Savings/"+AID +".txt"));

            JSONObject SAVjsonObject = (JSONObject) obj;

            int AccountID = (int) SAVjsonObject.get("AccountID");
            int CustomerID = (int) SAVjsonObject.get("CustomerID");
            double Balance = (double) SAVjsonObject.get("Balance");
            char type = (char) SAVjsonObject.get("type");
            LocalDate CDdate = (LocalDate) SAVjsonObject.get("CDdate");
            double interestRate = (double) SAVjsonObject.get("interestRate");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}//end of Saving
