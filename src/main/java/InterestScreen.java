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
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterestScreen.dispose();
                ManagerSelectFunction managerScreen = new ManagerSelectFunction();
            }
        });
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterestScreen.dispose();
                LoginScreen loginScreen = new LoginScreen();
            }
        });
    }
}
