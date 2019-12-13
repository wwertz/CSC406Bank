import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * This Java class uses Java Swing.
 *
 * This class is for the InterestScreen. This screen is accessible from the ManagerSelectScreen.
 *
 * This screen is the main administration screen for all of the interests rates for Savings Accounts, CD's,
 * Long and Short Term Loans, and for Credit Cards.
 *
 * For each of the interests there is an associated button that updates the default interest rates for new accounts,
 * a text field for what the interest rate will be change to, and a decimal number for the current interest rate.
 *
 * The "Accrue Interest" button executes the accrueInterest button for all accounts in the database that
 * have yet to be accrued.
 *
 * The "Process Transactions" button executes the functions that take transactions that are set to false,
 * and subtracts the owed money and sets them to True.
 *
 * Pressing the "Back" button sends the GUI back to the "ManagerSelectScreen".
 *
 * Upon exiting the GUI, the system will write all the data in live memory to the database, and the program
 * will terminate.
 */
public class InterestScreen {
    private JButton savingsButton;
    private JButton shortLoanButton;
    private JButton longLoanButton;
    private JButton CDsButton;
    private JTextField savingsField;
    private JTextField CDField;
    private JTextField shortLoanField;
    private JTextField longLoanField;
    private JButton backButton;
    private JPanel panel1;
    private JButton creditCardButton;
    private JTextField creditCardField;
    private JLabel csaving;
    private JLabel cCD;
    private JLabel cshort;
    private JLabel clong;
    private JLabel ccredit;
    private JButton accrueInterest;
    private JButton processTransactionButton;
    private JButton postBillsButton;
    private JMenuItem logout;
    private JMenuBar menu;

    public InterestScreen() {
        menu = new JMenuBar();
        logout = new JMenuItem("Logout");
        menu.add(logout);

        final JFrame InterestScreen = new JFrame("PitA Bank");
        InterestScreen.setContentPane(panel1);
        InterestScreen.setPreferredSize(new Dimension(800, 600));
        InterestScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        InterestScreen.pack();
        InterestScreen.setVisible(true);
        InterestScreen.setJMenuBar(menu);
        InterestScreen.setLocationRelativeTo(null);

        //get values to display
        csaving.setText(Double.toString(Main.savingsInterest));
        cshort.setText(Double.toString(Main.stlInterest));
        clong.setText(Double.toString(Main.ltlInterest));
        cCD.setText(Double.toString(Main.cdInterest));
        ccredit.setText(Double.toString(Main.ccInterest));

        //back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterestScreen.dispose();
                ManagerSelectFunction managerScreen = new ManagerSelectFunction();
            }
        });

        //logout button
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterestScreen.dispose();
                LoginScreen loginScreen = new LoginScreen();
            }
        });

        //saving interest
        savingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double temp = Double.parseDouble(savingsField.getText());
                if (savingsField != null && temp > 0) {
                    Main.savingsInterest = temp;
                    csaving.setText(Double.toString(Main.savingsInterest));
                    for(int i = 0; i < Main.savings.size(); i++){
                        Main.savings.get(i).setInterestRate(temp);
                    }
                }
            }
        });

        //short term loan interest
        shortLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double temp = Double.parseDouble(shortLoanField.getText());
                if (shortLoanField != null && temp > 0) {
                    Main.stlInterest = temp;
                    cshort.setText(Double.toString(Main.stlInterest));
                }
            }
        });

        //long term loan interest
        longLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double temp = Double.parseDouble(longLoanField.getText());
                if (longLoanField != null && temp > 0) {
                    Main.ltlInterest = temp;
                    clong.setText(Double.toString(Main.ltlInterest));
                }
            }
        });

        //cd interest
        CDsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double temp = Double.parseDouble(CDField.getText());
                if (CDField != null && temp > 0) {
                    Main.cdInterest = temp;
                    cCD.setText(Double.toString(Main.cdInterest));
                }
            }
        });

        //credit card interest
        creditCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double temp = Double.parseDouble(creditCardField.getText());
                if (creditCardField != null && temp > 0) {
                    Main.ccInterest = temp;
                    ccredit.setText(Double.toString(Main.ccInterest));
                }
            }
        });
        accrueInterest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < Main.savings.size(); i++) {
                    Main.savings.get(i).accrueInterest();
                }
                for (int i = 0; i < Main.loans.size(); i++) {
                    Main.loans.get(i).accrueInterest();
                }
                for (int i = 0; i < Main.checkings.size(); i++) {
                    Main.checkings.get(i).accrueInterest();
                }
            }
        });
        processTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for (int i = 0; i < Main.checks.size(); i++) {
                    Check checky = Main.checks.get(i);
                    if (checky.isProcessed().equals("false")) { // check was not processed
                        double amount = checky.getAmount();
                        String acID = checky.getCheckingAccID();
                        for (int j = 0; j < Main.checkings.size(); j++) {
                            if (acID.equals(Main.checkings.get(j).accountID)) {
                                    boolean success = Main.checkings.get(j).withdrawal(amount);
                                    System.out.println(success);
                                    if (success) {
                                        checky.setProcessed("true");
                                    } else {
                                        checky.setProcessed("canceled");
                                    }
                                }

                            }
                        }
                    }

                }

        });
        postBillsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < Main.loans.size(); i++){
                    Main.loans.get(i).postBill();
                }
            }
        });
    }
}
