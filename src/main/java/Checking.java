import java.time.LocalDate;

/**
 *
 * This class is for the Checking account object.
 *
 */
public class Checking extends  Account{

    protected boolean hasBackup;
    protected String backupID; //saving account ID
    protected LocalDate dateOpened;
    protected int overdrafts;
    protected Saving backup;
    protected LocalDate dateAccrued;

    /**
     * Constructor for Checking Account object.
     * TODO explain what the if statement is doing
     */
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

    /**
     * TODO not called
     */
    public void accrueInterest(){
        if(type.equals("Gold Checking")) {
            if (dateAccrued == null || dateAccrued.getYear() <= LocalDate.now().getYear() && dateAccrued.getMonthValue() < LocalDate.now().getMonthValue() ||
                    dateAccrued.getYear() <= LocalDate.now().getYear() && (dateAccrued.getMonthValue() == 12 && LocalDate.now().getMonthValue() == 1)) {
                balance = balance + (balance * (Main.savingsInterest / 12));
                dateAccrued = LocalDate.now();
            } else {
                System.out.println("Interest already accrued");
            }
        }

    }



    /**
     * deposit function
     *
     * The function that is responsible for taking the
     *
     * @param amount / double money
     */
    //deposit and check to see if upgrade or downgrade TODO explain
    public void deposit(double amount){
        balance += amount;
        checkType();

        //check for fees
        if(type.equals("t"))
            balance -= .50;
    }

    /**
     * withdrawl function
     *
     * TODO
     *
     * @param amount / double money
     * @return / boolean TODO 1-2 word description
     */
    public boolean withdrawal(double amount){
        //check amount vs balance
        boolean success;
        if(amount>=balance){
            System.out.println("Not enough in account. Checking for backup");

            //check for backup;
            if(hasBackup){

                for(int i=0; i<Main.savings.size(); i++){
                    if(this.backupID.equals(Main.savings.get(i).getAccountID())){
                        backup = Main.savings.get(i);
                    }
                }

                if(backup.getBalance() + balance >= amount){
                    amount -= balance;
                    balance = 0;
                    backup.withdrawal(amount+.5);
                    success = true;
                }else{
                    //withdrawal(amount);
                    //check for fees
                    balance -= 20;
                    success = false;
                }

            }else{
                balance -= 20;
                success = false;
            }

        }else{
            balance-= amount;
            //check for fees
            if(type.equals("TMB Checking"))
                balance -= .50;
            success = true;
        }

        return success;
    }

    /**
     * checkType function
     *
     * TODO
     *
     * @return / String type
     */
    public String checkType(){
        if(balance>=1000){
            return type = "Gold Checking";
        }else{
            return type = "TMB Checking";
        }
    }

    /**
     * toString function
     *
     * TODO explaib
     *
     * @return toString
     */
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


    /**
     * Setters and Getters for Checking account object
     */
    public boolean isHasBackup() {return hasBackup;}
    public void setHasBackup(boolean hasBackup) {this.hasBackup = hasBackup;}
    public void setBackupID(String backupID) { this.backupID = backupID; }
    public String getBackupID() {return backupID;}
    public int getOverdrafts() {return overdrafts;}
}//end of Checking