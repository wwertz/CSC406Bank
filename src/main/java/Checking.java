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
    protected Saving backup;
    protected LocalDate dateAccrued;

    //constructor
    public Checking(String custID, String accountID, double balance, String dateOpened, boolean hasBackup, String backupID, int overdrafts, String type, String dateAccrued) {
        super(accountID, custID, balance, type, dateAccrued);
        if(dateOpened.length()>4)
            this.dateOpened = LocalDate.of(Integer.parseInt(dateOpened.substring(6)),
                    Integer.parseInt(dateOpened.substring(0,2)), Integer.parseInt(dateOpened.substring(3,5)));
        this.hasBackup = hasBackup;
        this.backupID = backupID;
        this.overdrafts = overdrafts;
        checkType();
        if(dateAccrued.length()>4)
            this.dateAccrued = LocalDate.of(Integer.parseInt(dateAccrued.substring(6)),
                    Integer.parseInt(dateAccrued.substring(0,2)), Integer.parseInt(dateAccrued.substring(3,5)));

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
            System.out.println("Not enough in account. Checking for backup");

            //check for backup;
            if(hasBackup==true){

                for(int i=0; i<Main.savings.size(); i++){
                    if(this.backupID.equals(Main.savings.get(i).getAccountID())){
                        backup = Main.savings.get(i);
                    }
                }

                if(backup.getBalance() + balance >= amount){
                    amount -= balance;
                    balance = 0;
                    backup.withdrawal(amount+.5);
                }else{
                    //withdrawal(amount);
                    //check for fees
                    balance -= 2.50;
                }
            }

        }else{
            balance-= amount;
            //check for fees
            if(type.equals("TMB Checking"))
                balance -= .50;
        }

        return;
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

        String aDate;
        if(dateAccrued != null){
            aDate = dateAccrued.toString();
            aDate = aDate.substring(5,7) +"/"+ aDate.substring(8) +"/"+ aDate.substring(0,4);
        }else {
            aDate = null;
        }

        return
                custID + ',' +
                        accountID + ',' +
                        balance + ',' +
                        date + ',' +
                        hasBackup + ',' +
                        backupID + ',' +
                        overdrafts + ',' +
                        type + ',' +
                        aDate;
    }

    public boolean isHasBackup() {return hasBackup;}
    public void setHasBackup(boolean hasBackup) {this.hasBackup = hasBackup;}
    public void setBackupID(String backupID) {
        this.backupID = backupID;
    }
    public String getBackupID() {return backupID;}
    public int getOverdrafts() {return overdrafts;}
}//end of Checking