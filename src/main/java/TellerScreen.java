import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class TellerScreen {
    private JButton backButton;
    private JButton selectButton;
    private JComboBox Accounts;
    private JLabel customerName;
    private JButton creditButton;
    private JTextField creditAmount;
    private JButton debitButton;
    private JTextField debitAmount;
    private JPanel panel1;
    private JButton transferButton;
    private JComboBox fromAccount;
    private JComboBox toAccount;
    private JTextArea TransferAmount;
    private JTable accountInfo;
    private JButton closeAccountButton;
    private JTable transactions;
    private JMenuItem logout;
    private JMenuBar menu;
    private ArrayList<Account> accountList = new ArrayList<>();
    private Account current = null;
    public TellerScreen(String ssn) {
        menu = new JMenuBar();
        logout = new JMenuItem("Logout");
        menu.add(logout);
        final JFrame telScreen = new JFrame("PitA Bank");
        telScreen.setContentPane(panel1);
        telScreen.setPreferredSize(new Dimension(800, 600));
        telScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telScreen.pack();
        telScreen.setVisible(true);
        telScreen.setJMenuBar(menu);
        if (!Main.manager){closeAccountButton.setVisible(false);}
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
        DefaultTableModel accountModel = new DefaultTableModel();
        accountModel.addColumn("Account Type");
        accountModel.addColumn("Account ID");
        accountModel.addColumn("Balance");
        accountInfo.setModel(accountModel);
        DefaultTableModel transactionModel = new DefaultTableModel();
        transactionModel.addColumn("Date");
        transactionModel.addColumn("Processed");
        transactionModel.addColumn("Amount");
        transactionModel.addColumn("Description");
        transactions.setModel(transactionModel);
        selectButton.addActionListener(new ActionListener() {
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
                        accountModel.addRow(new Object[]{current.getType(), current.getAccountID(), current.getBalance()});
                        if(current.getType().equals("Gold Checking") || current.getType().equals("TMB Checking")){
                            for(int j = 0; j < Main.checks.size(); j++){
                                if(Main.checks.get(j).getCheckingAccID().equals(current.accountID)){
                                    Check temp = Main.checks.get(j);
                                    transactionModel.addRow(new Object[]{temp.getDate(), temp.isProcessed(), temp.getAmount(), null});
                                }//add check to table
                            }//search checks table for checks from current account
                        }//extra steps for checking accounts
                    }//add account info to tables
                }//search customer accounts for selected account
            }//selectButton action
        });

        creditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = Accounts.getSelectedItem().toString();
                for (int i = 0; i < accountList.size(); i++) {
                    if (accountList.get(i).getAccountID().equals(id)) {
                        current = accountList.get(i);
                    }
                }//get the selected account
                if(creditAmount.getText()!= null) {
                    double amount = Double.parseDouble(creditAmount.getText());
                    current.deposit(amount);
                    accountModel.setValueAt(current.getBalance(), 0, 2);
                }
            }
        });
        debitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = Accounts.getSelectedItem().toString();
                for (int i = 0; i < accountList.size(); i++) {
                    if (accountList.get(i).getAccountID().equals(id)) {
                        current = accountList.get(i);
                    }
                }//get the selected account
                if(debitAmount.getText() != null) {
                    double amount = Double.parseDouble(debitAmount.getText());
                    current.withdrawal(amount);
                    accountModel.setValueAt(current.getBalance(), 0, 2);
                }
            }
        });
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (TransferAmount.getText() != null) {
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
                    to.deposit(amount);
                    from.withdrawal(amount);
                }
                accountModel.setValueAt(current.getBalance(), 0, 2);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telScreen.dispose();
                EmpAccountLookup lookupScreen = new EmpAccountLookup();
            }
        });
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telScreen.dispose();
                LoginScreen loginScreen = new LoginScreen();
            }
        });
    }
}