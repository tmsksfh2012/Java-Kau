package test;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Database user_data = new Database();
		while(true) {
			Scanner input = new Scanner(System.in);
			String Lname="";
			int Lpassword=0;
			System.out.printf("----------<ATM �۵�>----------\n�ȳ��ϼ���.\n�����͸� �Է��Ͻʽÿ�\n(�̸�,��й�ȣ)\n�Է�: ");
			input = new Scanner(input.next()).useDelimiter(",");
			
			Lname = input.next();
			Lpassword = input.nextInt();
			
			User Luser = user_data.getData(Lname, Lpassword);
			if(Luser == null) { //��ȯ���� null�� ��� -> �Է¹��� �̸� Ȥ�� ��й�ȣ�� ������ ���̽� ������ �ٸ� ���
				System.out.println("�̸� Ȥ�� ��й�ȣ�� �߸��Ǿ����ϴ�.");
				continue;
			}
			else {
				if (Luser.kindOfAccount() == true) {
					DawUI view = new DawUI(Luser,user_data);
				}
				else{
					TdUI view = new TdUI(Luser,user_data);
				}
			}
			user_data.getRecord();
			System.out.println("---------<ATM ��� �Ϸ�>---------");
		}
	}
}
