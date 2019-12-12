import java.time.LocalDate;

/**
 * This class is for the Transaction object
 */
public class Transaction {

    protected String account;
    protected double amount;
    protected String date;
    protected String description;


    /**
     * Constructor for the Transaction object.
     */
    public Transaction(String account, double amount, String date, String description) {
        this.account = account;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    /**
     * @return toString
     */
    @Override
    public String toString() {
        return
                account + ',' +
                amount + ',' +
                date + ',' +
                description;
    }

    /**
     * Setters and Getters for the Transaction object.
     */
    public String getAccount() {return account;}
    public double getAmount() {return amount;}
    public String getDate() {return date;}
    public String getDescription() {return description;}
}//end of class Transaction
