import java.time.LocalDate;

public class Transaction {

    protected String account;
    protected double amount;
    protected LocalDate date;
    protected String description;


    //constructor
    public Transaction(String account, double amount, String date, String description) {
        this.account = account;
        this.amount = amount;
        if(date.length()>4)
            this.date = LocalDate.of(Integer.parseInt(date.substring(6)),
                    Integer.parseInt(date.substring(0,2)), Integer.parseInt(date.substring(3,5)));
        this.description = description;
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

        return
                account + ',' +
                amount + ',' +
                sdate + ',' +
                description;
    }

    //getters
    public String getAccount() {return account;}
    public double getAmount() {return amount;}
    public LocalDate getDate() {return date;}
    public String getDescription() {return description;}
}//end of class Transaction
