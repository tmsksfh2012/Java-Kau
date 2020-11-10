package src2;

// 데이터베이스 등록 후 MainFrame 실행 -> 정보 입력 후 -> 옳으면 -> AtmIndexFrame 으로 넘어감

import java.awt.Button;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainFrame extends JFrame {
    public static Database userData;
    public static User user;

    public MainFrame() {
        super("ATM 작동");
        setLayout(null);

        setSize(300,180);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        Label mainTxt1 = new Label("Hi.");
        Label mainTxt2 = new Label("Enter your name and password.");
        Label userName = new Label("Name:");
        Label userPwd = new Label("Password:");

        mainTxt1.setBounds(10,10,80,20);
        mainTxt2.setBounds(10,30,180,20);
        userName.setBounds(20,60,60,20);
        userPwd.setBounds(20,80,60,20);

        add(mainTxt1);
        add(mainTxt2);
        add(userName);
        add(userPwd);

        TextField txtName = new TextField(5);
        TextField txtPwd =  new TextField(4);

        txtName.setBounds(120, 50, 150, 20);
        txtPwd.setBounds(120, 70, 150, 20);

        add(txtName);
        add(txtPwd);

        Button button = new Button("ENTER");

        Panel buttonPanel = new Panel();
        buttonPanel.add(button);
        buttonPanel.setBounds(143, 100, 200, 200);
        add(buttonPanel);

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String name = txtName.getText();
                    int password = Integer.parseInt(txtPwd.getText());
                    user = userData.getData(name, password);

                    txtName.setText("");
                    txtPwd.setText("");

                    // 입출금 계좌일 경우
                    if (user.kindOfAccount() == true) {
                    	dispose();
                        new DawIndexFrame();
                    }
                    // 정기예금일 경우
                    else if (user.kindOfAccount() == false){
                    	dispose();
                        new TdIndexFrame();
                    }
                }catch(Exception ex) {
                    JOptionPane.showMessageDialog(null,"이름 혹은 비밀번호가 잘못되었습니다.\n다시 입력하십시오.","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    public static void main(String[] args) {
        userData = new Database();
    }
}
