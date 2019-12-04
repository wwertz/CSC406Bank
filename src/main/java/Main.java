// CSC 406 Banking Project

// Jake Blessing
// Benjamin Oliphant
// Sam Poirer
// Wayne Wertz

import Users.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static boolean manager = false;
    //build list
    public static ArrayList<Customer> customers = new ArrayList<>();
    public static ArrayList<Check> checks = new ArrayList<>();
    public static ArrayList<Checking> checkings = new ArrayList<>();
    public static ArrayList<Saving> savings = new ArrayList<>();
    public static ArrayList<Loan> loans = new ArrayList<>();
    public static ArrayList<Transaction> transactions = new ArrayList<>();
    public static double savingsInterest = 0.1;
    public static double stlInterest = 0.1;
    public static double ltlInterest = 0.1;
    public static double ccInterest = 0.1;
    public static double cdInterest = 0.1;

    public static void main(String[] args) throws IOException {

        //read data from databases
        System.out.println(LocalDate.now().toString());
        readCustomer(customers);
        readChecks(checks);
        readChecking(checkings);
        readSaving(savings);
        readLoan(loans);
        readTransaction(transactions);
        readGlobal();

        //launch gui
        LoginScreen initial = new LoginScreen();

        //testing
        //stopPayment(checks, checkings,"202");

        //wait for system to close
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

    public static void updateDatabase() throws IOException {
        writeData(customers, "Customers.txt");
        writeData(checks, "Checks.txt");
        writeData(checkings, "Checkings.txt");
        writeData(savings, "Savings.txt");
        writeData(loans, "Loans.txt");
        writeData(transactions, "Transactions.txt");
        writeGlobal("Global.txt");
    }//end of database

    public static void readCustomer(ArrayList<Customer> list) throws FileNotFoundException {
        File file = new File("src/Customers.txt");
        Scanner input = new Scanner(file);
        while(input.hasNextLine()){
            String[] field = input.nextLine().split(",");
            list.add(new Customer(field[0],field[1],field[2],field[3],field[4],field[5],field[6],field[7],field[8]));
        }
        System.out.println("read customer");
    }//end of readCustomer

    public static void readChecks(ArrayList<Check> list) throws FileNotFoundException {
        File file = new File("src/Checks.txt");
            Scanner input = new Scanner(file);
            while(input.hasNextLine()){
                String[] field = input.nextLine().split(",");
                list.add(new Check(field[0],field[1],field[2],Double.parseDouble(field[3]), field[4]));
            }
        System.out.println("read checks");
    }//end of readChecks

    public static void readChecking(ArrayList<Checking> list) throws FileNotFoundException {
        File file = new File("src/Checkings.txt");
        Scanner input = new Scanner(file);
        while(input.hasNextLine()){
            String[] field = input.nextLine().split(",");
            list.add(new Checking(field[0], field[1], Double.parseDouble(field[2]), field[3], Boolean.valueOf(field[4]), field[5], Integer.parseInt(field[6]), field[7]));
        }
        System.out.println("read checking accounts");
    }//end of readChecking

    public static void readSaving(ArrayList<Saving> list) throws FileNotFoundException {
        File file = new File("src/Savings.txt");
        Scanner input = new Scanner(file);
        while(input.hasNextLine()){
            String[] field = input.nextLine().split(",");
            list.add(new Saving(field[0], field[1],Double.parseDouble(field[2]), Double.parseDouble(field[3]),
                    Double.parseDouble(field[4]),field[5],Boolean.valueOf(field[6]),field[7],field[8]));
        }
        System.out.println("read savings account");
    }//end of readSaving

    public static void readLoan(ArrayList<Loan> list) throws FileNotFoundException {
        File file = new File("src/Loans.txt");
        Scanner input = new Scanner(file);
        while(input.hasNextLine()){
            String[] field = input.nextLine().split(",");
            list.add(new Loan(field[0], field[1], Double.parseDouble(field[2]), Double.parseDouble(field[3]), Double.parseDouble(field[4]),
                    field[5], field[6], Double.parseDouble(field[7]), field[8], Boolean.valueOf(field[9]), field[10]));
        }
        System.out.println("read loans accounts");
    }//end of readLoan

    public static void readTransaction(ArrayList<Transaction> list) throws FileNotFoundException {
        File file = new File("src/Transactions.txt");
        Scanner input = new Scanner(file);
        while(input.hasNextLine()){
            String[] field = input.nextLine().split(",");
            list.add(new Transaction(field[0], Double.parseDouble(field[1]), field[2], field[3]));
        }
        System.out.println("read transactions");
    }//end of readTransaction

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

    public static void writeGlobal(String source) throws IOException {
        File file = new File("src/" + source);
        PrintWriter out = new PrintWriter(file);
        out.print(savingsInterest +","+ stlInterest +","+ ltlInterest +","+ ccInterest +","+ cdInterest);
        System.out.println("write " + source);
        out.close();
    }//end of writeGlobal

    public static void writeData(ArrayList<?> list, String source) throws IOException {
        File file = new File("src/" + source);
        PrintWriter out = new PrintWriter(file);
        for(int i=0; i<list.size(); i++) {
            out.println(list.get(i).toString());
        }
        System.out.println("write " + source);
        out.close();
    }//end of writeData

    //still needs to charge the customer once we get data in
    public static void stopPayment(ArrayList<Check> list, ArrayList<Checking> checking, String checkNumber){
        for(int i=0; i<list.size(); i++){
            if(list.get(i).getCheckNumber().equals(checkNumber)){
                if(list.get(i).isProcessed().equals("false")){
                    System.out.println(list.get(i).getCheckNumber() + " Is removed and wont be processed");
                    for(int j=0; j<checking.size(); j++){
                        //find the right account to charge
                        if(list.get(i).getCheckingAccID().equals(checking.get(j).getAccountID()))
                            checking.get(j).withdrawal(15.00);
                    }//end of for j
                    list.remove(i);
                }else{
                    System.out.println(list.get(i) + "Was already processed");
                }//end of for i
            }
        }
    }//end of stopPayment

}//end of Main Class
