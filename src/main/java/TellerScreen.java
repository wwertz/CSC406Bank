import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TellerScreen {
    private JButton backButton;
    private JButton selectButton;
    private JComboBox comboBox1;
    private JLabel customerName;
    private JButton creditButton;
    private JTextField creditAmount;
    private JButton debitButton;
    private JTextField debitAmount;
    private JPanel panel1;
    private JButton transferButton;
    private JComboBox comboBox2;
    private JComboBox toAccount;
    private JTextArea TransferAmount;
    private JTable accountInfo;
    private JButton closeAccountButton;

    public TellerScreen() {
        final JFrame telScreen = new JFrame("PitA Bank");
        telScreen.setContentPane(panel1);
        telScreen.setPreferredSize(new Dimension(800, 600));
        telScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telScreen.pack();
        telScreen.setVisible(true);
        if (!Main.manager){closeAccountButton.setVisible(false);}
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telScreen.dispose();
                EmpAccountLookup lookupScreen = new EmpAccountLookup();
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