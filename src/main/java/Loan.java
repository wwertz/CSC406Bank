import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalDate;

/**
 *
 * This class is for the Loan object.
 *
 */
public class Loan extends Account{

    protected double initialBalance;
    protected double interestRate;
    protected double amountDue;
    protected LocalDate dueDate; // due date
    protected LocalDate notifiedDate; //date bill sent
    protected LocalDate lastPaymentDate; //date of last payment made
    protected boolean flag = false;
    protected LocalDate dateAccrued;

    //constructor

    /**
     * Constructor for the Loan object
     *
     * TODO explain
     */
    public Loan(String custID, String accountID, double initialBalance, double balance, double interestRate,
                String dueDate, String notifiedDate, double amountDue, String lastPaymentDate, boolean flag, String type, String dateAccrued) {
        super(accountID, custID, balance, type, dateAccrued);
        this.initialBalance = initialBalance;
        this.interestRate = interestRate;
        this.amountDue = amountDue;
        if(dueDate.length()>4)
            this.dueDate = LocalDate.of(Integer.parseInt(dueDate.substring(6)),
                    Integer.parseInt(dueDate.substring(0,2)), Integer.parseInt(dueDate.substring(3,5)));
        if(notifiedDate.length()>4)
            this.notifiedDate = LocalDate.of(Integer.parseInt(notifiedDate.substring(6)),
                    Integer.parseInt(notifiedDate.substring(0,2)), Integer.parseInt(notifiedDate.substring(3,5)));
        if(lastPaymentDate.length()>4)
            this.lastPaymentDate = LocalDate.of(Integer.parseInt(lastPaymentDate.substring(6)),
                    Integer.parseInt(lastPaymentDate.substring(0,2)), Integer.parseInt(lastPaymentDate.substring(3,5)));
        this.flag = flag;
        if(dateAccrued.length()>4)
            this.dateAccrued = LocalDate.of(Integer.parseInt(dateAccrued.substring(6)),
                    Integer.parseInt(dateAccrued.substring(0,2)), Integer.parseInt(dateAccrued.substring(3,5)));
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
     * postBill function
     *
     * This function
     * TODO is never used ??
     */
    public void postBill() {
        if (type.equals("Long Term Loan")) {
            amountDue = balance * .025;
            dueDate = LocalDate.now().plusMonths(1);
        } else if (type.equals("Credit Card")){
            amountDue = balance;
            dueDate = LocalDate.now().plusDays(10);
        }

        else {
            amountDue = balance * .05;
            dueDate = LocalDate.now().plusMonths(1);
        }
    }


    /**
     * This function does
     * TODO explain
     *
     * @param amount / double money
     */
    @Override
    public void deposit(double amount){
        //check to see if late

        if (type.equals("Long Term Loan") || type.equals("Short Term Loan")) {  //loans
            if(LocalDate.now().isAfter(dueDate)){               //late
                flag = true;
                //balance += balance*interestRate/100;
                balance += 75.00;
                balance -= amount;
                lastPaymentDate = LocalDate.now();
            }else{                                             //not late
                flag = false;
                //balance += balance*interestRate/100;
                balance -= amount;
                lastPaymentDate = LocalDate.now();
            }
        }else{                                                  //credit cards
            LocalDate beforeCharge = LocalDate.of(notifiedDate.getYear(), notifiedDate.getMonth(), 10);
            if(LocalDate.now().isAfter(dueDate)){               //late
                flag = true;
                //balance += balance*interestRate/100;
                balance += 75.00;
                balance -= amount;
                lastPaymentDate = LocalDate.now();
            }else if(LocalDate.now().isBefore(beforeCharge)){  //no finance charge
                flag = false;
                balance -= amount;
                lastPaymentDate = LocalDate.now();
            }else{                                             //on time with finance charge
                flag = true;
                //balance += balance*interestRate/100;
                balance -= amount;
                lastPaymentDate = LocalDate.now();
            }
        }
    }


    /**
     * withdrawal function
     *
     * This function
     *  TODO
     *
     * @param amount / double money
     * @return / boolean
     */
    @Override
    public boolean withdrawal(double amount) {
        boolean result = true;
        if (type.equals("Credit Card")){
            if (balance + amount <= initialBalance) {
                balance += amount;
                result = true;
            }else{ result = false;}
        }
        return result;
    }

    /**
     * toString function
     *
     * TODO
     *
     *
     * @return toString
     */
    @Override
    public String toString() {
        //convert dates back into strings formatted in the database
        String ddate;
        if(dueDate != null){
            ddate = dueDate.toString();
            ddate = ddate.substring(5,7) +"/"+ ddate.substring(8) +"/"+ ddate.substring(0,4);
        }else {
            ddate = null;
        }

        String ndate;
        if(notifiedDate != null){
            ndate = notifiedDate.toString();
            ndate = ndate.substring(5,7) +"/"+ ndate.substring(8) +"/"+ ndate.substring(0,4);
        }else {
            ndate = null;
        }

        String ldate;
        if(lastPaymentDate != null){
            ldate = lastPaymentDate.toString();
            ldate = ldate.substring(5,7) +"/"+ ldate.substring(8) +"/"+ ldate.substring(0,4);
        }else {
            ldate = null;
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
                initialBalance + ',' +
                balance + ',' +
                interestRate + ',' +
                ddate + ',' +
                ndate + ',' +
                amountDue + ',' +
                ldate + ',' +
                flag + ',' +
                type + ',' +
                aDate;
    }

    /**
     * Setters and Getters
     */
    public double getInitialBalance() {return initialBalance;}
    public double getInterestRate() {return interestRate;}
    public void setInterestRate(double interestRate) {this.interestRate = interestRate;}
    public double getAmountDue() {return amountDue;}
    public void setAmountDue(double amountDue) {this.amountDue = amountDue;}
    public LocalDate getDueDate() {return dueDate;}
    public void setDueDate(LocalDate dueDate) {this.dueDate = dueDate;}
    public LocalDate getNotifiedDate() {return notifiedDate;}
    public void setNotifiedDate(LocalDate notifiedDate) {this.notifiedDate = notifiedDate;}
    public LocalDate getLastPaymentDate() {return lastPaymentDate;}
    public void setLastPaymentDate(LocalDate lastPaymentDate) {this.lastPaymentDate = lastPaymentDate;}
    public boolean isFlag() {return flag;}
    public void setFlag(boolean flag) {this.flag = flag;}


}//end of loan