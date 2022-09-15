package GUI;

import java.awt.Button;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main extends JFrame {
	static Database user_data;
	
	public Main() {
		JFrame MAIN = new JFrame();
		MAIN.setLayout(null);
		MAIN.setTitle("ATM �۵�");
		
		MAIN.setSize(300,180);
		MAIN.setResizable(false);
		MAIN.setVisible(true);
		MAIN.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Label mainTxt1 = new Label("�ȳ��ϼ���.");
		Label mainTxt2 = new Label("�̸��� ��й�ȣ�� �Է��Ͻʽÿ�.");
		Label userName = new Label("�̸�:");
		Label userPw = new Label("��й�ȣ:");
		
		mainTxt1.setBounds(10,10,80,20);
		mainTxt2.setBounds(10,30,180,20);
		userName.setBounds(20,60,60,20);
		userPw.setBounds(20,80,60,20);
		
		MAIN.add(mainTxt1);
		MAIN.add(mainTxt2);
		MAIN.add(userName);
		MAIN.add(userPw);
		
		TextField txtName = new TextField(5);
		TextField txtPw =  new TextField(4);
		
		txtName.setBounds(120, 50, 150, 20);
		txtPw.setBounds(120, 70, 150, 20);
		
		MAIN.add(txtName);
		MAIN.add(txtPw);

		Button button = new Button("ENTER");
		
		Panel paButton = new Panel();
		paButton.add(button);
		paButton.setBounds(143, 100, 200, 200);
		MAIN.add(paButton);
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					String Lname = txtName.getText();
					int Lpassword = Integer.parseInt(txtPw.getText());
					User Luser = user_data.getData(Lname, Lpassword);
					
					txtName.setText("");
					txtPw.setText("");
					
					if (Luser.kindOfAccount() == true) {
						DawUI view = new DawUI(Luser,user_data);
					}
					else if (Luser.kindOfAccount() == false){
						TdUI view = new TdUI(Luser,user_data);
					}
					user_data.getRecord(); //GUI�� �����ؾ���
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null,"�̸� Ȥ�� ��й�ȣ�� �߸��Ǿ����ϴ�.\n�ٽ� �Է��Ͻʽÿ�.","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	public static void main(String[] args) {
		user_data = new Database();		
	}
}
