import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This Java class uses Java Swing.
 *
 * This class is for the CustomerScreenAccounts screen.
 *
 * The Customer is presented with a drop down ComboBox with the numbers of their associated accounts.
 * Pressing the "Select" button selects the numbered account, and reads all the information associated with that
 * account in the below fields.
 *
 * Pressing the "Transfer" button executes a transfer of money from the account number in the first drop down ComboBox
 * to the account number in the second drop down ComboBox. The amount of money to be transfered is determined by the
 * amount that the customer puts into the panel below "Amount" on the GUI screen.
 *
 * If a check has been processed it is set "True", if a check has not yet been processed it is set"False"
 * Pressing the "Cancel Check" button changes the state of any checks that are currently set "False" to "Canceled".
 *
 * The fields at the lower half of the GUI are filled with the corresponding data from the account number that was
 * in the drop down ComboBox when the "Select" button was pressed.
 *
 * The fields show the currently selected account type, the associated account number, the account balance,
 * the amount due / owed to the bank, and the data that the owed amount is due to be paid without penalty.
 *
 * The bottom of the GUI displays the history and past transactions of the selected account type.
 * It displays the data the transaction was made, the transaction number, whether the transaction was processed,
 * the amount of money in the transaction, and a description of the transaction.
 *
 * Pressing the "Back" button sends the GUI back to the "ATMLogin" screen.
 *
 * Pressing the "Logout" button sends the GUI back to the initial "LoginScreen" screen.
 *
 * Upon exiting the GUI, the system will write all the data in live memory to the database, and the program
 * will terminate.
 */
