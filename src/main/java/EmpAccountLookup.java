import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmpAccountLookup {
    private JPanel panel1;
    private JTextField accountNumTextField;
    private JTextField SSNTextField;
    private JButton lookupButton;
    private JButton backButton;
    private JLabel custSSN;
    private JMenuItem logout;
    private JMenuBar menu;

    public EmpAccountLookup() {
        menu = new JMenuBar();
        logout = new JMenuItem("Logout");
        menu.add(logout);
        final JFrame lookupScreen = new JFrame("PitA Bank");
        lookupScreen.setContentPane(panel1);
        lookupScreen.setPreferredSize(new Dimension(800, 600));
        lookupScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        lookupScreen.pack();
        lookupScreen.setVisible(true);
        lookupScreen.setJMenuBar(menu);
        lookupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lookupScreen.dispose();
                TellerScreen tellerScreen = new TellerScreen();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lookupScreen.dispose();
                if(!Main.manager) {
                    LoginScreen loginScreen = new LoginScreen();
                }
                else{
                    ManagerSelectFunction managerScreen = new ManagerSelectFunction();
                }
            }
        });
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lookupScreen.dispose();
                LoginScreen loginScreen = new LoginScreen();
            }
        });
    }
}