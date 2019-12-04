import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
