import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.*;

//********************************still needs interest****************************************8

public class Saving extends  Account{

    protected boolean isCD;
    protected double interestRate;
    protected LocalDate CDdate; //date CD matures
    protected LocalDate dateOpened;
    protected LocalDate dateAccrued;
    protected double initBal;

    public Saving( String custID, String accountID, double initBal, double balance, double interestRate, String dateOpened, boolean isCD, String CDdate, String type, String dateAccrued) {
        super(accountID, custID, balance, type, dateAccrued);
        if(dateOpened.length()>4)
            this.dateOpened = LocalDate.of(Integer.parseInt(dateOpened.substring(6)),
                    Integer.parseInt(dateOpened.substring(0,2)), Integer.parseInt(dateOpened.substring(3,5)));
        if(CDdate.length()>4)
            this.CDdate = LocalDate.of(Integer.parseInt(CDdate.substring(6)),
                    Integer.parseInt(CDdate.substring(0,2)), Integer.parseInt(CDdate.substring(3,5)));
        if(dateAccrued.length()>4)
            this.dateAccrued = LocalDate.of(Integer.parseInt(dateAccrued.substring(6)),
                    Integer.parseInt(dateAccrued.substring(0,2)), Integer.parseInt(dateAccrued.substring(3,5)));
        this.isCD = isCD;
        this.interestRate = interestRate;
        this.initBal = initBal;
    }

    public void accrueInterest(){
        balance = balance+(balance*(interestRate/12));
    }

    //deposit
    public void deposit(double amount){
        balance += amount;
    }

    //withdrawal
    public boolean withdrawal(double amount){
        //check amount vs balance
        if(type.equals("Savings")){
            if(amount<=balance) {
                balance -= amount;
                return true;
            }
            else if(amount>balance){
                System.out.println("not enough cash");
                return false;
            }
        }else if(type.equals("CD")){
            //is CD and before mature date
            return false;
        }
        return true;
    }

    public String toString() {
        String date;
        if(dateOpened != null){
            date = dateOpened.toString();
            date = date.substring(5,7) +"/"+ date.substring(8) +"/"+ date.substring(0,4);
        }else {
            date = null;
        }

        String cDate;
        if(CDdate != null){
            cDate = CDdate.toString();
            cDate = cDate.substring(5,7) +"/"+ cDate.substring(8) +"/"+ cDate.substring(0,4);
        }else {
            cDate = null;
        }

        String aDate;
        if(dateAccrued != null){
            aDate = dateAccrued.toString();
            aDate = aDate.substring(5,7) +"/"+ aDate.substring(8) +"/"+ aDate.substring(0,4);
        }else {
            aDate = null;
        }

        return
                custID + ',' +
                        accountID + ',' +
                        initBal + ',' +
                        balance + ',' +
                        interestRate + ',' +
                        date + ',' +
                        isCD + ',' +
                        cDate + ',' +
                        type + ',' +
                        aDate;
    }

    //getters and setters
    public LocalDate getCDdate() {return CDdate;}
    public void setCDdate(LocalDate CDdate) {this.CDdate = CDdate;}
    public double getInterestRate() {return interestRate;}
    public void setInterestRate(double interestRate) {this.interestRate = interestRate;}
    public double getBalance() {return balance;}
    public void setBalance(int balance) {this.balance = balance;}
    public double getInitBal(){return initBal;}

}//end of Saving