
import java.util.Date;
import java.time.*;

public class Saving extends  Account{

    char type; // s = simple and c = CD
    double interestRate;
    Date CDdate; //date CD matures


    public Saving(int accountID, int custID, double balance, char type, Date CDdate, double interestRate) {
        super(accountID, custID, balance);
        this.type = type;
        this.CDdate = CDdate;
        this.interestRate = interestRate;
    }

    //deposit
    public void deposit(double amount){
        balance += amount;
    }

    //withdrawal
    public void withdrawal(double amount){
        //check amount vs balance
        if(type=='t'){
            if(amount<=balance)
                balance -= amount;
            else if(amount>balance){
                System.out.println("not enough cash");
            }
        }else if(type=='c'){
            /*
            if(CDdate>){
                balance -= balance*.20;                //not right*********************
                balance -= amount;
            }else {
                balance -= amount;
            }
            */
        }
    }

    //getters and setters
    public char getType() {return type;}
    public void setType(char type) {this.type = type;}
    public Date getCDdate() {return CDdate;}
    public void setCDdate(Date CDdate) {this.CDdate = CDdate;}
    public double getInterestRate() {return interestRate;}
    public void setInterestRate(double interestRate) {this.interestRate = interestRate;}

}//end of Saving
