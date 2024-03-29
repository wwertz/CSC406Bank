import java.time.LocalDate;

/**
 * This is an abstract class for Accounts
 */
public abstract class Account {

    protected String accountID;
    protected String custID;
    protected double balance;
    protected LocalDate dateOpened; //get current date when created
    protected String type;
    protected String dateAccrued;

    /**
     * Constructor for the Account object
     */
    public Account(String accountID, String custID, double balance, String type, String dateAccrued) {
        this.accountID = accountID;
        this.custID = custID;
        this.balance = balance;
        this.type = type;
        this.dateAccrued = dateAccrued;
    }

    /**
     * Setters and Getters for the Account object.
     */
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
    public abstract void deposit(double amount);
    public abstract boolean withdrawal(double amount);

}//end of Account