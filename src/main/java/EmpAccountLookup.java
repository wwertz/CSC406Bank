import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        lookupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (SSNTextField.getText().isEmpty() == false) {
                    String ssn = SSNTextField.getText();
                    for (int i = 0; i < Main.customers.size(); i++) {
                        if (Main.customers.get(i).getSsn().equals(ssn)) {
                            lookupScreen.dispose();
                            TellerScreen tellerScreen = new TellerScreen(ssn, null);
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