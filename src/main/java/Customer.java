import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.zip.GZIPInputStream;


/**
 *      Dates need to be changed from String to localdate
 *
 *      I question if things dealing with money should be BigDecimal or ints. Not long or double so we dont have
 *      floating point errors
 *
**/

class Customer {

    private String ssn,firstName, lastname, address, city, state, zipcode, atmCard;


    Customer(String ssn, String firstName, String lastName, String address, String city, String state,
                    String zipcode, String atmCard){

        this.ssn = ssn;
        this.firstName = firstName;
        this.lastname = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.atmCard = atmCard;

    }

    public static class SavingsAccounts implements Runnable {
        Customer customer;
        int savingsAccID;
        double acctBalance, cIntRate;
        String dateOpened, cdCloseDate;
        Boolean isACD;


        SavingsAccounts(Customer customer, int savingsAccID, double acctBalance, double cIntRate, String dateOpened,
                         Boolean isACD, String cdCloseDate) {
            this.customer = customer;
            this.savingsAccID = savingsAccID;
            this.acctBalance = acctBalance;
            this.cIntRate = cIntRate;
            this.dateOpened = dateOpened;
            this.isACD = isACD;
            this.cdCloseDate = cdCloseDate;

        }

        @Override
        public void run() {

        }
    }


    public static class CheckingAccounts implements Runnable {
        Customer customer;
        int checkingAccID, backupSavings, overdrafts;
        double acctBalance;
        String dateOpened;
        boolean isGold;

        CheckingAccounts(Customer customer, int checkingAccID, double acctBalance, String dateOpened, boolean isGold,
                         int backupSavings, int overdrafts) {
            this.customer = customer;
            this.checkingAccID = checkingAccID;
            this.acctBalance = acctBalance;
            this.dateOpened = dateOpened;
            this.isGold = isGold;
            this.backupSavings = backupSavings;
            this.overdrafts = overdrafts;

        }


        @Override
        public void run() {

        }
    }


    private void withdrawal(double acctbalance){}

    private void deposit(double acctbalance){}






}
