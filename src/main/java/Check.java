import java.time.LocalDate;

public class Check {

    private String checkNumber;
    private String checkingAccID;
    private String date;
    private double amount;
    private boolean processed;

    //constructor
    public Check(String checkNumber, String checkingAccID, String date, double amount, boolean processed) {
        this.checkNumber = checkNumber;
        this.checkingAccID = checkingAccID;
        this.date = date;
        this.amount = amount;
        this.processed = processed;
    }

    @Override
    public String toString() {
        return "Check{" +
                "checkNumber='" + checkNumber + '\'' +
                ", checkingAccID='" + checkingAccID + '\'' +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                ", processed=" + processed +
                '}';
    }

    //getter and setter
    public String getCheckNumber() {return checkNumber;}
    public String getDate() {return date;}
    public double getAmount() {return amount;}
    public boolean isProcessed() {return processed;}
    public void setProcessed(boolean processed) {this.processed = processed;}
}
