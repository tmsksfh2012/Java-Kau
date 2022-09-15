package GUI;
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

//ArrayList가 두 개 필요 -> user, record
//Database에 사용자 정보(user)와 사용자 업무 사항(record)가 기억되어야함

public class Database extends JFrame{
    private Map<User,ArrayList<Record>> dataList; //User, Record 자료형의 Map으로 데이터베이스 구현
    private String name = "";
    private int password = 0;
	private int balance = 0;

    public Database() {
    	dataList= new HashMap<>();
    	//while(true) {
			//Scanner input = new Scanner(System.in);
		this.addWindowListener(new WinEvent());
		setLayout(null);
		setTitle("DATA BASE 입력");
		
		Label mainTxt1 = new Label("안녕하세요.");
		Label mainTxt2 = new Label("사용자 정보를 입력하십시오.");
		Label userName = new Label("이름:");
		Label userPw = new Label("비밀번호:");
		Label userTp = new Label("계좌 유형:");
		Label userBal = new Label("잔액:");
		
		mainTxt1.setBounds(10,10,80,20);
		mainTxt2.setBounds(10,30,180,20);
		userName.setBounds(20,60,60,20);
		userPw.setBounds(20,80,60,20);
		userTp.setBounds(20,100,60,20);
		userBal.setBounds(20,120,60,20);
		
		add(mainTxt1);
		add(mainTxt2);
		add(userName);
		add(userPw);
		add(userTp);
		add(userBal);
		
		TextField txtName = new TextField(5);
		TextField txtPw =  new TextField(4);
		TextField txtBal =  new TextField(10);
		
		txtName.setBounds(120, 60, 150, 20);
		txtPw.setBounds(120, 80, 150, 20);
		txtBal.setBounds(120, 120, 150, 20);
		
		add(txtName);
		add(txtPw);
		add(txtBal);			
		
		Panel panTp = new Panel(new FlowLayout(FlowLayout.LEFT));
		
		CheckboxGroup group = new CheckboxGroup();
		Checkbox Td = new Checkbox("정기예금",group,false);
		Checkbox Daw = new Checkbox("입출금",group,false);
		
		panTp.add(Td);
		panTp.add(Daw);
		panTp.setBounds(120, 92, 180, 30);
		
		Button button = new Button("ENTER");
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					name += txtName.getText();
					password = Integer.parseInt(txtPw.getText());
					balance = Integer.parseInt(txtBal.getText());
					txtName.setText("");
					txtPw.setText("");
					txtBal.setText("");
					
					if(Td.getState()) {
						User user = new User(false, name, password, balance);
						name = "";
					    password = 0;
						balance = 0;
						if(isUser(user) == true) {
							JOptionPane.showMessageDialog(null,"이미 존재하는 사용자입니다.\n다시 입력해주십시오.","Error",JOptionPane.ERROR_MESSAGE);
						}
						else{
							dataList.put(user, null);
						}
					}
					else if(Daw.getState()) {
						User user = new User(true, name, password, balance);
						name = "";
					    password = 0;
						balance = 0;
						if(isUser(user) == true) {
							JOptionPane.showMessageDialog(null,"이미 존재하는 사용자입니다.\n다시 입력해주십시오.","Error",JOptionPane.ERROR_MESSAGE);
						}
						else {
							dataList.put(user, null);
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
		
		Panel paButton = new Panel();
		paButton.add(button);
		paButton.setBounds(143, 140, 200, 200);
		
		add(panTp);
		add(paButton);

		setSize(300,220);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    class WinEvent implements WindowListener{

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			new Main();
			
		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    public void InputData(User user, Record record) { //record 기록용 함수
    	ArrayList<Record>tmp = new ArrayList<Record>();
    	tmp = dataList.get(user);
    	if(tmp == null) {
    		tmp = new ArrayList<Record>();
    		tmp.add(record);
    		dataList.put(user,tmp);
    	}
    	else{
    		tmp.add(record);
    		dataList.put(user, tmp);
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
    public void getRecord() { // record 출력
    	List<Entry<User,ArrayList<Record>>> keySetList = new ArrayList<Entry<User,ArrayList<Record>>>(dataList.entrySet());
    	for(Entry<User, ArrayList<Record>> entry : keySetList) {
    		int count = 1; // 업무 순서 표기
    		System.out.printf("%s님의 업무 사항\n",entry.getKey().getName());
    		if(entry.getValue() == null) {
    			System.out.println("없음");
    			continue;
    		}
    		for(Record i : entry.getValue()) {
    			System.out.printf("%d.", count);
    			i.getServiceCon();
    			count++;
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
    public User getData(String name, int password) { //user 정보를 반환한다. 계좌 정보가 없을 시 null 반환
    	List<Entry<User,ArrayList<Record>>> keySetList = new ArrayList<Entry<User,ArrayList<Record>>>(dataList.entrySet());
    	for(Entry<User, ArrayList<Record>> entry : keySetList) {
    		if(entry.getKey().getName().equals(name)&& entry.getKey().getPwd() == password) {
    			return entry.getKey();
    		}
    	}
    	return null;
    }
    public User getData(String name) { //계좌이체 시 송금 계좌의 정보를 반환한다. 계좌 정보가 없을 시 null 반환
    	List<Entry<User,ArrayList<Record>>> keySetList = new ArrayList<Entry<User,ArrayList<Record>>>(dataList.entrySet());
    	for(Entry<User, ArrayList<Record>> entry : keySetList) {
    		if(entry.getKey().getName().equals(name)) {
    			return entry.getKey();
    		}
    	}
    	return null;
    }

}
