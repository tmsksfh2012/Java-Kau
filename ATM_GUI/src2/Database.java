package src2;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

// ArrayList가 두 개 필요 -> user, record
// Database에 사용자 정보(user)와 사용자 업무 사항(record)가 기억되어야함

public class Database extends JFrame{
    private Map<User,ArrayList<Record>> dataList; // User, Record 자료형의 Map으로 데이터베이스 구현
    private String name = "";
    private int password = 0;
    private int balance = 0;

    public Database() {
        dataList= new HashMap<>();
        setLayout(null);
        setTitle("DATABASE 입력");

        Label mainTxt1 = new Label("Hi.");
        Label mainTxt2 = new Label("Enter Information.");
        Label userName = new Label("name:");
        Label userPwd = new Label("password:");
        Label userAccountType = new Label("Account:");
        Label userBalance = new Label("balance:");

        mainTxt1.setBounds(10,10,80,20);
        mainTxt2.setBounds(10,30,180,20);
        userName.setBounds(20,60,60,20);
        userPwd.setBounds(20,80,60,20);
        userAccountType.setBounds(20,100,60,20);
        userBalance.setBounds(20,120,60,20);

        add(mainTxt1);
        add(mainTxt2);
        add(userName);
        add(userPwd);
        add(userAccountType);
        add(userBalance);

        TextField txtName = new TextField(5);
        TextField txtPwd =  new TextField(4);
        TextField txtBalance =  new TextField(10);

        txtName.setBounds(120, 60, 150, 20);
        txtPwd.setBounds(120, 80, 150, 20);
        txtBalance.setBounds(120, 120, 150, 20);

        add(txtName);
        add(txtPwd);
        add(txtBalance);

        // 계좌 종류 panel
        Panel accountPanel = new Panel(new FlowLayout(FlowLayout.LEFT));

        // 예금 종류 설정
        CheckboxGroup group = new CheckboxGroup();
        Checkbox td = new Checkbox("TD",group,false);
        Checkbox daw = new Checkbox("DAW",group,false);

        accountPanel.add(td);
        accountPanel.add(daw);
        accountPanel.setBounds(120, 92, 180, 30);

        Button button1 = new Button("ENTER");
        Button button2 = new Button("DONE");

        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    // 비밀번호는 4자리로 제한
                    if (txtPwd.getText().length() != 4) {
                        JOptionPane.showMessageDialog(null, "Password must be 4 characters", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        name += txtName.getText();
                        password = Integer.parseInt(txtPwd.getText());
                        balance = Integer.parseInt(txtBalance.getText());
                        txtName.setText("");
                        txtPwd.setText("");
                        txtBalance.setText("");

                        // 정기예금 계좌일 경우
                        if(td.getState()) {
                            User user = new User(false, name, password, balance);
                            name = "";
                            password = 0;
                            balance = 0;
                            if(isUser(user) == true) {
                                JOptionPane.showMessageDialog(null,"User already exists.\nPlease Enter again.","Error",JOptionPane.ERROR_MESSAGE);
                            }
                            else{
                                dataList.put(user, null);
                            }
                        }
                        // 입출금 계좌일 경우
                        else if(daw.getState()) {
                            User user = new User(true, name, password, balance);
                            name = "";
                            password = 0;
                            balance = 0;
                            if(isUser(user) == true) {
                                JOptionPane.showMessageDialog(null,"User already exists.\nPlease Enter again..","Error",JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                dataList.put(user, null);
                            }
                        }
                    }
                }catch(Exception ex) {
                    if(dataList.isEmpty() == true) {
                        JOptionPane.showMessageDialog(null,"데이터 베이스가 비어 있습니다.\n다시 입력해주십시오.","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"잘못 입력하셨습니다.\n다시 입력하십시오.","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        //db 입력 종료
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dataList.isEmpty() == true) {
                    JOptionPane.showMessageDialog(null, "There is no information", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    dispose();
                    new MainFrame();
                }
            }
        });

        Panel buttonPanel = new Panel();
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.setBounds(117, 140, 200, 200);

        add(accountPanel);
        add(buttonPanel);

        setSize(300,220);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    //record 기록용 함수
    public void InputData(User user, Record record) {
        ArrayList<Record>temp = new ArrayList<Record>();
        temp = dataList.get(user);

        // 기록이 없을 경우
        if (temp == null) {
            temp = new ArrayList<Record>();
            temp.add(record);
            dataList.put(user,temp);
        }
        else{
            temp.add(record);
            dataList.put(user, temp);
        }
    }

    public ArrayList<Record> getRecord(User user) {
        List<Entry<User,ArrayList<Record>>> keySetList = new ArrayList<Entry<User,ArrayList<Record>>>(dataList.entrySet());
        for(Entry<User, ArrayList<Record>> entry : keySetList) {
            if(entry.getKey() == user) {
                return entry.getValue();
            }
        }
        return null;
    }

    // print Record
    public void getRecord() {
        List<Entry<User,ArrayList<Record>>> keySetList = new ArrayList<Entry<User,ArrayList<Record>>>(dataList.entrySet());

        for(Entry<User, ArrayList<Record>> entry : keySetList) {
            if(entry.getKey() == MainFrame.user) {
                int count = 1; // 업무 순서 표기
                System.out.printf("%s work history\n",entry.getKey().getName());

                if(entry.getValue() == null) {
                    System.out.println("there is no history");
                    continue;
                }

                for(Record i : entry.getValue()) {
                    System.out.printf("%d.", count);
                    i.getService();
                    count++;
                }
            }
        }
    }

    public boolean isUser(User user) {
        List<Entry<User,ArrayList<Record>>> keySetList = new ArrayList<Entry<User,ArrayList<Record>>>(dataList.entrySet());
        for(Entry<User, ArrayList<Record>> entry : keySetList) {
            if(entry.getKey().getName().equals(user.getName()) &&
                    (entry.getKey().getPwd() == user.getPwd()) &&
                    (entry.getKey().kindOfAccount() == user.kindOfAccount())) {
                return true;
            }
        }
        return false;
    }

    // user 정보를 반환한다. 계좌 정보가 없을 시 null 반환
    public User getData(String name, int password) {
        List<Entry<User,ArrayList<Record>>> keySetList = new ArrayList<Entry<User,ArrayList<Record>>>(dataList.entrySet());

        for(Entry<User, ArrayList<Record>> entry : keySetList) {
            if(entry.getKey().getName().equals(name)&& entry.getKey().getPwd() == password) {
                return entry.getKey();
            }
        }
        return null;
    }

    //계좌이체 시 송금 계좌의 정보를 반환한다. 계좌 정보가 없을 시 null 반환
    public User getData(String name) {
        List<Entry<User,ArrayList<Record>>> keySetList = new ArrayList<Entry<User,ArrayList<Record>>>(dataList.entrySet());

        for(Entry<User, ArrayList<Record>> entry : keySetList) {
            if(entry.getKey().getName().equals(name)) {
                return entry.getKey();
            }
        }
        return null;
    }

}
