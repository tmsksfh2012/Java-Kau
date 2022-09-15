package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

// ���� ��� �������̽��� ����
// Ŭ�������� ���� �ܾ� ��ȯ�� �ʿ���°����� �Ǵ�

public class Record {
    // ���� ����
	private Map<String, ArrayList<Integer>> serviceCon;

    // constructor
    public Record() {
    	serviceCon = new HashMap<String, ArrayList<Integer>>();
    	
    	ArrayList<Integer> list_1 = new ArrayList<Integer>();
    	serviceCon.put("�Ա�",list_1);
    	ArrayList<Integer> list_2 = new ArrayList<Integer>();
    	serviceCon.put("���",list_2);
    	ArrayList<Integer> list_3 = new ArrayList<Integer>();
    	serviceCon.put("���� ��ü",list_3);
    	ArrayList<Integer> list_4 = new ArrayList<Integer>();
    	serviceCon.put("�ܰ� ��ȸ",list_4);
    }
    public void recordingCon(int num, int amount) {
        switch(num) {
        case 1:
        	serviceCon.get("�Ա�").add(amount);
        	break;
        case 2:
        	serviceCon.get("���").add(amount);
        	break;
        case 3:
        	serviceCon.get("���� ��ü").add(amount);
        	break;
        case 4:
        	try{
        		int tmp = serviceCon.get("�ܰ� ��ȸ").get(0) + 1;
        		serviceCon.get("�ܰ� ��ȸ").remove(0);
            	serviceCon.get("�ܰ� ��ȸ").add(tmp);
        	} catch(Exception e){
        		serviceCon.get("�ܰ� ��ȸ").add(1);
        	}
        	break;
        }
    }

    // �̿��� ���� ���� ��ȯ
    public void getServiceCon() {
    	List<Entry<String,ArrayList<Integer>>> keySetList = new ArrayList<Entry<String,ArrayList<Integer>>>(serviceCon.entrySet());
    	
    	for(Entry<String, ArrayList<Integer>> entry : keySetList) {
    		if(entry.getValue().isEmpty() != true) {
    			System.out.printf("%s: ",entry.getKey());
	    		for(Integer i : entry.getValue()) {
	    			if(entry.getKey().equals("�ܰ� ��ȸ")) {
	    				System.out.printf("%dȸ ",i);
	    				continue;
	    			}
	    			System.out.printf("%d�� ", i);
	    		}
    		}
    	}
    	System.out.println();
    }
}