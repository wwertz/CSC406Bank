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

        lookupSSNButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                    }//customer exists, fill in info
                }//see if customer exists
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!exists){
                    temp = new Customer(ssnField.getText(), firstNameField.getText(),lastNameField.getText(),
                            addressField.getText(), cityField.getText(), statetField.getText(), zipField.getText(), ATM.getText(),
                    pinField.getText());
                    Main.customers.add(temp);
                }
                if(accountType.getSelectedItem().equals("Checking")){
                    if (backupAccount.getText().equals(null)){
                        backup = false;
                    }
                    Checking account = new Checking(temp.getSsn(), acctNumField.getText(), Double.parseDouble(balanceField.getText()),
                            LocalDate.now().toString(), backup, backupAccount.getText(), 0, "Checking");
                    Main.checkings.add(account);
                }
                if(accountType.getSelectedItem().equals("Savings")){
                    if (backupAccount.getText().equals(null)){
                        backup = false;
                    }else{
                        for (int i = 0; i < Main.checkings.size(); i++){
                            if (Main.checkings.get(i).getAccountID().equals(backupAccount.getText())){
                                Main.checkings.get(i).setHasBackup(true);
                                Main.checkings.get(i).setBackupID(backupAccount.getText());
                            }
                        }
                    }
                   Saving account = new Saving(temp.getSsn(), acctNumField.getText(), Double.parseDouble(balanceField.getText()),
                            Main.savingsInterest, LocalDate.now().toString(), false, null, "Saving");
                    Main.savings.add(account);
                }
                if(accountType.getSelectedItem().equals("CD")){
                    if (backupAccount.getText().equals(null)){
                        backup = false;
                    }
                    LocalDate cdDate = LocalDate.now().plusYears(3);
                    Saving account = new Saving(temp.getSsn(), acctNumField.getText(), Double.parseDouble(balanceField.getText()),
                            Main.cdInterest, LocalDate.now().toString(), true, cdDate.toString(), "CD");
                    Main.savings.add(account);
                }
                if(accountType.getSelectedItem().equals("Short Term Loan")){
                    if (backupAccount.getText().equals(null)){
                        backup = false;
                    }
                    LocalDate dueDate = LocalDate.now().plusYears(5);
                    Loan account = new Loan(temp.getSsn(), acctNumField.getText(), Double.parseDouble(balanceField.getText()),
                            Double.parseDouble(balanceField.getText()), Main.stlInterest,dueDate.toString(), null,
                            0.0, null, false, "Short Term Loan");
                    Main.loans.add(account);
                }
                if(accountType.getSelectedItem().equals("Long Term Loan")){
                    if (backupAccount.getText().equals(null)){
                        backup = false;
                    }
                    LocalDate dueDate = LocalDate.now().plusYears(15);
                    Loan account = new Loan(temp.getSsn(), acctNumField.getText(), Double.parseDouble(balanceField.getText()),
                            Double.parseDouble(balanceField.getText()), Main.ltlInterest, dueDate.toString(), null,
                            0.0, null, false, "Long Term Loan");
                    Main.loans.add(account);
                }
                if(accountType.getSelectedItem().equals("Credit Card")){
                    if (backupAccount.getText().equals(null)){
                        backup = false;
                    }
                    LocalDate dueDate = LocalDate.now().plusYears(15);
                    Loan account = new Loan(temp.getSsn(), acctNumField.getText(), Double.parseDouble(balanceField.getText()),
                            Double.parseDouble(balanceField.getText()), Main.ccInterest, dueDate.toString(), null,
                            0.0, null, false, "Credit Card");
                    Main.loans.add(account);
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
