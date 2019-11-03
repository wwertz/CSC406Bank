public class Checking extends  Account{

    //********************************still needs interest****************************************8

    char type; //t = TMB and g = Gold/Diamond
    boolean hasBackup;
    int backupID; //saving account ID

    //constructor
    public Checking(int accountID, int custID, double balance, boolean hasBackup, int backupID) {
        super(accountID, custID, balance);
        this.hasBackup = hasBackup;
        this.backupID = backupID;
        checkType();
    }

    //deposit
    public void deposit(double amount){
        balance += amount;
        checkType();

        //check for fees
        if(type=='t')
            balance -= .50;
    }

    //withdrawal
    public void withdrawal(double amount){
        //check amount vs balance
        if(amount>=balance){
            System.out.println("not enough in balance, checking for backup");
            if(hasBackup==true){
                //****************************needs update after savings finished*******************************
            }
        }else if(amount<balance){
            balance-= amount;
        }

        //check for fees
        if(type=='t')
            balance -= .50;
    }

    public char checkType(){
        if(balance>=1000){
            return type = 'g';
        }else{
            return type = 't';
        }
    }

    public char getType() {return type;}
    public void setType(char type) {this.type = type;}
    public boolean isHasBackup() {return hasBackup;}
    public void setHasBackup(boolean hasBackup) {this.hasBackup = hasBackup;}
}//end of Checking
