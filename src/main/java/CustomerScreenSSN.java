import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * This Java class uses Java Swing.
 *
 * This class is for the CustomerScreenSSN screen.
 *
 * The user is prompted to enter their SSN into the password field, and then they press the Login button,
 * the GUI screen then changes to the "CustomerScreenAccounts" screen with that customers associated information.
 *
 * Pressing the "Back" button sends the GUI back to the initial "LoginScreen" screen.
 *
 * Upon exiting the GUI, the system will write all the data in live memory to the database, and the program
 * will terminate.
 *
 */
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
        cusScreen.setLocationRelativeTo(null);

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
                String ssn = SSNfield.getText();
                cusScreen.dispose();
                CustomerScreenAccounts accounts = new CustomerScreenAccounts(ssn);
            }
        });
    }
}
