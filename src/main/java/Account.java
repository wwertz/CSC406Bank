import java.util.Date;

//subclasses Loan, Checking and Saving
public class Account {

    int accountID;
    int custID;
    double balance;
    Date dateOpened;

    //constructor
    public Account(int accountID, int custID, double balance) {
        this.accountID = accountID;
        this.custID = custID;
        this.balance = balance;
    }

    //getter and setters
    public int getAccountID() {return accountID;}
    public void setAccountID(int accountID) {this.accountID = accountID;}
    public int getCustID() {return custID;}
    public void setCustID(int custID) {this.custID = custID;}
    public double getBalance() {return balance;}
    public void setBalance(int balance) {this.balance = balance;}
    public Date getDateOpened() {return dateOpened; }
    public void setDateOpened(Date dateOpened) {this.dateOpened = dateOpened;}
}//end of Account
