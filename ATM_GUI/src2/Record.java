package src2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

// 추후 디비 인터페이스와 연결
// 클래스에서 현재 잔액 반환은 필요없는것으로 판단

public class Record {
    public static Record record = new Record();

    // 서비스 내용
    private Map<String, ArrayList<Integer>> service;

    // constructor
    public Record() {
        service = new HashMap<String, ArrayList<Integer>>();

        ArrayList<Integer> list_1 = new ArrayList<Integer>();
        service.put("입금",list_1);
        ArrayList<Integer> list_2 = new ArrayList<Integer>();
        service.put("출금",list_2);
        ArrayList<Integer> list_3 = new ArrayList<Integer>();
        service.put("계좌 이체",list_3);
    }

    public void recordService(int num, int amount) {
        switch(num) {
            case 1:
                service.get("입금").add(amount);
                break;
            case 2:
                service.get("출금").add(amount);
                break;
            case 3:
                service.get("계좌 이체").add(amount);
                break;
        }
    }

    // 이용한 서비스 내용 반환
    public void getService() {
        List<Entry<String,ArrayList<Integer>>> keySetList = new ArrayList<Entry<String,ArrayList<Integer>>>(service.entrySet());

        for(Entry<String, ArrayList<Integer>> entry : keySetList) {
            if(entry.getValue().isEmpty() != true) {
                System.out.printf("%s: ",entry.getKey());
                for(Integer i : entry.getValue()) {
                    System.out.printf("%d원 ", i);
                }
            }
        }
        System.out.println();
    }
}