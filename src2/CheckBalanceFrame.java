package src2;

import javax.swing.*;

public class CheckBalanceFrame {
    private int userBalance;

    public CheckBalanceFrame() {
        userBalance = MainFrame.user.getUserBalance();
        String chkBalanceMsg = "Your balance is " + Integer.toString(userBalance);

        JOptionPane.showMessageDialog(null, chkBalanceMsg, "Balance", JOptionPane.INFORMATION_MESSAGE);
        new MainFrame();
    }
}
