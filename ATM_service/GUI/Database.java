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

//ArrayList�� �� �� �ʿ� -> user, record
//Database�� ����� ����(user)�� ����� ���� ����(record)�� ���Ǿ����

public class Database extends JFrame{
    private Map<User,ArrayList<Record>> dataList; //User, Record �ڷ����� Map���� �����ͺ��̽� ����
    private String name = "";
    private int password = 0;
	private int balance = 0;

    public Database() {
    	dataList= new HashMap<>();
    	//while(true) {
			//Scanner input = new Scanner(System.in);
		this.addWindowListener(new WinEvent());
		setLayout(null);
		setTitle("DATA BASE �Է�");
		
		Label mainTxt1 = new Label("�ȳ��ϼ���.");
		Label mainTxt2 = new Label("����� ������ �Է��Ͻʽÿ�.");
		Label userName = new Label("�̸�:");
		Label userPw = new Label("��й�ȣ:");
		Label userTp = new Label("���� ����:");
		Label userBal = new Label("�ܾ�:");
		
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
		Checkbox Td = new Checkbox("���⿹��",group,false);
		Checkbox Daw = new Checkbox("�����",group,false);
		
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
							JOptionPane.showMessageDialog(null,"�̹� �����ϴ� ������Դϴ�.\n�ٽ� �Է����ֽʽÿ�.","Error",JOptionPane.ERROR_MESSAGE);
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
							JOptionPane.showMessageDialog(null,"�̹� �����ϴ� ������Դϴ�.\n�ٽ� �Է����ֽʽÿ�.","Error",JOptionPane.ERROR_MESSAGE);
						}
						else {
							dataList.put(user, null);
						}
					}
				}catch(Exception ex) {
					if(dataList.isEmpty() == true) {
						JOptionPane.showMessageDialog(null,"������ ���̽��� ��� �ֽ��ϴ�.\n�ٽ� �Է����ֽʽÿ�.","Error",JOptionPane.ERROR_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null,"�߸� �Է��ϼ̽��ϴ�.\n�ٽ� �Է��Ͻʽÿ�.","Error",JOptionPane.ERROR_MESSAGE);
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
    public void InputData(User user, Record record) { //record ��Ͽ� �Լ�
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
    public void getRecord() { // record ���
    	List<Entry<User,ArrayList<Record>>> keySetList = new ArrayList<Entry<User,ArrayList<Record>>>(dataList.entrySet());
    	for(Entry<User, ArrayList<Record>> entry : keySetList) {
    		int count = 1; // ���� ���� ǥ��
    		System.out.printf("%s���� ���� ����\n",entry.getKey().getName());
    		if(entry.getValue() == null) {
    			System.out.println("����");
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
    public User getData(String name, int password) { //user ������ ��ȯ�Ѵ�. ���� ������ ���� �� null ��ȯ
    	List<Entry<User,ArrayList<Record>>> keySetList = new ArrayList<Entry<User,ArrayList<Record>>>(dataList.entrySet());
    	for(Entry<User, ArrayList<Record>> entry : keySetList) {
    		if(entry.getKey().getName().equals(name)&& entry.getKey().getPwd() == password) {
    			return entry.getKey();
    		}
    	}
    	return null;
    }
    public User getData(String name) { //������ü �� �۱� ������ ������ ��ȯ�Ѵ�. ���� ������ ���� �� null ��ȯ
    	List<Entry<User,ArrayList<Record>>> keySetList = new ArrayList<Entry<User,ArrayList<Record>>>(dataList.entrySet());
    	for(Entry<User, ArrayList<Record>> entry : keySetList) {
    		if(entry.getKey().getName().equals(name)) {
    			return entry.getKey();
    		}
    	}
    	return null;
    }

}
