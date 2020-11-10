package src2;

// 비밀번호 확인 ui

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class PasswordFrame extends JFrame implements ActionListener {
    // 입금인지 출금인지
    boolean isDeposit;
    // 숫자 입력창
    JPasswordField pwd;
    int userPwd = 0;
    String pwdInput = "";
    JButton Button[] = new JButton[16];

    // 오류 메세지들
    String tooManyPwd = "비밀번호는 4자리까지 입력 가능합니다.\n다시 입력해주세요";
    String tooLittlePwd = "비밀번호를 4자리까지 입력해주세요";
    String wrongPwd = "비밀번호가 옳지 않습니다\n비밀번호를 다시 입력해주세요";

    // 비밀번호 에러시
    public void errorMessage(String error){
        JOptionPane.showMessageDialog(null, error,"비밀번호 오류",JOptionPane.WARNING_MESSAGE);
        pwdInput = "";
        userPwd = 0;
        pwd.setText(pwdInput);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();

        if (input.equals("<-")) {
            // 처음 숫자에서 지울 때
            if (pwdInput.length() == 1 || pwdInput.length() == 0) {
                pwdInput = "";
                userPwd = 0;
                pwd.setText(pwdInput);
            }
            else {
                pwdInput = pwdInput.substring(0, pwdInput.length() - 1);
                userPwd = Integer.parseInt(pwdInput);
                pwd.setText(pwdInput);
            }
        }
        else if (input.equals("Enter")) {
            // 비밀번호 확인 메소드
            if (MainFrame.user.getPwd() == Integer.parseInt(pwdInput)) {
                if (isDeposit) {
                    MainFrame.user.deposit(DepositFrame.amount);
                }
                else {
                    MainFrame.user.withdraw(DepositFrame.amount);
                }
                dispose();
                new SpecificationMessage();
            }
            else if (pwdInput.length() < 4) {
                errorMessage(tooLittlePwd);
            }
            // 비밀번호 불일치
            else {
                errorMessage(wrongPwd);
            }
        }
        // 숫자 입력
        else {
            // 비밀번호 4자리까지
            if (pwdInput.length() == 4) {
                errorMessage(tooManyPwd);
            }
            else {
                pwdInput = pwdInput + e.getActionCommand();
                pwd.setText(pwdInput);
                userPwd = Integer.parseInt(pwdInput);
            }
        }
    }

    public PasswordFrame(boolean depositOrWithDraw) {
        super("비밀번호 입력");
        super.setVisible(true);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);

        isDeposit = depositOrWithDraw;

        pwd = new JPasswordField(30);
        pwd.setEditable(false);
        JPanel P1 = new JPanel();
        JPanel P2 = new JPanel();
        P1.add(pwd);
        P2.setLayout(new GridLayout(4, 3, 10, 10));
        String btnValue[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "<-", "0", "Enter"};

        for (int i = 0; i < 12; i++) {
            Button[i] = new JButton(btnValue[i]);
            P2.add(Button[i]);
            Button[i].addActionListener(this);
            Button[i].setBackground(new Color(175, 175, 175));
            Button[i].setFont(new Font("굴림", Font.BOLD, 30));
            Button[i].setForeground(Color.WHITE);

            if (i == 9 || i == 11) {
                Button[i].setBackground(new Color(0, 160, 240));
                Button[i].setFont(new Font("굴림", Font.BOLD, 20));
                Button[i].setForeground(Color.WHITE);
            }
        }

        add(P1, BorderLayout.NORTH);
        add(P2, BorderLayout.CENTER);
        setVisible(true);
    }

}
