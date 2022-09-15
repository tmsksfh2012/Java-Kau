package test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

//ArrayList�� �� �� �ʿ� -> user, record
//Database�� ����� ����(user)�� ����� ���� ����(record)�� ���Ǿ����

public class Database{
    private Map<User,ArrayList<Record>> dataList; //User, Record �ڷ����� Map���� �����ͺ��̽� ����

    public Database() {
    	dataList= new HashMap<>();
    	while(true) {
			Scanner input = new Scanner(System.in);
			String name = "";
			int password = 0;
			int type = 0;
			int balance = 0;
			
			System.out.printf("------<������ ���̽� ���� �Է�>------\n�Է� ���� [�̸�,��й�ȣ,��������(�����=1,��������=0),�ܾ�]\n(�Է� �Ϸ�� -1)\n�Է�: ");
			
			String tmp = input.nextLine(); //�Է��� �������Ͽ����� �Ǵ�
			Integer comp = -1; //-1�� Integer�� �޾� toString�� �����ϰ� ����
			
			if(tmp.equals(comp.toString())) {
				if(dataList.isEmpty() == true) {
					System.out.println("������ ���̽��� ��� �ֽ��ϴ�.\n�ٽ� �Է����ֽʽÿ�.");
					continue;
				}
				System.out.println("\n----<����� ������ ���̽� �Է� �Ϸ�>----\n");
				break;
			}
			else {
				input = new Scanner(tmp).useDelimiter(","); //�޸� �������� ������ �Է�
				try {
					name = input.next();
					password = input.nextInt();
					type = input.nextInt();
					balance = input.nextInt();
					
				}catch(Exception e){
					System.out.println("�߸� �Է��ϼ̽��ϴ�.");
				}
				if(type == 1) {
					User user = new User(true, name, password, balance);
					dataList.put(user, null);
				}
				else if(type == 0) {
					User user = new User(false, name, password, balance);
					dataList.put(user, null);
				}
			}
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
    
    /*public void DView(User user) { //����Ʈ�� �Լ�
    	List<Entry<User,Record>> keySetList = new ArrayList<Entry<User,Record>>(dataList.entrySet());
    	for(Entry<User, Record> entry : keySetList) {
    		if(entry.getKey() == user) {
    			System.out.printf("ȸ�� ����: %s\n�ŷ� �ݾ�:%d\n�ܾ�: %d\n���� ����: %d\n", entry.getKey().getName(), entry.getValue().getUsedAmount(),
    					entry.getKey().getUserBalance(), entry.getValue().getServiceNum());
    		}
    	}
    }
    
    public void AllView() { //����Ʈ�� �Լ�
    	List<Entry<User,Record>> keySetList = new ArrayList<Entry<User,Record>>(dataList.entrySet());
    	for(Entry<User, Record> entry : keySetList) {
    			System.out.printf("ȸ�� ����: %s\n�ܾ�:%d\n", entry.getKey().getName(),
    					entry.getKey().getUserBalance());
    	}
    }
    */
}
