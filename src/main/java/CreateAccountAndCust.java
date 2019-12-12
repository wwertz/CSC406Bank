import Users.Customer;
import java.time.LocalDate;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                        LocalDate cdDate = LocalDate.now().plusYears(3);
                        String cdate = LocalDate.now().toString();
                        cdate = cdate.substring(5, 7) + "/" + cdate.substring(8) + "/" + cdate.substring(0, 4);
                        Saving account = new Saving(temp.getSsn(), acctNumField.getText(), Double.parseDouble(balanceField.getText()),
                                Double.parseDouble(balanceField.getText()), Main.cdInterest, cdate,
                                true, cdDate.toString(), "CD", null);
                        Main.savings.add(account);
                    }
                    if (accountType.getSelectedItem().equals("Short Term Loan")) {
                        if (backupAccount.getText().isEmpty()) {
                            backup = false;
                            backupAccount.setText("null");
                        }
                        LocalDate dueDate = LocalDate.now().plusMonths(1);
                        Loan account = new Loan(temp.getSsn(), acctNumField.getText(), Double.parseDouble(balanceField.getText()),
                                Double.parseDouble(balanceField.getText()), Main.stlInterest, dueDate.toString(), null,
                                Double.parseDouble(balanceField.getText())*.05, null, false, "Short Term Loan", null);
                        Main.loans.add(account);
                    }
                    if (accountType.getSelectedItem().equals("Long Term Loan")) {
                        backup = false;
                        backupAccount.setText("null");
                        LocalDate dueDate = LocalDate.now().plusMonths(1);
                        Loan account = new Loan(temp.getSsn(), acctNumField.getText(), Double.parseDouble(balanceField.getText()),
                                Double.parseDouble(balanceField.getText()), Main.ltlInterest, dueDate.toString(), null,
                                Double.parseDouble(balanceField.getText())*.025, null, false, "Long Term Loan", null);
                        Main.loans.add(account);
                    }
                    if (accountType.getSelectedItem().equals("Credit Card")) {
                        backup = false;
                        backupAccount.setText("null");
                        LocalDate dueDate = LocalDate.now().plusMonths(1);
                        Loan account = new Loan(temp.getSsn(), acctNumField.getText(), Double.parseDouble(balanceField.getText()),
                                Double.parseDouble(balanceField.getText()), Main.ccInterest, dueDate.toString(), null,
                                0.0, null, false, "Credit Card", null);
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
