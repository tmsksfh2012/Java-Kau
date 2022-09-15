package test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

//ArrayList가 두 개 필요 -> user, record
//Database에 사용자 정보(user)와 사용자 업무 사항(record)가 기억되어야함

public class Database{
    private Map<User,ArrayList<Record>> dataList; //User, Record 자료형의 Map으로 데이터베이스 구현

    public Database() {
    	dataList= new HashMap<>();
    	while(true) {
			Scanner input = new Scanner(System.in);
			String name = "";
			int password = 0;
			int type = 0;
			int balance = 0;
			
			System.out.printf("------<데이터 베이스 정보 입력>------\n입력 형태 [이름,비밀번호,계좌유형(입출금=1,정기적금=0),잔액]\n(입력 완료시 -1)\n입력: ");
			
			String tmp = input.nextLine(); //입력을 마무리하였는지 판단
			Integer comp = -1; //-1을 Integer로 받아 toString이 가능하게 해줌
			
			if(tmp.equals(comp.toString())) {
				if(dataList.isEmpty() == true) {
					System.out.println("데이터 베이스가 비어 있습니다.\n다시 입력해주십시오.");
					continue;
				}
				System.out.println("\n----<사용자 데이터 베이스 입력 완료>----\n");
				break;
			}
			else {
				input = new Scanner(tmp).useDelimiter(","); //콤마 기준으로 나눠서 입력
				try {
					name = input.next();
					password = input.nextInt();
					type = input.nextInt();
					balance = input.nextInt();
					
				}catch(Exception e){
					System.out.println("잘못 입력하셨습니다.");
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
    
    /*public void DView(User user) { //프린트용 함수
    	List<Entry<User,Record>> keySetList = new ArrayList<Entry<User,Record>>(dataList.entrySet());
    	for(Entry<User, Record> entry : keySetList) {
    		if(entry.getKey() == user) {
    			System.out.printf("회원 정보: %s\n거래 금액:%d\n잔액: %d\n수행 업무: %d\n", entry.getKey().getName(), entry.getValue().getUsedAmount(),
    					entry.getKey().getUserBalance(), entry.getValue().getServiceNum());
    		}
    	}
    }
    
    public void AllView() { //프린트용 함수
    	List<Entry<User,Record>> keySetList = new ArrayList<Entry<User,Record>>(dataList.entrySet());
    	for(Entry<User, Record> entry : keySetList) {
    			System.out.printf("회원 정보: %s\n잔액:%d\n", entry.getKey().getName(),
    					entry.getKey().getUserBalance());
    	}
    }
    */
}
