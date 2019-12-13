import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * This Java class uses Java Swing.
 *
 * This class is for the TellerScreen screen. This screen is accessible by selecting "Teller" at the initial login
 * page with the login drop down menu.
 *
 * The Teller screen has two text fields, one for an Account number, and one for a Customer SSN.
 * Only one of the two text fields are required to do a lookup of an account.
 * Upon entering data into the text field, the Teller is expected to press the "Lookup" button to lookup a
 * customer's account information.
 *
 * Pressing the "Lookup" button with valid customer information changes the GUI screen to the "TellerScreen".
 *
 * Pressing the "Cancel" button sends the GUI back to the initial "LoginScreen".
 *
 * Upon exiting the GUI, the system will write all the data in live memory to the database, and the program
 * will terminate.
 */
public class EmpAccountLookup {
    private JPanel panel1;
    private JTextField accountNumTextField;
    private JTextField SSNTextField;
    private JButton lookupButton;
    private JButton backButton;
    private JLabel custSSN;
    private JLabel ErrorMessage;
    private JMenuItem logout;
    private JMenuBar menu;

    public EmpAccountLookup() {
        menu = new JMenuBar();
        logout = new JMenuItem("Logout");
        menu.add(logout);
        final JFrame lookupScreen = new JFrame("PitA Bank");
        lookupScreen.setContentPane(panel1);
        lookupScreen.setPreferredSize(new Dimension(800, 600));
        lookupScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        lookupScreen.pack();
        lookupScreen.setVisible(true);
        lookupScreen.setJMenuBar(menu);
        lookupScreen.setLocationRelativeTo(null);
        lookupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (SSNTextField.getText().isEmpty() == false) {
                    String ssn = SSNTextField.getText();
                    for (int i = 0; i < Main.customers.size(); i++) {
                        if (Main.customers.get(i).getSsn().equals(ssn)) {
                            lookupScreen.dispose();
                            TellerScreen tellerScreen = new TellerScreen(ssn, "null");
                        }
                    }
                    ErrorMessage.setText("Customer SSN Not Found");
                }
                else if(accountNumTextField.getText().isEmpty() == false) {
                    String acct = accountNumTextField.getText();
                    for (int i = 0; i < Main.checkings.size(); i++) {
                        if (Main.checkings.get(i).getAccountID().equals(acct)) {
                            lookupScreen.dispose();
                            TellerScreen tellerScreen = new TellerScreen(Main.checkings.get(i).getCustID(),
                                    Main.checkings.get(i).getAccountID());
                        }
                    }
                    for (int i = 0; i < Main.savings.size(); i++) {
                        if (Main.savings.get(i).getAccountID().equals(acct)) {
                            lookupScreen.dispose();
                            TellerScreen tellerScreen = new TellerScreen(Main.savings.get(i).getCustID(),
                                    Main.savings.get(i).getAccountID());
                        }
                    }
                    for (int i = 0; i < Main.loans.size(); i++) {
                        if (Main.loans.get(i).getAccountID().equals(acct)) {
                            lookupScreen.dispose();
                            TellerScreen tellerScreen = new TellerScreen(Main.loans.get(i).getCustID(),
                                    Main.loans.get(i).getAccountID());
                        }
                    }
                }

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lookupScreen.dispose();
                if(!Main.manager) {
                    LoginScreen loginScreen = new LoginScreen();
                }
                else{
                    ManagerSelectFunction managerScreen = new ManagerSelectFunction();
                }
            }
        });
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lookupScreen.dispose();
                LoginScreen loginScreen = new LoginScreen();
            }
        });
    }
}