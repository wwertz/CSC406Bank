import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        Users.addItem("Customer");
        Users.addItem("Teller");
        Users.addItem("Manager");

        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = Users.getSelectedItem().toString();
                if (value.equals("Customer")){
                    loginScreen.dispose();
                    CustomerScreenSSN cusScreen = new CustomerScreenSSN();
                }
            }
        });
    }
}
