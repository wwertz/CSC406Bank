import java.time.*;

/**
 * Dates need to be changed from String to localdate
 *
 * I question if things dealing with money should be BigDecimal or ints. Not long or double so we dont have
 * floating point errors
 *
 **/

class Customer {

    private String ssn, firstName, lastname, address, city, state, zipcode, atmCard;

    char type; // s = simple and c = CD
    double interestRate;
    LocalDate CDdate;
    LocalDate dateOpened;

    Customer(String ssn, String firstName, String lastName, String address, String city, String state,
             String zipcode, String atmCard) {

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
        public void run() { }


        private void withdrawalSaving(double amount) {
//            //check amount vs balance
//            if (type == 't') {
//                if (amount <= acctBalance)
//                    acctBalance -= amount;
//                else if (amount > acctBalance) {
//                    System.out.println("not enough cash");
//                }
//            } else if (type == 'c') {
//                //is CD and before mature date
//                if (LocalDate.now().isBefore(CDdate)) {
//                    acctBalance -= amount;
//                } else {
//                    acctBalance -= amount;
//                }
//            }
        }

        private void depositSavings(double amount) {
            acctBalance += amount;
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
        public void run() { }


        private void withdrawalChecking(double amount) {
//            //check amount vs balance
//            if (amount >= acctBalance) {
//                System.out.println("not enough in balance, checking for backup");
//                if (hasBackup == true) {
//                    //****************************needs update after savings finished*******************************
//                }
//            } else if (amount < acctBalance) {
//                acctBalance -= amount;
//            }
//
//            //check for fees
//            if (type == 't')
//                acctBalance -= .50;
        }

        private void depositChecking(double amount) {
//            acctBalance += amount;
//            checkType();
//
//            //check for fees
//            if (type == 't')
//                acctBalance -= .50;
        }

    }

}
