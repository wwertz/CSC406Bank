import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * This Java class uses Java Swing.
 *
 * This class is for the ManagerSelectFunction of the GUI. This screen is accessible by selecting "Manager"
 * at the initial login page
 *
 * This GUI interface has three buttons for Manager level interfaces; Account Lookup, Open Account,
 * and Global Functions
 *
 * Upon selecting "Account Lookup", the screen changes to the "EmpAccountLookup" screen.
 * Upon selecting "Open Account", the screen changes to the "CreateAccountAndCust" screen.
 * Upon selecting "Global Functions", the screen changes to the InterestScreen" screen.
 *
 * Pressing the "Back" button sends the GUI back to the initial "LoginScreen".
 *
 * Upon exiting the GUI, the system will write all the data in live memory to the database, and the program
 * will terminate.
 *
 */
public class ManagerSelectFunction {
    private JButton accountLookupButton;
    private JButton openAccountButton;
    private JButton globalFunctionsButton;
    private JPanel panel1;
    private JButton backButton;
    private JMenuItem logout;
    private JMenuBar menu;

    public ManagerSelectFunction() {
        menu = new JMenuBar();
        logout = new JMenuItem("Logout");
        menu.add(logout);
        final JFrame managerScreen = new JFrame("PitA Bank");
        managerScreen.setContentPane(panel1);
        managerScreen.setPreferredSize(new Dimension(800, 600));
        managerScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        managerScreen.pack();
        managerScreen.setVisible(true);
        managerScreen.setJMenuBar(menu);
        managerScreen.setLocationRelativeTo(null);

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
        globalFunctionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managerScreen.dispose();
                InterestScreen interestScreen = new InterestScreen();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managerScreen.dispose();
                LoginScreen loginScreen = new LoginScreen();
            }
        });
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managerScreen.dispose();
                LoginScreen loginScreen = new LoginScreen();
            }
        });
        openAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managerScreen.dispose();
                CreateAccountAndCust createAccountAndCust = new CreateAccountAndCust();
            }
        });
    }
}
