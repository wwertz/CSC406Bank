import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerScreenAccounts {
    private JComboBox Accounts;
    private JButton backButton;
    private JPanel panel1;
    private JButton transferButton;
    private JComboBox destAccountBox;
    private JComboBox sourceAccountBox;
    private JLabel From;
    private JLabel To;
    private JTable accountInfo;
    private JTextField Amount;
    private JButton cancelCheckButton;
    private JTextField textField1;
    private JTable transactions;
    private JMenuItem logout;
    private JMenuBar menu;

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
        for (int i = 0; i < Main.checkings.size(); i++){
            Checking temp = Main.checkings.get(i);
            if (temp.getCustID().equals(ssn)){
                Accounts.addItem("Checking #" + temp.getAccountID());

            }
        }
        for (int i = 0; i < Main.savings.size(); i++){
            Saving temp = Main.savings.get(i);
            if (temp.getCustID().equals(ssn)){
                Accounts.addItem("Savings #" + temp.getAccountID());

            }
        }
        for (int i = 0; i < Main.loans.size(); i++){
            Loan temp = Main.loans.get(i);
            if (temp.getCustID().equals(ssn)){
                String type = null;
                switch (temp.getType()){
                    case 'c':
                        type = "Credit Card";
                        break;
                    case 's':
                        type = "Short Loan";
                        break;
                    case'l':
                        type = "Long Loan";
                        break;
                }
                Accounts.addItem(type + " #" + temp.getAccountID());

            }
        }
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cusScreen.dispose();
                CustomerScreenSSN screenSSN = new CustomerScreenSSN();
            }
        });
        accountInfo.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                {null,null,null}
                },
                new String []{
                        "Account Type","Account ID", "Balance"
                 }
                ) {public boolean isCellEditable(int row, int column){return false;}}
        );
        transactions.setModel(new javax.swing.table.DefaultTableModel(
                                     new Object [][] {
                                             {null,null,null,null}
                                     },
                                     new String []{
                                             "Date", "Processed","Amount", "Desc"
                                     }
                             ) {public boolean isCellEditable(int row, int column){return false;}}
        );
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cusScreen.dispose();
                LoginScreen loginScreen = new LoginScreen();
            }
        });
    }
}
