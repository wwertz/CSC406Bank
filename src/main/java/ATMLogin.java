import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This Java class uses Java Swing.
 *
 * This class is for the ATM login. This screen is accessible by selecting "ATM" at the initial login page with the
 * login drop down menu.
 *
 * This screen has a text field for the customer's card number, and a password field for the PIN. Both are required.
 *
 * Upon entering data into both fields, the user is expected to press the Enter button, where the system checks
 * whether the data entered is valid. If it's invalid, the user is prompted to "Please retry".
 *
 * If the user successfully enters their information, the screen changes to the "ATMScreen.java" with the
 * customer's checking account information.
 *
 * Pressing the "Cancel" button sends the GUI back to the initial "LoginScreen".
 *
 * Upon exiting the GUI, the system will write all the data in live memory to the database, and the program
 * will terminate.
 */
public class ATMLogin {
    private JTextField cardNum;
    private JPasswordField PIN;
    private JButton loginButton;
    private JButton cancelButton;
    private JPanel panel1;
    private JLabel ErrorMessage;

    public ATMLogin() {
        final JFrame ATMLogin = new JFrame("PitA Bank");
        ATMLogin.setContentPane(panel1);
        ATMLogin.setPreferredSize(new Dimension(800, 600));
        ATMLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ATMLogin.pack();
        ATMLogin.setVisible(true);
        ATMLogin.setLocationRelativeTo(null);

        ErrorMessage.setText("Enter ATM card and PIN");

        //login to account
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String card = cardNum.getText();
                String pin = PIN.getText();
                for(int i=0; i<Main.customers.size(); i++){
                    if(Main.customers.get(i).getAtmCard().equals(card) && Main.customers.get(i).getPin().equals(pin)){
                        String ssn = Main.customers.get(i).getSsn();
                        ATMLogin.dispose();
                        ATMScreen ATM = new ATMScreen(ssn);
                    }

                }//end of for
                ErrorMessage.setText("Invalid account or PIN. Please retry.");
                ErrorMessage.setForeground(Color.red);
            }
        });


        //go back
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ATMLogin.dispose();
                LoginScreen loginScreen = new LoginScreen();
            }
        });
    }
}
