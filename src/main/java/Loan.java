import java.time.LocalDate;
import java.util.Date;

//********************************still needs interest****************************************8

public class Loan {
    double initialBalance;
    double currentBalance;
    double interestRate;
    double amountDue;
    LocalDate dueDate; // due date
    LocalDate notifiedDate; //date bill sent
    LocalDate lastPaymentDate; //date of last payment made
    boolean flag = false;

    //constructor
    public Loan(double initialBalance, double currentBalance, double interestRate, double amountDue, LocalDate dueDate, LocalDate notifiedDate, LocalDate lastPaymentDate) {
        this.initialBalance = initialBalance;
        this.currentBalance = currentBalance;
        this.interestRate = interestRate;
        this.amountDue = amountDue;
        this.dueDate = dueDate;
        this.notifiedDate = notifiedDate;
        this.lastPaymentDate = lastPaymentDate;
    }

    public void payment(double amount){
        //check to see if late
        if(LocalDate.now().isAfter(dueDate)){               //late
            flag = true;
            currentBalance -= amount + 75.00;                      //add late fee
            lastPaymentDate = LocalDate.now();
        }else{                                              //not late
            flag = false;
            currentBalance -= amount;
            lastPaymentDate = LocalDate.now();
        }
    }

    //getter and setter
    public double getInitialBalance() {return initialBalance;}
    public void setInitialBalance(double initialBalance) {this.initialBalance = initialBalance;}
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
