import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerScreenAccounts {
    private JTextField chooseAnAccountToTextField;
    private JComboBox Accounts;
    private JButton backButton;
    private JPanel panel1;

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
    }
}
