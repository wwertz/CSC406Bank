import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerScreenAccounts {
    private JComboBox Accounts;
    private JButton backButton;
    private JPanel panel1;
    private JButton transferButton;
    private JComboBox destAccountBox;
    private JComboBox sourceAccountBox;
    private JLabel From;
    private JLabel To;
    private JTable accountInfo;
    private JTextField Amount;

    public CustomerScreenAccounts() {
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
                CustomerScreenSSN screenSSN = new CustomerScreenSSN();
            }
        });
        accountInfo.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                {null,null,null}
                },
                new String []{
                        "Account Type","Account ID", "Balance"
                 }
                ) {public boolean isCellEditable(int row, int column){return false;}}
        );
    }
}
