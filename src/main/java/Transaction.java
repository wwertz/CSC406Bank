import java.time.LocalDate;

public class Transaction {

    protected String account;
    protected double amount;
    protected String date;
    protected String description;


    //constructor
    public Transaction(String account, double amount, String date, String description) {
        this.account = account;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    @Override
    public String toString() {
        return
                account + ',' +
                amount + ',' +
                date + ',' +
                description;
    }

    //getters
    public String getAccount() {return account;}
    public double getAmount() {return amount;}
    public String getDate() {return date;}
    public String getDescription() {return description;}
}//end of class Transaction
