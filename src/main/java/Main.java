// CSC 406 Banking Project

// Jake Blessing
// Benjamin Oliphant
// Sam Poirer
// Wayne Wertz

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static boolean manager = false;
    public static void main(String[] args) {
        ArrayList<Check> checks = new ArrayList<Check>();
        readChecks(checks);
        LoginScreen initial = new LoginScreen();

        Loan a1 = new Loan("1000", "423148894", 3579, 5000, .15, 95.83, LocalDate.of(2019, 11, 27), LocalDate.of(2019, 10, 1), LocalDate.of(2019, 8, 20), false, 's');

        System.out.println(a1.getLastPaymentDate());
        a1.payment(95.83);
        System.out.println(a1.getLastPaymentDate());
        System.out.println(a1.getBalance());




//Customer(String ssn, String firstName, String lastName, String address, String city, String state, String zipcode, String atmCard)
        Customer RonaldJones = new Customer("423-45-2345", "Ronald", "Jones", "114 North 4th",
                "Clarkesdale", "MO", "64493", "2.89965E+15");


    }//end of main method
    public static void readChecks(ArrayList<Check> list){
        File file = new File("src/Checks.txt");
        try {
            Scanner input = new Scanner(file);
            while(input.hasNextLine()){
                String[] field = input.nextLine().split(",");
                list.add(new Check(field[0],field[1],field[2],Double.parseDouble(field[3]),Boolean.valueOf(field[4])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//public Check(String checkNumber, String checkingAccID, String date, double amount, boolean processed) {

    }

}//end of Main Class