public class CustomerScreenAccounts {
    private JComboBox Accounts;
    private JButton backButton;
    private JPanel panel1;
    private JButton transferButton;
    private JComboBox toAccount;
    private JComboBox fromAccount;
    private JLabel From;
    private JLabel To;
    private JTable accountInfo;
    private JTextField TransferAmount;
    private JButton cancelCheckButton;
    private JTable transactions;
    private JButton SelectButton;
    private JButton transactionButton;
    private JTextField transAmount;
    private JTextField transDesc;
    private JLabel amtLabel;
    private JLabel descLabel;
    private JMenuItem logout;
    private JMenuBar menu;
    private ArrayList<Account> accountList = new ArrayList<>();
    private Account current = null;
    private Loan currentL = null;
    public CustomerScreenAccounts(String ssn) {
        menu = new JMenuBar();
        logout = new JMenuItem("Logout");
        menu.add(logout);
        final JFrame cusScreen = new JFrame("PitA Bank");
        cusScreen.setContentPane(panel1);
        cusScreen.setPreferredSize(new Dimension(800, 600));
        cusScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cusScreen.pack();
        cusScreen.setVisible(true);
        cusScreen.setJMenuBar(menu);
        cusScreen.setLocationRelativeTo(null);
        for (int i = 0; i < Main.checkings.size(); i++){
            Checking temp = Main.checkings.get(i);
            if (temp.getCustID().equals(ssn)){
                Accounts.addItem(temp.getAccountID());
                fromAccount.addItem((temp.getAccountID()));
                toAccount.addItem((temp.getAccountID()));
                accountList.add(temp);
            }
        }
        for (int i = 0; i < Main.savings.size(); i++){
            Saving temp = Main.savings.get(i);
            if (temp.getCustID().equals(ssn)){
                Accounts.addItem(temp.getAccountID());
                fromAccount.addItem(temp.getAccountID());
                toAccount.addItem(temp.getAccountID());
                accountList.add(temp);
            }
        }
        for (int i = 0; i < Main.loans.size(); i++) {
            Loan temp = Main.loans.get(i);
            if (temp.getCustID().equals(ssn)) {
                Accounts.addItem(temp.getAccountID());
                toAccount.addItem(temp.getAccountID());
                accountList.add(temp);
            }
        }
        transactionButton.setVisible(false);
        transAmount.setVisible(false);
        transDesc.setVisible(false);
        amtLabel.setVisible(false);
        descLabel.setVisible(false);
        DefaultTableModel accountModel = new DefaultTableModel();
        accountModel.addColumn("Account Type");
        accountModel.addColumn("Account ID");
        accountModel.addColumn("Balance");
        accountModel.addColumn("Amount Due");
        accountModel.addColumn("Due Date");
        accountInfo.setModel(accountModel);
        DefaultTableModel transactionModel = new DefaultTableModel();
        transactionModel.addColumn("Date");
        transactionModel.addColumn("#");
        transactionModel.addColumn("Processed");
        transactionModel.addColumn("Amount");
        transactionModel.addColumn("Description");
        transactions.setModel(transactionModel);

        SelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = Accounts.getSelectedItem().toString();
                transactionModel.setRowCount(0);
                for (int i = 0; i < accountList.size(); i++){
                    if(accountList.get(i).getAccountID().equals(id)) {
                        current = accountList.get(i);
                        if(accountModel.getRowCount() > 0) {
                            accountModel.removeRow(0);
                        }
                        if(current.getType().equals("Gold Checking") || current.getType().equals("TMB Checking")){
                            accountModel.addRow(new Object[]{current.getType(), current.getAccountID(),
                                    current.getBalance(), null, null});
                            transactionButton.setVisible(true);
                            transAmount.setVisible(true);
                            transDesc.setVisible(true);
                            amtLabel.setVisible(true);
                            descLabel.setVisible(true);
                            for(int j = 0; j < Main.checks.size(); j++){
                                if(Main.checks.get(j).getCheckingAccID().equals(current.accountID)){
                                    Check temp = Main.checks.get(j);
                                    transactionModel.addRow(new Object[]{temp.getDate(), temp.getCheckNumber(),
                                            temp.isProcessed(), temp.getAmount(), null});
                                }//add check to table
                            }//search checks table for checks from current account
                        }//extra steps for checking accounts
                        else if(current.getType().equals("Credit Card")){
                            for (int k = 0; k < Main.loans.size(); k ++){
                                if (Main.loans.get(k).getAccountID().equals(current.accountID)){
                                    currentL = Main.loans.get(k);
                                }
                            }
                            String ddate = currentL.getDueDate().toString();
                            ddate = ddate.substring(5, 7) + "/" + ddate.substring(8) + "/" + ddate.substring(0, 4);
                            accountModel.addRow(new Object[]{current.getType(), current.getAccountID(),
                                    current.getBalance(), currentL.getAmountDue(), ddate});
                            transactionButton.setVisible(true);
                            transAmount.setVisible(true);
                            transDesc.setVisible(true);
                            amtLabel.setVisible(true);
                            descLabel.setVisible(true);
                            for(int j = 0; j < Main.transactions.size(); j++){
                                if(Main.transactions.get(j).getAccount().equals(current.accountID)){
                                    Transaction temp = Main.transactions.get(j);
                                    transactionModel.addRow(new Object[]{temp.getDate(), null, "true", temp.getAmount(), temp.getDescription()});
                                }//add check to table
                            }//search checks table for CC from current account
                        }//extra steps for CC loan accounts
                        else if(current.getType().equals("Short Term Loan") || current.getType().equals("Long Term Loan")) {
                            for (int k = 0; k < Main.loans.size(); k++) {
                                System.out.println(k);
                                if (Main.loans.get(k).getAccountID().equals(current.accountID)) {
                                    currentL = Main.loans.get(k);
                                }
                            }
                            String ddate = currentL.getDueDate().toString();
                            ddate = ddate.substring(5, 7) + "/" + ddate.substring(8) + "/" + ddate.substring(0, 4);
                            accountModel.addRow(new Object[]{current.getType(), current.getAccountID(),
                                    current.getBalance(), currentL.getAmountDue(), ddate});
                        }else accountModel.addRow(new Object[]{current.getType(), current.getAccountID(),
                                current.getBalance(), null, null});
                    }//add account info to tables
                }//search customer accounts for selected account
            }//selectButton action
        });
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!TransferAmount.getText().equals("")) {
                    double amount = Double.parseDouble(TransferAmount.getText());
                    Account to = null;
                    Account from = null;
                    String fromID = fromAccount.getSelectedItem().toString();
                    String toID = toAccount.getSelectedItem().toString();

                    for (int i = 0; i < accountList.size(); i++) {
                        if (accountList.get(i).getAccountID().equals(fromID)) {
                            from = accountList.get(i);
                        }
                        if (accountList.get(i).getAccountID().equals(toID)) {
                            to = accountList.get(i);
                        }
                    }//get the selected account
                    if (amount <= from.balance) {
                        to.deposit(amount);
                        from.withdrawal(amount);
                    }
                    accountModel.setValueAt(current.getBalance(), 0, 2);
                }

            }
        });
        cancelCheckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Check toCancel = new Check(null, null, null, 0.0, "false");
                int row = transactions.getSelectedRow();
                String num = transactions.getValueAt(row, 1).toString();
                for (int i = 0; i < Main.checks.size(); i++){
                    if (Main.checks.get(i).getCheckNumber().equals(num)){
                        toCancel = Main.checks.get(i);
                        toCancel.setProcessed("cancelled");
                        current.withdrawal(15);
                        accountInfo.setValueAt(current.balance, 0, 2);
                        transactions.setValueAt(toCancel.isProcessed(), row, 2);

                    }
                }

            }
        });
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cusScreen.dispose();
                LoginScreen loginScreen = new LoginScreen();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cusScreen.dispose();
                CustomerScreenSSN screenSSN = new CustomerScreenSSN();
            }
        });

        transactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (accountModel.getValueAt(0, 0).equals("Credit Card")){
                    Transaction tran = new Transaction(accountModel.getValueAt(0, 1).toString(),
                            Double.parseDouble(transAmount.getText()),
                            LocalDate.now().toString(), transDesc.getText());
                    //T ODO: credit limit check
                    Main.transactions.add(tran);
                    transactionModel.addRow(new Object[]{tran.getDate(), null, "true", tran.getAmount(), tran.getDescription()});

                }else{
                    int sizeChecks = Main.checks.size() -1;
                    int num = Integer.parseInt(Main.checks.get(sizeChecks).getCheckNumber()) + 1;
                    Check checky = new Check(""+num,
                            accountModel.getValueAt(0,1).toString(), LocalDate.now().toString(),
                            Double.parseDouble((transAmount.getText())),
                            "false");
                    Main.checks.add(checky);
                    transactionModel.addRow(new Object[]{checky.getDate(), checky.getCheckNumber(), checky.isProcessed(), checky.getAmount(),""});

                }


            }
        });
    }
}
