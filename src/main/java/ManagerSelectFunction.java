import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerSelectFunction {
    private JButton accountLookupButton;
    private JButton openAccountButton;
    private JButton interestRatesButton;
    private JPanel panel1;
    private JButton backButton;

    public ManagerSelectFunction() {
        final JFrame managerScreen = new JFrame("PitA Bank");
        managerScreen.setContentPane(panel1);
        managerScreen.setPreferredSize(new Dimension(800, 600));
        managerScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        managerScreen.pack();
        managerScreen.setVisible(true);
        accountLookupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managerScreen.dispose();
                EmpAccountLookup lookupScreen = new EmpAccountLookup();
            }
        });
        openAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        interestRatesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managerScreen.dispose();
                LoginScreen loginScreen = new LoginScreen();
            }
        });
    }
}