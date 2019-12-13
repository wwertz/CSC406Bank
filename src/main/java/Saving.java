import java.time.*;

/**
 * This class is for the Savings account object.
 */
public class Saving extends  Account{

    protected boolean isCD;
    protected double interestRate;
    protected LocalDate CDdate; //date CD matures
    protected LocalDate dateOpened;
    protected LocalDate dateAccrued;
    protected double initBal;

    /**
     * Constructor for the Saving account Object
     * TODO explain
     */
    public Saving( String custID, String accountID, double initBal, double balance, double interestRate, String dateOpened, boolean isCD, String CDdate, String type, String dateAccrued) {
        super(accountID, custID, balance, type, dateAccrued);
        if(dateOpened.length()>4)
            this.dateOpened = LocalDate.of(Integer.parseInt(dateOpened.substring(6)),
                    Integer.parseInt(dateOpened.substring(0,2)), Integer.parseInt(dateOpened.substring(3,5)));
        if(CDdate.length()>4)
            this.CDdate = LocalDate.of(Integer.parseInt(CDdate.substring(6)),
                    Integer.parseInt(CDdate.substring(0,2)), Integer.parseInt(CDdate.substring(3,5)));
        if(dateAccrued.length()>4)
            this.dateAccrued = LocalDate.of(Integer.parseInt(dateAccrued.substring(6)),
                    Integer.parseInt(dateAccrued.substring(0,2)), Integer.parseInt(dateAccrued.substring(3,5)));
        this.isCD = isCD;
        this.interestRate = interestRate;
        this.initBal = initBal;
    }


    /**
     * accrueInterest function
     *
     * This function does some math that adds the yearly amount of interest accrued to the current balance
     */
    public void accrueInterest(){
        if(dateAccrued==null || dateAccrued.getYear()<=LocalDate.now().getYear() && dateAccrued.getMonthValue()<LocalDate.now().getMonthValue() ||
                dateAccrued.getYear()<=LocalDate.now().getYear() && (dateAccrued.getMonthValue()==12 && LocalDate.now().getMonthValue()==1)){
            balance = balance+(balance*(interestRate/12));
            dateAccrued = LocalDate.now();
        }else{
            System.out.println("Interest already accrued");
        }

    }

    /**
     * deposit function
     *
     * This function takes the an AMOUNT of money and adds it to the total balance of the savings account.
     *
     * @param amount / double money
     */
    public void deposit(double amount){
        balance += amount;
    }


    /**
     * withdrawal function
     *
     * This function checks whether the account is a normal Savings account or a CD.
     * If it's a Savings account, it checks if the amount to be withdrawn is less than the balance, and if true,
     * subtracts the amount. If the amount is greater than the balance, it fails to withdrawal.
     * If its a CD, it checks whether the CCD has matured or not, and subtracts the amount from the balance.
     *
     * @param amount / double money
     * @return / boolean
     */
    public boolean withdrawal(double amount){
        //check amount vs balance
        if(type.equals("Savings")){
            if(amount<=balance) {
                balance -= amount;
                return true;
            }
            else if(amount>balance){
                System.out.println("not enough cash");
                return false;
            }
        }else if(type.equals("CD")){
            Period diff = Period.between(CDdate, LocalDate.now());
            int months = diff.getMonths();

            if(LocalDate.now().isBefore(CDdate)){
                //before mature date
                System.out.println("It is before the CD mature date.");
                balance -= amount;
            }else{
                //after mature date
                System.out.println("It is after the CD mature date.");
                balance -= amount;
            }
            return false;
        }
        return true;
    }


    /**
     * toString function
     *
     * This toString function prints out one of several things.
     * It can print the Savings account data of when it was opened.
     * It can print the CD account data of when the date was created.
     * It can print the account data of when to accrue.
     * It can print all the data associated with the Savings account.
     *
     * @return toString
     */
    public String toString() {
        String date;
        if(dateOpened != null){
            date = dateOpened.toString();
            date = date.substring(5,7) +"/"+ date.substring(8) +"/"+ date.substring(0,4);
        }else {
            date = null;
        }

        String cDate;
        if(CDdate != null){
            cDate = CDdate.toString();
            cDate = cDate.substring(5,7) +"/"+ cDate.substring(8) +"/"+ cDate.substring(0,4);
        }else {
            cDate = null;
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
                        initBal + ',' +
                        balance + ',' +
                        interestRate + ',' +
                        date + ',' +
                        isCD + ',' +
                        cDate + ',' +
                        type + ',' +
                        aDate;
    }


    /**
     * Setters and Getters for the Savings account object
     */
    public LocalDate getCDdate() {return CDdate;}
    public void setCDdate(LocalDate CDdate) {this.CDdate = CDdate;}
    public double getInterestRate() {return interestRate;}
    public void setInterestRate(double interestRate) {this.interestRate = interestRate;}
    public double getBalance() {return balance;}
    public void setBalance(int balance) {this.balance = balance;}
    public double getInitBal(){return initBal;}

}//end of Saving