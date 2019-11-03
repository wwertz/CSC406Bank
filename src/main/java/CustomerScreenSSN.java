import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerScreenSSN {
    private JPanel panel1;
    private JPasswordField SSNfield;
    private JButton backButton;
    private JButton loginButton;

    public CustomerScreenSSN(){
        final JFrame cusScreen = new JFrame("PitA Bank");
        cusScreen.setContentPane(panel1);
        cusScreen.setPreferredSize(new Dimension(800, 600));
        cusScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cusScreen.pack();
        cusScreen.setVisible(true);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cusScreen.dispose();
                LoginScreen loginScreen = new LoginScreen();
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cusScreen.dispose();
                CustomerScreenAccounts accounts = new CustomerScreenAccounts();
            }
        });
    }
}
