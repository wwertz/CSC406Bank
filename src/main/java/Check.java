import java.time.LocalDate;

/**
 * This is the class for a Check object.
 */
public class Check {

    private String checkNumber;
    private String checkingAccID;
    private String date;
    private double amount;
    private String processed;

    /**
     * Constructor for the Check object.
     */
    public Check(String checkNumber, String checkingAccID, String date, double amount, String processed) {
        this.checkNumber = checkNumber;
        this.checkingAccID = checkingAccID;
        this.amount = amount;
        this.processed = processed;
        this.date = date;
    }

    /**
     * @return toString
     */
    @Override
    public String toString() {
        return checkNumber + ',' +
                checkingAccID + ',' +
                date + ',' +
                amount + ',' +
                processed;
    }

    /**
     * Getters and Setter for the Check object.
     */
    public String getCheckingAccID() {return checkingAccID;}
    public String getCheckNumber() {return checkNumber;}
    public String getDate() {return date;}
    public double getAmount() {return amount;}
    public String isProcessed() {return processed;}
    public void setProcessed(String processed) {this.processed = processed;}
}
