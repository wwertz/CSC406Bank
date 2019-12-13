 /**
  * CSC 406 Banking Project
  * Group 2
  * Team Leader: Jake Blessing
  * Wayne Wertz
  * Benjamin Oliphant
  * Sam Poirer
 **/


import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import Users.*;

/**
 * MAIN driver class for the Banking Project
 * Executing main runs the GUI
 *
 * TODO
 *
 **/

public class Main {

    /**
     * Declares the ArrayLists that will store important banking information in memory while the GUI runs.
     **/
    public static ArrayList<Customer> customers = new ArrayList<>();
    public static ArrayList<Check> checks = new ArrayList<>();
    public static ArrayList<Checking> checkings = new ArrayList<>();
    public static ArrayList<Saving> savings = new ArrayList<>();
    public static ArrayList<Loan> loans = new ArrayList<>();
    public static ArrayList<Transaction> transactions = new ArrayList<>();

    // declares and sets defaults for interest rate before read from database
    public static double savingsInterest = 0.1;
    public static double stlInterest = 0.1;
    public static double ltlInterest = 0.1;
    public static double ccInterest = 0.1;
    public static double cdInterest = 0.1;
    public static boolean manager = false;


    public static void main(String[] args) throws IOException {
        /**
         * Terminal debugging for today's date.
         **/
        System.out.println(LocalDate.now().toString());
        /**
         * Reads the important banking data into the live memory of the ArrayLists.
         **/
        readCustomer(customers);
        readChecks(checks);
        readChecking(checkings);
        readSaving(savings);
        readLoan(loans);
        readTransaction(transactions);
        readGlobal();

        /**
         * Launch the users GUI for running the Banking Project.
         **/
        LoginScreen initial = new LoginScreen();

        //testing

        /**
         * Waits for the system to shutdown
         * Code will execute when the GUI is closed.
         * After GUI is closed, the system writes the live memory of the ArrayLists to the database storage
         **/
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){
                try {
                    //write data to databases
                    updateDatabase();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }//end of main method


    /**
     * updateDatabase function
     *
     * The function that is responsible for calling writeData function for all of the ArrayLists.
     * See writeData function. The writeData call takes the associated ArrayList and the Database storage container.
     * See writeGlobal function. The writeGlobal call takes the Global Database storage container.
     *
     * @throws IOException /
     **/
    public static void updateDatabase() throws IOException {
        writeData(customers, "Customers.txt");
        writeData(checks, "Checks.txt");
        writeData(checkings, "Checkings.txt");
        writeData(savings, "Savings.txt");
        writeData(loans, "Loans.txt");
        writeData(transactions, "Transactions.txt");
        writeGlobal("Global.txt");
    }//end of database


    /**
     * readCustomer function
     *
     * The function that is responsible for taking the data from the Database for Customer data, and adds it into
     * the live memory of the "Customer" ARRAYLIST.
     *
     * @param list / ArrayList
     * @throws FileNotFoundException /
     **/
    public static void readCustomer(ArrayList<Customer> list) throws FileNotFoundException {
        File file = new File("src/Customers.txt");
        Scanner input = new Scanner(file);
        while(input.hasNextLine()){
            String[] field = input.nextLine().split(",");
            list.add(new Customer(field[0],field[1],field[2],field[3],field[4],field[5],field[6],field[7],field[8]));
        }
        System.out.println("read customer");
    }//end of readCustomer


    /**
     * readChecks function
     *
     * The function that is responsible for taking the data from the Database for Checks data, and adds it into
     * the live memory of the "Check" ARRAYLIST.
     *
     * @param list / ArrayList
     * @throws FileNotFoundException /
     **/
    public static void readChecks(ArrayList<Check> list) throws FileNotFoundException {
        File file = new File("src/Checks.txt");
            Scanner input = new Scanner(file);
            while(input.hasNextLine()){
                String[] field = input.nextLine().split(",");
                list.add(new Check(field[0],field[1],field[2],Double.parseDouble(field[3]), field[4]));
            }
        System.out.println("read checks");
    }//end of readChecks


    /**
     * readChecking function
     *
     * The function that is responsible for taking the data from the Database for Checking account data, and adds it
     * into the live memory of the "Checking" ARRAYLIST.
     *
     * @param list / ArrayList
     * @throws FileNotFoundException /
     */
    public static void readChecking(ArrayList<Checking> list) throws FileNotFoundException {
        File file = new File("src/Checkings.txt");
        Scanner input = new Scanner(file);
        while(input.hasNextLine()){
            String[] field = input.nextLine().split(",");
            list.add(new Checking(field[0], field[1], Double.parseDouble(field[2]), field[3], Boolean.valueOf(field[4]), field[5], Integer.parseInt(field[6]), field[7], field[8]));
        }
        System.out.println("read checking accounts");
    }//end of readChecking


    /**
     * readSaving function
     *
     * The function that is responsible for taking the data from the Database for Savings account data, and adds it
     * into the live memory of the "Saving" ARRAYLIST.
     *
     * @param list / ArrayList
     * @throws FileNotFoundException /
     */
    public static void readSaving(ArrayList<Saving> list) throws FileNotFoundException {
        File file = new File("src/Savings.txt");
        Scanner input = new Scanner(file);
        while(input.hasNextLine()){
            String[] field = input.nextLine().split(",");
            list.add(new Saving(field[0], field[1],Double.parseDouble(field[2]), Double.parseDouble(field[3]),
                    Double.parseDouble(field[4]),field[5],Boolean.valueOf(field[6]),field[7],field[8], field[9]));
        }
        System.out.println("read savings account");
    }//end of readSaving


    /**
     * readLoan function
     *
     * The function that is responsible for taking the data from the Database for Loan account data, and adds it
     * into the live memory of the "Loan" ARRAYLIST.
     *
     * @param list / ArrayList
     * @throws FileNotFoundException /
     */
    public static void readLoan(ArrayList<Loan> list) throws FileNotFoundException {
        File file = new File("src/Loans.txt");
        Scanner input = new Scanner(file);
        while(input.hasNextLine()){
            String[] field = input.nextLine().split(",");
            list.add(new Loan(field[0], field[1], Double.parseDouble(field[2]), Double.parseDouble(field[3]), Double.parseDouble(field[4]),
                    field[5], field[6], Double.parseDouble(field[7]), field[8], Boolean.valueOf(field[9]), field[10], field[11]));
        }
        System.out.println("read loans accounts");
    }//end of readLoan


    /**
     * readTransaction function
     *
     * The function that is responsible for taking the data from the Database for Transaction account data, and adds it
     * into the live memory of the "Transaction" ArrayList.
     *
     * @param list / ArrayList
     * @throws FileNotFoundException /
     */
    public static void readTransaction(ArrayList<Transaction> list) throws FileNotFoundException {
        File file = new File("src/Transactions.txt");
        Scanner input = new Scanner(file);
        while(input.hasNextLine()){
            String[] field = input.nextLine().split(",");
            list.add(new Transaction(field[0], Double.parseDouble(field[1]), field[2], field[3]));
        }
        System.out.println("read transactions");
    }//end of readTransaction


    /**
     * readGlobal function
     *
     * The function that is responsible for taking the global variables saved in the "Global" database, and setting
     * their values to the saved defaults.
     *
     * @throws FileNotFoundException /
     */
    public static void readGlobal() throws FileNotFoundException {
        File file = new File("src/Global.txt");
        Scanner input = new Scanner(file);
        while(input.hasNextLine()){
            String[] field = input.nextLine().split(",");
            savingsInterest = Double.parseDouble(field[0]);
            stlInterest = Double.parseDouble(field[1]);
            ltlInterest = Double.parseDouble(field[2]);
            ccInterest = Double.parseDouble(field[3]);
            cdInterest = Double.parseDouble(field[4]);
        }
        System.out.println("read rates");
    }//end of readGlobal


    /**
     * writeGlobal function
     *
     * The function that is responsible for taking the global variables currently in the live memory of the program
     * and saves their values into the SOURCE database for the purpose of data preservation, for the next time the
     * program is ran.
     *
     * @param source / src *.txt file for the database
     * @throws IOException /
     */
    public static void writeGlobal(String source) throws IOException {
        File file = new File("src/" + source);
        PrintWriter out = new PrintWriter(file);
        out.print(savingsInterest +","+ stlInterest +","+ ltlInterest +","+ ccInterest +","+ cdInterest);
        System.out.println("write " + source);
        out.close();
    }//end of writeGlobal


    /**
     * writeData function
     *
     * The function that is responsible for taking the list of ARRAYLIST data currently in the live memory
     * environment of the program, and saves their values to the database SOURCE for the purpose of data
     * preservation. for the next time the program is ran.
     *
     * @param list / Arraylist
     * @param source / src *.txt file for the database
     * @throws IOException /
     */
    public static void writeData(ArrayList<?> list, String source) throws IOException {
        File file = new File("src/" + source);
        PrintWriter out = new PrintWriter(file);
        for(int i=0; i<list.size(); i++) {
            out.println(list.get(i).toString());
        }
        System.out.println("write " + source);
        out.close();
    }//end of writeData

}//end of Main Class
