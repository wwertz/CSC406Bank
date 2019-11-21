import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ATMScreen {
    private JTable accountInfo;
    private JButton withdrawButton;
    private JTextField amount;
    private JPanel panel1;
    private JButton backButton;
    private JMenuItem logout;
    private JMenuBar menu;

    String type;
    String accountType;
    double balance;
    Checking temp;

    public ATMScreen(String ssn) {
        menu = new JMenuBar();
        logout = new JMenuItem("Logout");
        menu.add(logout);
        final JFrame ATMScreen = new JFrame("PitA Bank");
        ATMScreen.setContentPane(panel1);
        ATMScreen.setPreferredSize(new Dimension(800, 600));
        ATMScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ATMScreen.pack();
        ATMScreen.setVisible(true);
        ATMScreen.setJMenuBar(menu);

        //get checking info
        for (int i = 0; i < Main.checkings.size(); i++){
            temp = Main.checkings.get(i);
            if (temp.getCustID().equals(ssn)){
                type = temp.getType();
                accountType = temp.getAccountID();
                balance = temp.getBalance();
            }
        }

        //table
        accountInfo.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {type,accountType,balance}
                        },
                new String []{
                        "Account Type","Account ID", "Balance"
                }
                ) {public boolean isCellEditable(int row, int column){return false;}}
        );

        //back
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ATMScreen.dispose();
                ATMLogin ATM = new ATMLogin();
            }
        });

        //logout back to startup
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ATMScreen.dispose();
                LoginScreen loginScreen = new LoginScreen();
            }
        });

        //withdraw button
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temp.withdrawal(Double.parseDouble(amount.getText()));
                ATMScreen.dispose();
                ATMLogin ATM = new ATMLogin();
            }
        });
    }

}
