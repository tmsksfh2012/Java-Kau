package test;
// ���⿹�� ui

import java.util.ArrayList;
import java.util.Scanner;

public class TdUI {
	// ����� ������
	User user;
	// ����� ���� ���� ����
	Record record;
    // �Աݱݾ�
    int amount;
    // ��й�ȣ
    int pwd;
    
	public TdUI(User Luser, Database user_data) {
		user = Luser;
		record = new Record();
	
	    // initial state
	    System.out.printf("%s�� �ȳ��ϼ���\n", user.getName());
	
	    Scanner input = new Scanner(System.in);
	
	    // �Ա� ȭ��
	    System.out.printf("\n<----�Ա�---->");
	    System.out.printf("\n�Ա� �ݾ��� �Է����ּ���: ");
	    amount = input.nextInt();
	    // !�Ա� ȭ��
	
	    // ��й�ȣ Ȯ��
	    System.out.printf("\n��й�ȣ�� �Է����ּ���: ");
	    pwd = input.nextInt();
	
	    // ��й�ȣ ����ġ
	    if (pwd != user.getPwd()) {
	        System.out.println("��й�ȣ ����ġ");
	        System.out.printf("��й�ȣ�� �ٽ� �Է����ּ���: ");
	        pwd = input.nextInt();
	    }
	    // !��й�ȣ ����ġ
	
	    // ��й�ȣ ��ġ
	    else {
	        // �Ա� ����
	        System.out.printf("%d���� ���⿹�� ���¿� �Ա��մϴ�. \n\n", amount);
	        user.deposit(amount);
	        record.recordingCon(1,amount);
	        System.out.printf("%s�� �����մϴ�.\n", user.getName());
	        // !�Ա� ����
	
	        // ���
	        user_data.InputData(user, record); //
	        // !���
	
	    }
	}
}