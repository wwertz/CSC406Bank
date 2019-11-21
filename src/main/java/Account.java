import java.time.LocalDate;

//subclasses Loan, Checking and Saving
public abstract class Account {

    protected String accountID;
    protected String custID;
    protected double balance;
    protected LocalDate dateOpened; //get current date when created
    protected String type;

    //constructor
    public Account(String accountID, String custID, double balance, String type) {
        this.accountID = accountID;
        this.custID = custID;
        this.balance = balance;
        this.type = type;
    }

    //getter and setters
    public String getAccountID() {return accountID;}
    public void setAccountID(String accountID) {this.accountID = accountID;}
    public String getCustID() {return custID;}
    public void setCustID(String custID) {this.custID = custID;}
    public double getBalance() {return balance;}
    public void setBalance(double balance) {this.balance = balance;}
    public LocalDate getDateOpened() {return dateOpened; }
    public void setDateOpened(LocalDate dateOpened) {this.dateOpened = dateOpened;}
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}
}//end of Account