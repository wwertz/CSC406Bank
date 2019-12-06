//Customer screen

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

public class Loan extends Account{

    protected double initialBalance;
    protected double interestRate;
    protected double amountDue;
    protected LocalDate dueDate; // due date
    protected LocalDate notifiedDate; //date bill sent
    protected LocalDate lastPaymentDate; //date of last payment made
    protected boolean flag = false;
    protected ArrayList<String> creditHistory;

    //constructor
    public Loan(String custID, String accountID, double initialBalance, double balance, double interestRate,
                String dueDate, String notifiedDate, double amountDue, String lastPaymentDate, boolean flag, String type) {
        super(accountID, custID, balance, type);
        this.initialBalance = initialBalance;
        this.interestRate = interestRate;
        this.amountDue = amountDue;
        if(dueDate.length()>4)
            this.dueDate = LocalDate.of(Integer.parseInt(dueDate.substring(6)),
                    Integer.parseInt(dueDate.substring(0,2)), Integer.parseInt(dueDate.substring(3,5)));
        if(notifiedDate.length()>4)
            this.notifiedDate = LocalDate.of(Integer.parseInt(notifiedDate.substring(6)),
                    Integer.parseInt(notifiedDate.substring(0,2)), Integer.parseInt(notifiedDate.substring(3,5)));
        if(lastPaymentDate.length()>4)
            this.lastPaymentDate = LocalDate.of(Integer.parseInt(lastPaymentDate.substring(6)),
                    Integer.parseInt(lastPaymentDate.substring(0,2)), Integer.parseInt(lastPaymentDate.substring(3,5)));
        this.flag = flag;
    }

    public void accrueInterest(){
        balance = balance+(balance*(interestRate/12));
    }

    public void postBill() {
        if (type.equals("Long Term Loan")) {
            amountDue = balance * .025;
            dueDate = LocalDate.now().plusMonths(1);
        } else {
            amountDue = balance * .05;
            dueDate = LocalDate.now().plusMonths(1);
        }
    }
    //make a payment
    @Override
    public void deposit(double amount){
        //check to see if late

        if (type.equals("Long Term Loan") || type.equals("Short Term Loan")) {  //loans
            if(LocalDate.now().isAfter(dueDate)){               //late
                flag = true;
                //balance += balance*interestRate/100;
                balance += 75.00;
                balance -= amount;
                lastPaymentDate = LocalDate.now();
            }else{                                             //not late
                flag = false;
                //balance += balance*interestRate/100;
                balance -= amount;
                lastPaymentDate = LocalDate.now();
            }
        }else{                                                  //credit cards
            LocalDate beforeCharge = LocalDate.of(notifiedDate.getYear(), notifiedDate.getMonth(), 10);
            if(LocalDate.now().isAfter(dueDate)){               //late
                flag = true;
                //balance += balance*interestRate/100;
                balance += 75.00;
                balance -= amount;
                lastPaymentDate = LocalDate.now();
                creditHistory.add("Payment of " +Double.toString(amount) +" on "+ LocalDate.now()+ "***late***");
            }else if(LocalDate.now().isBefore(beforeCharge)){  //no finance charge
                flag = false;
                balance -= amount;
                lastPaymentDate = LocalDate.now();
                creditHistory.add("Payment of " +Double.toString(amount) +" on "+ LocalDate.now());
            }else{                                             //on time with finance charge
                flag = true;
                //balance += balance*interestRate/100;
                balance -= amount;
                lastPaymentDate = LocalDate.now();
                creditHistory.add("Payment of " +Double.toString(amount) +" on "+ LocalDate.now());
            }
        }
    }
    @Override
    public void withdrawal(double amount) {
        if (type.equals("Credit Card")){
            if (amountDue + amount < balance) {
                amountDue += amount;
            }
        }

    }
    @Override
    public String toString() {
        //convert dates back into strings formatted in the database
        String ddate;
        if(dueDate != null){
            ddate = dueDate.toString();
            ddate = ddate.substring(5,7) +"/"+ ddate.substring(8) +"/"+ ddate.substring(0,4);
        }else {
            ddate = null;
        }

        String ndate;
        if(notifiedDate != null){
            ndate = notifiedDate.toString();
            ndate = ndate.substring(5,7) +"/"+ ndate.substring(8) +"/"+ ndate.substring(0,4);
        }else {
            ndate = null;
        }

        String ldate;
        if(lastPaymentDate != null){
            ldate = lastPaymentDate.toString();
            ldate = ldate.substring(5,7) +"/"+ ldate.substring(8) +"/"+ ldate.substring(0,4);
        }else {
            ldate = null;
        }

        return
                custID + ',' +
                accountID + ',' +
                initialBalance + ',' +
                balance + ',' +
                interestRate + ',' +
                ddate + ',' +
                ndate + ',' +
                amountDue + ',' +
                ldate + ',' +
                flag + ',' +
                type;
    }

