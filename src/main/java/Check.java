import java.time.LocalDate;

public class Check {

    private String checkNumber;
    private String checkingAccID;
    private String date;
    private double amount;
    private String processed;

    //constructor
    public Check(String checkNumber, String checkingAccID, String date, double amount, String processed) {
        this.checkNumber = checkNumber;
        this.checkingAccID = checkingAccID;
        this.date = date;
        this.amount = amount;
        this.processed = processed;
    }

    @Override
    public String toString() {
        return checkNumber + ',' +
                checkingAccID + ',' +
                date + ',' +
                amount + ',' +
                processed;
    }

    //getter and setter
    public String getCheckingAccID() {return checkingAccID;}
    public String getCheckNumber() {return checkNumber;}
    public String getDate() {return date;}
    public double getAmount() {return amount;}
    public String isProcessed() {return processed;}
    public void setProcessed(String processed) {this.processed = processed;}
}
