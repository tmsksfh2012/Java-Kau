package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

// 추후 디비 인터페이스와 연결
// 클래스에서 현재 잔액 반환은 필요없는것으로 판단

public class Record {
    // 서비스 내용
	private Map<String, ArrayList<Integer>> serviceCon;

    // constructor
    public Record() {
    	serviceCon = new HashMap<String, ArrayList<Integer>>();
    	
    	ArrayList<Integer> list_1 = new ArrayList<Integer>();
    	serviceCon.put("입금",list_1);
    	ArrayList<Integer> list_2 = new ArrayList<Integer>();
    	serviceCon.put("출금",list_2);
    	ArrayList<Integer> list_3 = new ArrayList<Integer>();
    	serviceCon.put("계좌 이체",list_3);
    	ArrayList<Integer> list_4 = new ArrayList<Integer>();
    	serviceCon.put("잔고 조회",list_4);
    }
    public void recordingCon(int num, int amount) {
        switch(num) {
        case 1:
        	serviceCon.get("입금").add(amount);
        	break;
        case 2:
        	serviceCon.get("출금").add(amount);
        	break;
        case 3:
        	serviceCon.get("계좌 이체").add(amount);
        	break;
        case 4:
        	try{
        		int tmp = serviceCon.get("잔고 조회").get(0) + 1;
        		serviceCon.get("잔고 조회").remove(0);
            	serviceCon.get("잔고 조회").add(tmp);
        	} catch(Exception e){
        		serviceCon.get("잔고 조회").add(1);
        	}
        	break;
        }
    }

    // 이용한 서비스 내용 반환
    public void getServiceCon() {
    	List<Entry<String,ArrayList<Integer>>> keySetList = new ArrayList<Entry<String,ArrayList<Integer>>>(serviceCon.entrySet());
    	
    	for(Entry<String, ArrayList<Integer>> entry : keySetList) {
    		if(entry.getValue().isEmpty() != true) {
    			System.out.printf("%s: ",entry.getKey());
	    		for(Integer i : entry.getValue()) {
	    			if(entry.getKey().equals("잔고 조회")) {
	    				System.out.printf("%d회 ",i);
	    				continue;
	    			}
	    			System.out.printf("%d원 ", i);
	    		}
    		}
    	}
    	System.out.println();
    }
}