import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMScreen {
    private JTable accountInfo;
    private JButton withdrawButton;
    private JTextField textField1;
    private JPanel panel1;
    private JButton backButton;
    private JMenuItem logout;
    private JMenuBar menu;

    public ATMScreen() {
        menu = new JMenuBar();
        logout = new JMenuItem("Logout");
        menu.add(logout);
        final JFrame ATMScreen = new JFrame("PitA Bank");
        ATMScreen.setContentPane(panel1);
        ATMScreen.setPreferredSize(new Dimension(800, 600));
        ATMScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ATMScreen.pack();
        ATMScreen.setVisible(true);
        ATMScreen.setJMenuBar(menu);
        accountInfo.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null,null,null}
                        },
                new String []{
                        "Account Type","Account ID", "Balance"
                }
                ) {public boolean isCellEditable(int row, int column){return false;}}
        );
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ATMScreen.dispose();
                ATMLogin ATM = new ATMLogin();
            }
        });
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ATMScreen.dispose();
                LoginScreen loginScreen = new LoginScreen();
            }
        });
    }

}
