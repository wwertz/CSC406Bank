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
     * If the dates are not null, they will be changed from String to LocalDate
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
     * accrueInterest function
     *
     * If dateAccrued is not current month, intrest will be added and date will be updated.
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
     * The function that is responsible for taking the taking an AMOUNT of money and adding it to the current balance.
     * It then checks the type of check that is being deposited and subtracts a 50 cent fee.
     *
     * @param amount / double money
     */
    public void deposit(double amount){
        balance += amount;
        checkType();

        //check for fees
        if(type.equals("t"))
            balance -= .50;
    }

    /**
     * withdrawal function
     *
     * This function takes a certain AMOUNT of money and subtracts it from the total balance.
     * It checks if the amount to be withdrawn is more than the current balance, and thats true, it checks if there is
     * a backup account and withdraws from that.
     * If there is not a backup account, the withdrawal is a failure and a penalty is subtracted from the balance.
     * If the balance is more than the amount, it simply subtracts from the balance, and checks whether there is a fee.
     *
     * @param amount / double money
     * @return / boolean success of withdrawal
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
     * This function checks whether the balance is is greater than or equal to 1000 dollars, and if it's true,
     * then the type of the checking account is "Gold Checking". If it's false, the type of the checking account
     * is "TMB Checking".
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
     * This toString function prints out one of three things.
     * It can print the data that an account was opened.
     * It can print the data that an account is to accrue interest.
     * It can print the account data of the checking account.
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