import javax.swing.*;
import java.awt.*;

public class ATMScreen {
    private JTable accountInfo;
    private JButton withdrawButton;
    private JTextField textField1;
    private JPanel panel1;

    public ATMScreen() {
        final JFrame ATMScreen = new JFrame("PitA Bank");
        ATMScreen.setContentPane(panel1);
        ATMScreen.setPreferredSize(new Dimension(800, 600));
        ATMScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ATMScreen.pack();
        ATMScreen.setVisible(true);
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
