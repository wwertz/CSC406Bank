import java.time.LocalDate;

public class Loan extends Account{
    //l=long, s=short and c=credit card
    char type;
    double initialBalance;
    double balance;
    double interestRate;
    double amountDue;
    LocalDate dueDate; // due date
    LocalDate notifiedDate; //date bill sent
    LocalDate lastPaymentDate; //date of last payment made
    boolean flag = false;

    //constructor
    public Loan(int accountID, int custID, double balance, double initialBalance, double interestRate, double amountDue,
                LocalDate dueDate, LocalDate notifiedDate, LocalDate lastPaymentDate, boolean flag, char type) {
        super(accountID, custID, balance);
        this.initialBalance = initialBalance;
        this.balance = balance;
        this.interestRate = interestRate;
        this.amountDue = amountDue;
        this.dueDate = dueDate;
        this.notifiedDate = notifiedDate;
        this.lastPaymentDate = lastPaymentDate;
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
            }else if(LocalDate.now().isBefore(beforeCharge)){  //no finance charge
                flag = false;
                balance -= amount;
                lastPaymentDate = LocalDate.now();
            }else{                                             //on time with finance charge
                flag = true;
                balance += balance*interestRate/100;
                balance -= amount;
                lastPaymentDate = LocalDate.now();
            }
        }
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
    public double getBalance() {return balance;}
    public void setBalance(int balance) {this.balance = balance;}
}//end of loan
