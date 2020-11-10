package src2;

// 출금 화면

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WithdrawFrame extends JFrame implements ActionListener {
    // 숫자 확인창
    JTextField T1;
    int money = 0;
    String stringInput = "";
    JButton Button[] = new JButton[16];
    // 만원 단위로
    JButton tenThousand = new JButton("만원");

    public boolean canWithdraw(int amount) {
        int balance = MainFrame.user.getUserBalance();
        // 백원 단위 존재하기 때문에 입금 불가
        if (amount % 1000 != 0) {
            return false;
        }
        else {
            if (amount > balance) {
                return false;
            }
            return true;
        }
    }

    public int getMoney() {
        return money;
    }

    public void errorMessage() {
        String error = "출금 금액을 다시 입력해주세요";
        JOptionPane.showMessageDialog(null, error, "출금 오류", JOptionPane.WARNING_MESSAGE);
        stringInput = "";
        money = 0;
        T1.setText("출금 금액을 입력하세요");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();

        if (input.equals("<-")) {
            // 처음 숫자에서 지울 때
            if (stringInput.length() == 1 || stringInput.length() == 0) {
                stringInput = "0";
                money = Integer.parseInt(stringInput);
                T1.setText(stringInput);
                stringInput = "";
            }
            else {
                stringInput = stringInput.substring(0, stringInput.length() - 1);
                money = Integer.parseInt(stringInput);
                T1.setText(stringInput);
            }
        }
        else if (input.equals("Enter")) {
            // 출금 금액 확인 메소드 (만원 단위 이상인지)
            if (money > 0 && canWithdraw(money)) {
                // 비밀번호 확인 프레임으로 넘어가기
                dispose();
                DepositFrame.amount = getMoney();
                Record.record.recordService(2, DepositFrame.amount);
                MainFrame.userData.InputData(MainFrame.user, Record.record);
                new PasswordFrame(false);
            }
            // 출금 불가능
            else {
                errorMessage();
            }
        }
        // 만원 단위로 변경
        else if (input.equals("만원")) {
            if (stringInput.equals("0")) {
                errorMessage();
            }
            else if (stringInput.length() == 0) {
                stringInput = "10000";
                T1.setText(stringInput);
                money = Integer.parseInt(stringInput);
            }
            else {
                stringInput = stringInput + "0000";
                T1.setText(stringInput);
                money = Integer.parseInt(stringInput);
            }
        }
        // 숫자 입력
        else {
            stringInput = stringInput + e.getActionCommand();
            T1.setText(stringInput);
            money = Integer.parseInt(stringInput);

            // 0만 입력하는 경우
            if (stringInput.equals("0")) {
                // 에러 메세지 출력
                errorMessage();
            }
        }
    }

    public WithdrawFrame() {
        super("출금");
        super.setVisible(true);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        T1 = new JTextField("Enter Withdraw amount", 30);
        T1.setEditable(false);

        JPanel P1 = new JPanel();
        JPanel P2 = new JPanel();
        JPanel P3 = new JPanel();
        P1.add(T1);
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

        tenThousand.setPreferredSize(new Dimension(90, 70));
        tenThousand.addActionListener(this);
        tenThousand.setBackground(new Color(0, 160, 240));
        tenThousand.setFont(new Font("굴림", Font.BOLD, 25));
        tenThousand.setForeground(Color.WHITE);
        P3.add(tenThousand);

        add(P1, BorderLayout.NORTH);
        add(P2, BorderLayout.CENTER);
        add(P3, BorderLayout.EAST);
        setVisible(true);
    }

}
