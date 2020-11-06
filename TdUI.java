package test;
// 정기예금 ui

import java.util.ArrayList;
import java.util.Scanner;

public class TdUI {
	// 사용자 데이터
	User user;
	// 사용자 업무 사항 저장
	Record record;
    // 입금금액
    int amount;
    // 비밀번호
    int pwd;
    
	public TdUI(User Luser, Database user_data) {
		user = Luser;
		record = new Record();
	
	    // initial state
	    System.out.printf("%s님 안녕하세요\n", user.getName());
	
	    Scanner input = new Scanner(System.in);
	
	    // 입금 화면
	    System.out.printf("\n<----입금---->");
	    System.out.printf("\n입금 금액을 입력해주세요: ");
	    amount = input.nextInt();
	    // !입금 화면
	
	    // 비밀번호 확인
	    System.out.printf("\n비밀번호를 입력해주세요: ");
	    pwd = input.nextInt();
	
	    // 비밀번호 불일치
	    if (pwd != user.getPwd()) {
	        System.out.println("비밀번호 불일치");
	        System.out.printf("비밀번호를 다시 입력해주세요: ");
	        pwd = input.nextInt();
	    }
	    // !비밀번호 불일치
	
	    // 비밀번호 일치
	    else {
	        // 입금 수행
	        System.out.printf("%d원을 정기예금 계좌에 입금합니다. \n\n", amount);
	        user.deposit(amount);
	        record.recordingCon(1,amount);
	        System.out.printf("%s님 감사합니다.\n", user.getName());
	        // !입금 수행
	
	        // 기록
	        user_data.InputData(user, record); //
	        // !기록
	
	    }
	}
}