import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


/**
 * This Java class uses Java Swing.
 *
 * This class is for the ATM Screen for the customers. Upon entering a valid card number and PIN in the
 * previous screen, ATMLogin, the customer enters this screen.
 *
 * The ATMScreen presents the user with the Account types associated with their account that are accessible from the
 * ATM, the Account ID, and the current Balance of their account.
 *
 * Below near the center of the screen, there is a withdraw button, and a text field for the customer to type the
 * amount they wish to withdraw from the system.
 *
 * Pressing the "Back" button sends the GUI back to the "ATMLogin" screen.
 *
 * Pressing the "Logout" button sends the GUI back to the initial "LoginScreen" screen.
 *
 * Upon exiting the GUI, the system will write all the data in live memory to the database, and the program
 * will terminate.
 */
public class ATMScreen {
    private JTable accountInfo;
    private JButton withdrawButton;
    private JTextField amount;
    private JPanel panel1;
    private JButton backButton;
    private JLabel errorMessage;
    private JMenuItem logout;
    private JMenuBar menu;

    String type;
    String accountType;
    double balance;
    Checking temp;

    public ATMScreen(String ssn) {
        errorMessage.setText("");
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
        ATMScreen.setLocationRelativeTo(null);

        //get checking info
        for (int i = 0; i < Main.checkings.size(); i++){
            if(ssn.equals(Main.checkings.get(i).getCustID())) {
                temp = Main.checkings.get(i);
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
                accountInfo.setValueAt(temp.getBalance(), 0, 2);

            }
        });
    }

}
