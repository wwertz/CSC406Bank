import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                if(savingsField != null && temp>0){
                    Main.savingsInterest = temp;
                    csaving.setText(Double.toString(Main.savingsInterest));
                }
            }
        });

        //short term loan interest
        shortLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double temp = Double.parseDouble(shortLoanField.getText());
                if(shortLoanField != null && temp>0) {
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
                if(longLoanField != null && temp>0) {
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
                if(CDField != null && temp>0) {
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
                if(creditCardField != null && temp>0) {
                    Main.ccInterest = temp;
                    ccredit.setText(Double.toString(Main.ccInterest));
                }
            }
        });
        accrueInterest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < Main.savings.size(); i++){
                    Main.savings.get(i).accrueInterest();
                }
                for (int i = 0; i < Main.loans.size(); i++){
                    Main.loans.get(i).accrueInterest();
                }
            }
        });
    }
}
