import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccountAndCust {
    private JButton lookupSSNButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JComboBox accountType;
    private JTextField textField8;
    private JTextField textField9;
    private JButton backButton;
    private JButton createButton;
    private JPanel panel1;
    private JTextField backupAccount;
    private JTextField ATM;
    private JMenuItem logout;
    private JMenuBar menu;
    public CreateAccountAndCust() {
        menu = new JMenuBar();
        logout = new JMenuItem("Logout");
        menu.add(logout);
        accountType.addItem("Standard Checking");
        accountType.addItem("Premium Checking");
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
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAcctCust.dispose();
                ManagerSelectFunction managerScreen = new ManagerSelectFunction();
            }
        });
    }
}