    public void JSONLoanToTxt(String custID, String accountID, double initialBalance, double balance, double interestRate,
                              String dueDate, String notifiedDate, double amountDue, String lastPaymentDate, boolean flag, char type){
        JSONObject obj = new JSONObject();
        obj.put("CustomerID", custID);
        obj.put("AccountID", accountID);
        obj.put("InitialBalance", initialBalance);
        obj.put("Balance", balance);
        obj.put("interestRate", interestRate);
        obj.put("dueDate", dueDate);
        obj.put("notifiedDate", notifiedDate);
        obj.put("amountDue", amountDue);
        obj.put("lastPaymentDate", lastPaymentDate);
        obj.put("flag", flag);
        obj.put("type", type);

        try (PrintWriter file = new PrintWriter("/Loan/"+accountID +".txt")) {
            file.write(obj.toJSONString());
            System.out.println("Successfully wrote loan "+accountID+" to file");
            System.out.println("\nJSON Object: " + obj);
        } catch (FileNotFoundException e) {
            System.out.println("Unsuccessful loan Account Write for "+accountID);
        }
    }

    @SuppressWarnings("unchecked")
    public void JSONTxtToLoan(int AID) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(
                    "/Loan/"+AID +".txt"));

            JSONObject LOANjsonObject = (JSONObject) obj;

            int custID = (int) LOANjsonObject.get("CustomerID");
            int accountID = (int) LOANjsonObject.get("AccountID");
            double initialBalance = (double) LOANjsonObject.get("InitialBalance");
            double balance = (double) LOANjsonObject.get("Balance");
            double interestRate = (double) LOANjsonObject.get("interestRate");
            String dueDate = (String) LOANjsonObject.get("dueDate");
            String notifiedDate = (String) LOANjsonObject.get("notifiedDate");
            double amountDue = (double) LOANjsonObject.get("amountDue");
            String lastPaymentDue = (String) LOANjsonObject.get("lastPaymentDue");
            boolean flag = (boolean) LOANjsonObject.get("flag");
            char type = (char) LOANjsonObject.get("type");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //getter and setter
    public double getInitialBalance() {return initialBalance;}
    public double getInterestRate() {return interestRate;}
    public void setInterestRate(double interestRate) {this.interestRate = interestRate;}
    public double getAmountDue() {return amountDue;}
    public void setAmountDue(double amountDue) {this.amountDue = amountDue;}
    public LocalDate getDueDate() {return dueDate;}
    public void setDueDate(LocalDate dueDate) {this.dueDate = dueDate;}
    public LocalDate getNotifiedDate() {return notifiedDate;}
    public void setNotifiedDate(LocalDate notifiedDate) {this.notifiedDate = notifiedDate;}
    public LocalDate getLastPaymentDate() {return lastPaymentDate;}
    public void setLastPaymentDate(LocalDate lastPaymentDate) {this.lastPaymentDate = lastPaymentDate;}
    public boolean isFlag() {return flag;}
    public void setFlag(boolean flag) {this.flag = flag;}


}//end of loan