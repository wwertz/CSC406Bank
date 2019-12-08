import java.time.LocalDate;

public class Check {

    private String checkNumber;
    private String checkingAccID;
    private LocalDate date;
    private double amount;
    private String processed;

    //constructor
    public Check(String checkNumber, String checkingAccID, String date, double amount, String processed) {
        this.checkNumber = checkNumber;
        this.checkingAccID = checkingAccID;
        this.amount = amount;
        this.processed = processed;
        if(date.length()>4)
            this.date = LocalDate.of(Integer.parseInt(date.substring(6)),
                    Integer.parseInt(date.substring(0,2)), Integer.parseInt(date.substring(3,5)));
    }

    @Override
    public String toString() {
        String sdate;
        if(date != null){
            sdate = date.toString();
            sdate = sdate.substring(5,7) +"/"+ sdate.substring(8) +"/"+ sdate.substring(0,4);
        }else {
            sdate = null;
        }

        return checkNumber + ',' +
                checkingAccID + ',' +
                sdate + ',' +
                amount + ',' +
                processed;
    }

    //getter and setter
    public String getCheckingAccID() {return checkingAccID;}
    public String getCheckNumber() {return checkNumber;}
    public LocalDate getDate() {return date;}
    public double getAmount() {return amount;}
    public String isProcessed() {return processed;}
    public void setProcessed(String processed) {this.processed = processed;}
}
