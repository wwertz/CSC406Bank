//Customer screen

import java.time.LocalDate;
import java.util.ArrayList;

public class Loan extends Account{
    //l=long, s=short and c=credit card
    protected char type;
    protected double initialBalance;
    protected double interestRate;
    protected double amountDue;
    protected LocalDate dueDate; // due date
    protected LocalDate notifiedDate; //date bill sent
    protected LocalDate lastPaymentDate; //date of last payment made
    protected boolean flag = false;
    protected ArrayList<String> creditHistory;

    //constructor
    public Loan(String custID, String accountID, double initialBalance, double balance, double interestRate,
                String dueDate, String notifiedDate, double amountDue, String lastPaymentDate, boolean flag, char type) {
        super(accountID, custID, balance);
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
        this.type = type;
    }

    //make a payment
    public void payment(double amount){
        //check to see if late

        if (type=='l' || type=='s') {                           //loans
            if(LocalDate.now().isAfter(dueDate)){               //late
                flag = true;
                balance += balance*interestRate/100;
                balance += 75.00;
                balance -= amount;
                lastPaymentDate = LocalDate.now();
            }else{                                             //not late
                flag = false;
                balance += balance*interestRate/100;
                balance -= amount;
                lastPaymentDate = LocalDate.now();
            }
        }else{                                                  //credit cards
            LocalDate beforeCharge = LocalDate.of(notifiedDate.getYear(), notifiedDate.getMonth(), 10);
            if(LocalDate.now().isAfter(dueDate)){               //late
                flag = true;
                balance += balance*interestRate/100;
                balance += 75.00;
                balance -= amount;
                lastPaymentDate = LocalDate.now();
                creditHistory.add("Payment of " +Double.toString(amount) +" on "+ LocalDate.now()+ "***late***");
            }else if(LocalDate.now().isBefore(beforeCharge)){  //no finance charge
                flag = false;
                balance -= amount;
                lastPaymentDate = LocalDate.now();
                creditHistory.add("Payment of " +Double.toString(amount) +" on "+ LocalDate.now());
            }else{                                             //on time with finance charge
                flag = true;
                balance += balance*interestRate/100;
                balance -= amount;
                lastPaymentDate = LocalDate.now();
                creditHistory.add("Payment of " +Double.toString(amount) +" on "+ LocalDate.now());
            }
        }
    }

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
                type;
    }

    //getter and setter
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