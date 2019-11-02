import java.time.LocalDate;
import java.util.Date;

public class Loan {
    double initialBalance;
    double interestRate;
    double amountDue;
    Date dueDate;
    Date notifiedDate;
    Date lastPaymentDate;
    Date cDate;
    boolean flag = false;

    //constructor


    public Loan(double initialBalance, double interestRate, double amountDue, Date dueDate, Date notifiedDate, Date lastPaymentDate) {
        this.initialBalance = initialBalance;
        this.interestRate = interestRate;
        this.amountDue = amountDue;
        this.dueDate = dueDate;
        this.notifiedDate = notifiedDate;
        this.lastPaymentDate = lastPaymentDate;
    }

    public void payment(double amount){

    }

    //getter and setter
    public double getInitialBalance() {return initialBalance;}
    public void setInitialBalance(double initialBalance) {this.initialBalance = initialBalance;}
    public double getInterestRate() {return interestRate;}
    public void setInterestRate(double interestRate) {this.interestRate = interestRate;}
    public double getAmountDue() {return amountDue;}
    public void setAmountDue(double amountDue) {this.amountDue = amountDue;}
    public Date getDueDate() {return dueDate;}
    public void setDueDate(Date dueDate) {this.dueDate = dueDate;}
    public Date getNotifiedDate() {return notifiedDate;}
    public void setNotifiedDate(Date notifiedDate) {this.notifiedDate = notifiedDate;}
    public Date getLastPaymentDate() {return lastPaymentDate;}
    public void setLastPaymentDate(Date lastPaymentDate) {this.lastPaymentDate = lastPaymentDate;}
    public boolean isFlag() {return flag;}
    public void setFlag(boolean flag) {this.flag = flag;}
}//end of loan
