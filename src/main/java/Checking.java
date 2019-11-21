import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Checking extends  Account{

    //********************************still needs interest****************************************8

    protected boolean hasBackup;
    protected String backupID; //saving account ID
    protected LocalDate dateOpened;
    protected int overdrafts;

    //constructor
    public Checking(String custID, String accountID, double balance, String dateOpened, boolean hasBackup, String backupID, int overdrafts, String type) {
        super(accountID, custID, balance, type);
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
        if(type.equals("t"))
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
        if(type.equals("TMB Checking"))
            balance -= .50;

    }

    //check type
    public String checkType(){
        if(balance>=1000){
            return type = "Gold Checking";
        }else{
            return type = "TMB Checking";
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
                        overdrafts + ',' +
                        type;
    }

    public boolean isHasBackup() {return hasBackup;}
    public void setHasBackup(boolean hasBackup) {this.hasBackup = hasBackup;}
}//end of Checking