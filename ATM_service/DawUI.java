package test;
// ����� UI

import java.util.ArrayList;
import java.util.Scanner;

public class DawUI {
	// ����� ������
	User user;
	// ����� ���� ���� ����
	Record record;
    // �ŷ��ݾ�
    int amount;
    // ���� �̿� ����
    char otherService = 'Y';
    // ��ǥ ��� ����
    char specification;
    // ATM
    Atm atm = new Atm();
    // �������� ����
    int dollar = 0;
    // ������ü�� ����
    String bankAccount;
    // ��й�ȣ
    int pwd;
	
	public DawUI(User user, Database user_data) {
		user = user;
		record = new Record();
	    // initial state
	    System.out.printf("%s�� �ȳ��ϼ���\n", user.getName());
	
	    while (otherService == 'Y') {
	        System.out.println("\n1. �Ա�");
	        System.out.println("2. ���");
	        System.out.println("3. ������ü");
	        System.out.println("4. �ܾ� ��ȸ");
	        System.out.printf("�����Ͻ� ������ �������ּ���: ");
	        Scanner input = new Scanner(System.in);
	        int service = input.nextInt();
	
	        // �Ա�
	        if (service == 1) {
	            // �Ա� ȭ��
	            System.out.printf("\n<----�Ա�---->");
	            System.out.printf("\n�Ա� �ݾ��� �Է����ּ���: ");
	            amount = input.nextInt();
	            // !�Ա� ȭ��
	
	            // �Ա� �ݾ� ���� (õ�� ��������)
	            while (amount % 1000 != 0) {
	                System.out.println("�Ա��� õ���� ���� �̻���� �����մϴ�");
	                System.out.printf("\n�Ա� �ݾ��� �Է����ּ���: ");
	                amount = input.nextInt();
	            }
	
	            // ��й�ȣ Ȯ��
	            System.out.printf("\n��й�ȣ�� �Է����ּ���: ");
	            pwd = input.nextInt();
	
	            // ��й�ȣ ����ġ
	            if (pwd != user.getPwd()) {
	                System.out.println("��й�ȣ ����ġ");
	                System.out.printf("\n�ٸ� ���񽺸� �̿��Ͻðڽ��ϱ�? (Y/N): ");
	                otherService = input.next().charAt(0);
	            }
	            // !��й�ȣ ����ġ
	
	            // ��й�ȣ ��ġ
	            else {
	                // �Ա� ����
	                System.out.printf("%d���� �Ա��մϴ�. \n\n", amount);
	                
	                user.deposit(amount); //
	                
	                atm.plusMoney(amount);
	                // !�Ա� ����
	
	                // ���
	                record.recordingCon(1, amount);	
	                // !���
	
	                // ��ǥ ���
	                System.out.printf("��ǥ�� ����Ͻðڽ��ϱ�? (Y/N): ");
	                specification = input.next().charAt(0);
	
	                if (specification == 'Y') {
	                    System.out.printf("\n�ŷ� �ݾ�: %d\n", amount);
	                    System.out.printf("�ܾ�: %d\n", user.getUserBalance());
	                    System.out.printf("\n�ٸ� ���񽺸� �̿��Ͻðڽ��ϱ�? (Y/N): ");
	                    otherService = input.next().charAt(0);
	                }
	                // ! ��ǥ ���
	
	                // �ٸ� ���� ����
	                else {
	                    System.out.printf("\n�ٸ� ���񽺸� �̿��Ͻðڽ��ϱ�? (Y/N): ");
	                    otherService = input.next().charAt(0);
	                }
	                // !�ٸ� ���� ����
	            }
	        }
	        // !�Ա�
	
	        // ���
	        else if (service == 2) {
	            // ��� ȭ��
	            System.out.printf("\n<----���---->");
	            System.out.printf("\n��� �ݾ��� �Է����ּ���: ");
	            amount = input.nextInt();
	            // !��� ȭ��
	
	            // ��� �ݾ� ���� (10000��, 50000��)
	            while (amount % 10000 != 0 && amount % 50000 != 0) {
	                System.out.println("��� �ݾ��� 10000���Ǻ��� �����մϴ�");
	                System.out.printf("\n��� �ݾ��� �Է����ּ���: ");
	                amount = input.nextInt();
	            }
	            // !��� �ݾ� ����
	
	            // �ܾ� ������
	            if (amount > user.getUserBalance()) {
	                System.out.printf("�ܾ��� �����մϴ�\n������ ���� �ܾ��� %d���Դϴ�\n", user.getUserBalance());
	                System.out.printf("�ٸ� ���񽺸� �̿��Ͻðڽ��ϱ�? (Y/N): ");
	                otherService = input.next().charAt(0);
	                continue;
	            }
	            // !�ܾ� ������
	
	            // ��� ����
	            // �������� ����
	            if (amount / 50000 > 0) {
	                System.out.printf("\n��� �ݾ�: %d\n", amount);
	                System.out.printf("�������� �Է�: ");
	                dollar = input.nextInt();
	
	                // ����ڰ� �Է��� ���������� �� ū ���
	                while (dollar > amount / 50000) {
	                    System.out.printf("\n��� �ݾ׺��� ���������� Ů�ϴ�\n�ٽ� �Է����ּ���: ");
	                    dollar = input.nextInt();
	                }
	            }
	            // !�������� ����
	
	            // atm ���簡�� �������� Ȯ��
	            if (dollar > atm.getFifty() || (amount - 50000 * dollar) / 10000 > atm.getTen()) {
	                System.out.printf("�˼��մϴ� atm�� ���� �����մϴ�.\n");
	
	                System.out.printf("�ٸ� ���񽺸� �̿��Ͻðڽ��ϱ�? (Y/N): ");
	                otherService = input.next().charAt(0);
	            }
	            // !atm ���簡�� �������� Ȯ��
	
	            else {
	                // ��й�ȣ Ȯ��
	                System.out.printf("\n��й�ȣ�� �Է����ּ���: ");
	                pwd = input.nextInt();
	
	                // ��й�ȣ ����ġ
	                if (pwd != user.getPwd()) {
	                    System.out.println("��й�ȣ ����ġ");
	                    System.out.printf("\n�ٸ� ���񽺸� �̿��Ͻðڽ��ϱ�? (Y/N): ");
	                    otherService = input.next().charAt(0);
	                }
	                // !��й�ȣ ����ġ
	
	                // ��й�ȣ ��ġ
	                else {
	                    // ��� ����
	                    System.out.printf("\n%d���� ����մϴ�. \n\n", amount);
	                    
	                    user.withdraw(amount); //
	                    
	                    atm.minusMoney(amount);
	                    // !��� ����
	
	                    // ���
	                    record.recordingCon(2, amount);
	                    // !���
	
	                    // ��ǥ ���
	                    System.out.printf("��ǥ�� ����Ͻðڽ��ϱ�? (Y/N): ");
	                    specification = input.next().charAt(0);
	
	                    if (specification == 'Y') {
	                        System.out.printf("\n�ŷ� �ݾ�: %d\n", amount);
	                        System.out.printf("�ܾ�: %d\n", user.getUserBalance());
	                        System.out.printf("\n�ٸ� ���񽺸� �̿��Ͻðڽ��ϱ�? (Y/N): ");
	                        otherService = input.next().charAt(0);
	                    }
	                    // ! ��ǥ ���
	
	                    // �ٸ� ���� ����
	                    else {
	                        System.out.printf("\n�ٸ� ���񽺸� �̿��Ͻðڽ��ϱ�? (Y/N): ");
	                        otherService = input.next().charAt(0);
	                    }
	                    // !�ٸ� ���� ����
	                }
	            }
	        }
	        // !���
	
	        // ������ü
	        else if (service == 3) {
	            // ������ü ȭ��
	            System.out.printf("\n<----������ü---->");
		        System.out.printf("\n�۱� ���¸� �Է����ּ���: ");
		        bankAccount = input.next();
	            User dest_user = user_data.getData(bankAccount);
	            if(dest_user == null) {
	            	System.out.printf("�۱� ���°� �߸��Ǿ����ϴ�.\n");
	            	System.out.printf("�ٸ� ���񽺸� �̿��Ͻðڽ��ϱ�? (Y/N): ");
	                otherService = input.next().charAt(0);
	                continue;
	            }
	            
	            System.out.printf("�۱� �ݾ��� �Է����ּ���: ");
	            amount = input.nextInt();
	            // !������ü ȭ��
	
	            // �ܾ� ������
	            if (amount > user.getUserBalance()) {
	                System.out.printf("�ܾ��� �����մϴ�\n������ ���� �ܾ��� %d���Դϴ�\n", user.getUserBalance());
	                System.out.printf("�ٸ� ���񽺸� �̿��Ͻðڽ��ϱ�? (Y/N): ");
	                otherService = input.next().charAt(0);
	                continue;
	            }
	            // !�ܾ� ������
	
	            // �۱� ����
	            else {
	                // ��й�ȣ Ȯ��
	                System.out.printf("\n��й�ȣ�� �Է����ּ���: ");
	                pwd = input.nextInt();
	
	                // ��й�ȣ ����ġ
	                if (pwd != user.getPwd()) {
	                    System.out.println("\n��й�ȣ ����ġ");
	                    System.out.printf("\n�ٸ� ���񽺸� �̿��Ͻðڽ��ϱ�? (Y/N): ");
	                    otherService = input.next().charAt(0);
	                }
	                // !��й�ȣ ����ġ
	
	                // ��й�ȣ ��ġ
	                else {
	                    System.out.printf("\n�۱� ����: %s\n�۱� �ݾ�: %d��\n\n", bankAccount, amount);
	                    user.withdraw(amount);
	                    dest_user.deposit(amount);
	                    // !�۱� ����
	
	                    // ���
	                    record.recordingCon(3, amount);               
	    
	                    // !���
	
	                    // ��ǥ ���
	                    System.out.printf("��ǥ�� ����Ͻðڽ��ϱ�? (Y/N): ");
	                    specification = input.next().charAt(0);
	
	                    if (specification == 'Y') {
	                        System.out.printf("\n�ŷ� �ݾ�: %d\n", amount);
	                        System.out.printf("�ܾ�: %d\n", user.getUserBalance());
	                        System.out.printf("\n�ٸ� ���񽺸� �̿��Ͻðڽ��ϱ�? (Y/N): ");
	                        otherService = input.next().charAt(0);
	                    }
	                    // ! ��ǥ ���
	
	                    // �ٸ� ���� ����
	                    else {
	                        System.out.printf("\n�ٸ� ���񽺸� �̿��Ͻðڽ��ϱ�? (Y/N): ");
	                        otherService = input.next().charAt(0);
	                    }
	                    // !�ٸ� ���� ����
	                }
	            }
	            // !�۱� ����
	        }
	        // !������ü
	
	        // �ܾ� ��ȸ
	        else if (service == 4) {
	            // ��й�ȣ Ȯ��
	            System.out.printf("\n��й�ȣ�� �Է����ּ���: ");
	            pwd = input.nextInt();
	
	            // ��й�ȣ ����ġ
	            if (pwd != user.getPwd()) {
	                System.out.println("��й�ȣ ����ġ");
	                System.out.printf("\n�ٸ� ���񽺸� �̿��Ͻðڽ��ϱ�? (Y/N): ");
	                otherService = input.next().charAt(0);
	            }
	            // !��й�ȣ ����ġ
	
	            // ��й�ȣ ��ġ
	            else {
	                System.out.printf("�ܾ�: %d��\n", user.getUserBalance());
	                record.recordingCon(4,0);
	                
	                System.out.printf("\n�ٸ� ���񽺸� �̿��Ͻðڽ��ϱ�? (Y/N): ");
	                otherService = input.next().charAt(0);
	            }
	        }
	        // !�ܾ���ȸ
	    }
	    // ����
	    System.out.printf("%s�� �����մϴ�.\n", user.getName());
	    user_data.InputData(user, record);
	    // !����
	}
}