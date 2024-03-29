import Users.Customer;
import java.time.LocalDate;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * This Java class uses Java Swing.
 *
 * This class is for the CreateAccountAndCust screen. This screen is accessible from the ManagerSelectScreen.
 *
 * This screen is the main administration screen for opening new accounts for new customers.
 * The "Lookup SSN" button verifies whether the SSN entered into the text field does or doesn't belong to
 * an already existing account in the system.
 *
 * The Last Name, First Name, Address, City, and State text fields collect information to be read into the system
 * for creation of a new customer of the bank.
 *
 * The Acct Type drop down ComboBox allows the Manager to select what kind of account to create.
 * The Account # text field allows the Manager to set the number for the account.
 * The manager is able to set an another account to be an associated Backup Account, and is able to set
 * the ATM bank card number, and the PIN.
 *
 * Pressing the "Create" button takes all of the filled information and saves it into memory as a new user
 * in the system.
 *
 * Pressing the "Back" button sends the GUI back to the "ManagerSelectScreen".
 *
 * Upon exiting the GUI, the system will write all the data in live memory to the database, and the program
 * will terminate.
 */
public class CreateAccountAndCust {
    private JButton lookupSSNButton;
    private JTextField ssnField;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField addressField;
    private JTextField cityField;
    private JTextField statetField;
    private JTextField zipField;
    private JComboBox accountType;
    private JTextField acctNumField;
    private JTextField balanceField;
    private JButton backButton;
    private JButton createButton;
    private JPanel panel1;
    private JTextField backupAccount;
    private JTextField ATM;
    private JTextField pinField;
    private JLabel errorMessage;
    private JMenuItem logout;
    private JMenuBar menu;
    private Customer temp;
    private boolean exists = false;
    private boolean backup = false;
    public CreateAccountAndCust() {
        menu = new JMenuBar();
        logout = new JMenuItem("Logout");
        menu.add(logout);
        accountType.addItem("Checking");
        accountType.addItem("Savings");
        accountType.addItem("CD");
        accountType.addItem("Short Term Loan");
        accountType.addItem("Long Term Loan");
        accountType.addItem("Credit Card");

        final JFrame createAcctCust = new JFrame("PitA Bank");
        createAcctCust.setContentPane(panel1);
        createAcctCust.setPreferredSize(new Dimension(800, 600));
        createAcctCust.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createAcctCust.pack();
        createAcctCust.setVisible(true);
        createAcctCust.setJMenuBar(menu);
        createAcctCust.setLocationRelativeTo(null);

        lookupSSNButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                errorMessage.setText("");
                lastNameField.setText("");
                lastNameField.setEditable(true);
                firstNameField.setText("");
                firstNameField.setEditable(true);
                addressField.setText("");
                addressField.setEditable(true);
                cityField.setText("");
                cityField.setEditable(true);
                statetField.setText("");
                statetField.setEditable(true);
                zipField.setText("");
                zipField.setEditable(true);
                ATM.setText(null);
                pinField.setText(null);
                backupAccount.setText(null);
                for(int i = 0; i < Main.customers.size(); i++){
                    if (ssnField.getText().equals(Main.customers.get(i).getSsn())){
                        exists = true;
                        temp = Main.customers.get(i);
                        lastNameField.setText(temp.getLastName());
                        lastNameField.setEditable(false);
                        firstNameField.setText(temp.getFirstName());
                        firstNameField.setEditable(false);
                        addressField.setText(temp.getAddress());
                        addressField.setEditable(false);
                        cityField.setText(temp.getCity());
                        cityField.setEditable(false);
                        statetField.setText(temp.getState());
                        statetField.setEditable(false);
                        zipField.setText(temp.getZipcode());
                        zipField.setEditable(false);
                        ATM.setText(null);
                        pinField.setText(null);
                        backupAccount.setText(null);
                    }//customer exists, fill in info

                }//see if customer exists
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                errorMessage.setText("");
                boolean acctExists = false;
                boolean create = true;
                for (int i = 0; i < Main.customers.size(); i++) {
                    if (ssnField.getText().equals(Main.customers.get(i).getSsn())) {
                        exists = true;
                    }
                }
                for (int i = 0; i < Main.checkings.size(); i++) {
                    if (acctNumField.getText().equals(Main.checkings.get(i).getAccountID())) {
                        acctExists = true;
                    }
                }
                for (int i = 0; i < Main.savings.size(); i++) {
                    if (acctNumField.getText().equals(Main.savings.get(i).getAccountID())) {
                        acctExists = true;
                    }
                }
                for (int i = 0; i < Main.loans.size(); i++) {
                    if (acctNumField.getText().equals(Main.loans.get(i).getAccountID())) {
                        acctExists = true;
                    }
                }
                if (!acctExists) {
                    boolean backupExists = false;
                    if (!exists) {
                        temp = new Customer(ssnField.getText(), firstNameField.getText(), lastNameField.getText(),
                                addressField.getText(), cityField.getText(), statetField.getText(), zipField.getText(), ATM.getText(),
                                pinField.getText());
                        Main.customers.add(temp);
                    }
                    if (accountType.getSelectedItem().equals("Checking")) {
                        if (backupAccount.getText().isEmpty()) {
                            backup = false;
                            backupAccount.setText("null");
                        }
                        else{
                            for(int i = 0; i < Main.savings.size(); i++){
                                Account temp = Main.savings.get(i);
                                if(backupAccount.getText().equals(temp.accountID)){
                                    backupExists = true;
                                }
                                if (backupAccount.getText().equals(temp.accountID)
                                        && !ssnField.getText().equals(temp.custID)){
                                    create = false;
                                    errorMessage.setText("Backup does not belong to customer");
                                }
                            }
                            if (!backupExists){
                                create = false;
                                errorMessage.setText("Backup does not exist");
                            }
                        }
                        String cdate = LocalDate.now().toString();
                        cdate = cdate.substring(5, 7) + "/" + cdate.substring(8) + "/" + cdate.substring(0, 4);
                        if (create) {
                            Checking account = new Checking(temp.getSsn(), acctNumField.getText(), Double.parseDouble(balanceField.getText()),
                                    cdate, backup, backupAccount.getText(), 0, "Checking", null);
                            Main.checkings.add(account);
                        }
                    }
                    if (accountType.getSelectedItem().equals("Savings")) {
                        if (backupAccount.getText().isEmpty()) {
                            backup = false;
                            backupAccount.setText("null");
                        } else {
                            for(int i = 0; i < Main.savings.size(); i++){
                                if (backupAccount.getText().equals(Main.savings.get(i).accountID)
                                        && !ssnField.getText().equals(Main.savings.get(i).custID)){
                                    create = false;
                                    errorMessage.setText("Backup does not belong to customer");
                                }
                            }
                            if(create) {
                                for (int i = 0; i < Main.checkings.size(); i++) {
                                    if (Main.checkings.get(i).getAccountID().equals(backupAccount.getText())) {
                                        Main.checkings.get(i).setHasBackup(true);
                                        Main.checkings.get(i).setBackupID(backupAccount.getText());
                                    }
                                }
                            }
                        }
                        String cdate = LocalDate.now().toString();
                        cdate = cdate.substring(5, 7) + "/" + cdate.substring(8) + "/" + cdate.substring(0, 4);
                        if(create) {
                            Saving account = new Saving(temp.getSsn(), acctNumField.getText(), Double.parseDouble(balanceField.getText()),
                                    Double.parseDouble(balanceField.getText()), Main.savingsInterest, cdate,
                                    false, "null", "Saving", null);
                            Main.savings.add(account);
                        }
                    }
                    if (accountType.getSelectedItem().equals("CD")) {
                        backup = false;
                        backupAccount.setText("null");
                        String cdDate = LocalDate.now().plusYears(3).toString();
                        cdDate = cdDate.substring(5, 7) + "/" + cdDate.substring(8) + "/" + cdDate.substring(0, 4);
                        String cdate = LocalDate.now().toString();
                        cdate = cdate.substring(5, 7) + "/" + cdate.substring(8) + "/" + cdate.substring(0, 4);
                        Saving account = new Saving(temp.getSsn(), acctNumField.getText(), Double.parseDouble(balanceField.getText()),
                                Double.parseDouble(balanceField.getText()), Main.cdInterest, cdate,
                                true, cdDate.toString(), "CD", "null");
                        Main.savings.add(account);
                    }
                    if (accountType.getSelectedItem().equals("Short Term Loan")) {
                        backup = false;
                        backupAccount.setText("null");

                        String cdate = LocalDate.now().toString();
                        cdate = cdate.substring(5, 7) + "/" + cdate.substring(8) + "/" + cdate.substring(0, 4);
                        String dueDate = LocalDate.now().plusMonths(1).toString();
                        dueDate = dueDate.substring(5, 7) + "/" + dueDate.substring(8) + "/" + dueDate.substring(0, 4);
                        Loan account = new Loan(temp.getSsn(), acctNumField.getText(), Double.parseDouble(balanceField.getText()),
                                Double.parseDouble(balanceField.getText()), Main.stlInterest, dueDate,cdate ,
                                Double.parseDouble(balanceField.getText())*.05, cdate, false, "Short Term Loan", cdate);
                        Main.loans.add(account);
                    }
                    if (accountType.getSelectedItem().equals("Long Term Loan")) {
                        backup = false;
                        backupAccount.setText("null");
                        String cdate = LocalDate.now().toString();
                        cdate = cdate.substring(5, 7) + "/" + cdate.substring(8) + "/" + cdate.substring(0, 4);
                        String dueDate = LocalDate.now().plusMonths(1).toString();
                        dueDate = dueDate.substring(5, 7) + "/" + dueDate.substring(8) + "/" + dueDate.substring(0, 4);
                        Loan account = new Loan(temp.getSsn(), acctNumField.getText(), Double.parseDouble(balanceField.getText()),
                                Double.parseDouble(balanceField.getText()), Main.ltlInterest, dueDate, cdate,
                                Double.parseDouble(balanceField.getText())*.025, cdate, false, "Long Term Loan", cdate);
                        Main.loans.add(account);
                    }
                    if (accountType.getSelectedItem().equals("Credit Card")) {
                        backup = false;
                        backupAccount.setText("null");
                        String cdate = LocalDate.now().toString();
                        cdate = cdate.substring(5, 7) + "/" + cdate.substring(8) + "/" + cdate.substring(0, 4);
                        String dueDate = LocalDate.now().plusMonths(1).toString();
                        dueDate = dueDate.substring(5, 7) + "/" + dueDate.substring(8) + "/" + dueDate.substring(0, 4);
                        Loan account = new Loan(temp.getSsn(), acctNumField.getText(), Double.parseDouble(balanceField.getText()),
                                Double.parseDouble(balanceField.getText()), Main.ccInterest, dueDate, cdate,
                                0.0, cdate, false, "Credit Card", cdate);
                        Main.loans.add(account);
                    }
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAcctCust.dispose();
                ManagerSelectFunction managerScreen = new ManagerSelectFunction();
            }
        });

    }
}
