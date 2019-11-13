import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMLogin {
    private JTextField cardNum;
    private JPasswordField PIN;
    private JButton loginButton;
    private JButton cancelButton;
    private JPanel panel1;

    public ATMLogin() {
        final JFrame ATMLogin = new JFrame("PitA Bank");
        ATMLogin.setContentPane(panel1);
        ATMLogin.setPreferredSize(new Dimension(800, 600));
        ATMLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ATMLogin.pack();
        ATMLogin.setVisible(true);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ATMLogin.dispose();
                ATMScreen ATM = new ATMScreen();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ATMLogin.dispose();
                LoginScreen loginScreen = new LoginScreen();
            }
        });
    }
}
