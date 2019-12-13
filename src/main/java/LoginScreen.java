import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * This Java class uses Java Swing.
 *
 * This class is for the Login Page of the GUI. When the program is initially ran and the GUI opens, this is the
 * screen that will open, and is the root page to go back to.
 *
 * This GUI interface has a "login" button, and a panel with a selection of 4 user types: Customer,ATM,Teller,Manager.
 *
 * Upon selecting and logging into ATM, the screen changes to the "ATMLogin" screen.
 * Upon selecting and logging into Customer, the screen changes to the "CustomerScreenSSN" screen.
 * Upon selecting and logging into Teller, the screen changes to the "EmpAccountLoopUp" screen.
 * Upon selecting and logging into Manager, the screen changes to the "ManagerSelectFunction" screen.
 *
 * Upon exiting the GUI, the system will write all the data in live memory to the database, and the program
 * will terminate.
 *
 */
public class LoginScreen {
    private JPanel panel1;
    private JButton Login;
    private JComboBox Users;

    public LoginScreen() {

        final JFrame loginScreen = new JFrame("PitA Bank");
        loginScreen.setContentPane(panel1);
        loginScreen.setPreferredSize(new Dimension(800, 600));
        loginScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginScreen.pack();
        loginScreen.setVisible(true);
        loginScreen.setLocationRelativeTo(null);
        Users.addItem("Customer");
        Users.addItem("ATM");
        Users.addItem("Teller");
        Users.addItem("Manager");
        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = Users.getSelectedItem().toString();
                if (value.equals("Customer")){
                    Main.manager = false;
                    loginScreen.dispose();
                    CustomerScreenSSN cusScreen = new CustomerScreenSSN();
                }
                if (value.equals("Teller")){
                    Main.manager = false;
                    loginScreen.dispose();
                    EmpAccountLookup lookupScreen = new EmpAccountLookup();
                }
                if (value.equals("Manager")){
                    loginScreen.dispose();
                    Main.manager = true;
                    ManagerSelectFunction managerScreen = new ManagerSelectFunction();
                }
                if (value.equals("ATM")){
                    Main.manager = false;
                    loginScreen.dispose();
                    ATMLogin ATM = new ATMLogin();
                }
            }
        });
    }
}
