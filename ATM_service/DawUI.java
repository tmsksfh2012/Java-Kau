package test;
// 입출금 UI

import java.util.ArrayList;
import java.util.Scanner;

public class DawUI {
	// 사용자 데이터
	User user;
	// 사용자 업무 사항 저장
	Record record;
    // 거래금액
    int amount;
    // 서비스 이용 유무
    char otherService = 'Y';
    // 명세표 출력 유무
    char specification;
    // ATM
    Atm atm = new Atm();
    // 오만원권 개수
    int dollar = 0;
    // 계좌이체용 계좌
    String bankAccount;
    // 비밀번호
    int pwd;
	
	public DawUI(User user, Database user_data) {
		user = user;
		record = new Record();
	    // initial state
	    System.out.printf("%s님 안녕하세요\n", user.getName());
	
	    while (otherService == 'Y') {
	        System.out.println("\n1. 입금");
	        System.out.println("2. 출금");
	        System.out.println("3. 계좌이체");
	        System.out.println("4. 잔액 조회");
	        System.out.printf("진행하실 업무를 선택해주세요: ");
	        Scanner input = new Scanner(System.in);
	        int service = input.nextInt();
	
	        // 입금
	        if (service == 1) {
	            // 입금 화면
	            System.out.printf("\n<----입금---->");
	            System.out.printf("\n입금 금액을 입력해주세요: ");
	            amount = input.nextInt();
	            // !입금 화면
	
	            // 입금 금액 범위 (천원 단위부터)
	            while (amount % 1000 != 0) {
	                System.out.println("입금은 천원권 단위 이상부터 가능합니다");
	                System.out.printf("\n입금 금액을 입력해주세요: ");
	                amount = input.nextInt();
	            }
	
	            // 비밀번호 확인
	            System.out.printf("\n비밀번호를 입력해주세요: ");
	            pwd = input.nextInt();
	
	            // 비밀번호 불일치
	            if (pwd != user.getPwd()) {
	                System.out.println("비밀번호 불일치");
	                System.out.printf("\n다른 서비스를 이용하시겠습니까? (Y/N): ");
	                otherService = input.next().charAt(0);
	            }
	            // !비밀번호 불일치
	
	            // 비밀번호 일치
	            else {
	                // 입금 수행
	                System.out.printf("%d원을 입금합니다. \n\n", amount);
	                
	                user.deposit(amount); //
	                
	                atm.plusMoney(amount);
	                // !입금 수행
	
	                // 기록
	                record.recordingCon(1, amount);	
	                // !기록
	
	                // 명세표 출력
	                System.out.printf("명세표를 출력하시겠습니까? (Y/N): ");
	                specification = input.next().charAt(0);
	
	                if (specification == 'Y') {
	                    System.out.printf("\n거래 금액: %d\n", amount);
	                    System.out.printf("잔액: %d\n", user.getUserBalance());
	                    System.out.printf("\n다른 서비스를 이용하시겠습니까? (Y/N): ");
	                    otherService = input.next().charAt(0);
	                }
	                // ! 명세표 출력
	
	                // 다른 서비스 선택
	                else {
	                    System.out.printf("\n다른 서비스를 이용하시겠습니까? (Y/N): ");
	                    otherService = input.next().charAt(0);
	                }
	                // !다른 서비스 선택
	            }
	        }
	        // !입금
	
	        // 출금
	        else if (service == 2) {
	            // 출금 화면
	            System.out.printf("\n<----출금---->");
	            System.out.printf("\n출금 금액을 입력해주세요: ");
	            amount = input.nextInt();
	            // !출금 화면
	
	            // 출금 금액 설정 (10000원, 50000원)
	            while (amount % 10000 != 0 && amount % 50000 != 0) {
	                System.out.println("출금 금액은 10000원권부터 가능합니다");
	                System.out.printf("\n출금 금액을 입력해주세요: ");
	                amount = input.nextInt();
	            }
	            // !출금 금액 설정
	
	            // 잔액 부족시
	            if (amount > user.getUserBalance()) {
	                System.out.printf("잔액이 부족합니다\n고객님의 현재 잔액은 %d원입니다\n", user.getUserBalance());
	                System.out.printf("다른 서비스를 이용하시겠습니까? (Y/N): ");
	                otherService = input.next().charAt(0);
	                continue;
	            }
	            // !잔액 부족시
	
	            // 출금 수행
	            // 오만원권 선택
	            if (amount / 50000 > 0) {
	                System.out.printf("\n출금 금액: %d\n", amount);
	                System.out.printf("오만원권 입력: ");
	                dollar = input.nextInt();
	
	                // 사용자가 입력한 오만원권이 더 큰 경우
	                while (dollar > amount / 50000) {
	                    System.out.printf("\n출금 금액보다 오만원권이 큽니다\n다시 입력해주세요: ");
	                    dollar = input.nextInt();
	                }
	            }
	            // !오만원권 선택
	
	            // atm 감당가능 지폐인지 확인
	            if (dollar > atm.getFifty() || (amount - 50000 * dollar) / 10000 > atm.getTen()) {
	                System.out.printf("죄송합니다 atm의 지폐가 부족합니다.\n");
	
	                System.out.printf("다른 서비스를 이용하시겠습니까? (Y/N): ");
	                otherService = input.next().charAt(0);
	            }
	            // !atm 감당가능 지폐인지 확인
	
	            else {
	                // 비밀번호 확인
	                System.out.printf("\n비밀번호를 입력해주세요: ");
	                pwd = input.nextInt();
	
	                // 비밀번호 불일치
	                if (pwd != user.getPwd()) {
	                    System.out.println("비밀번호 불일치");
	                    System.out.printf("\n다른 서비스를 이용하시겠습니까? (Y/N): ");
	                    otherService = input.next().charAt(0);
	                }
	                // !비밀번호 불일치
	
	                // 비밀번호 일치
	                else {
	                    // 출금 수행
	                    System.out.printf("\n%d원을 출금합니다. \n\n", amount);
	                    
	                    user.withdraw(amount); //
	                    
	                    atm.minusMoney(amount);
	                    // !출금 수행
	
	                    // 기록
	                    record.recordingCon(2, amount);
	                    // !기록
	
	                    // 명세표 출력
	                    System.out.printf("명세표를 출력하시겠습니까? (Y/N): ");
	                    specification = input.next().charAt(0);
	
	                    if (specification == 'Y') {
	                        System.out.printf("\n거래 금액: %d\n", amount);
	                        System.out.printf("잔액: %d\n", user.getUserBalance());
	                        System.out.printf("\n다른 서비스를 이용하시겠습니까? (Y/N): ");
	                        otherService = input.next().charAt(0);
	                    }
	                    // ! 명세표 출력
	
	                    // 다른 서비스 선택
	                    else {
	                        System.out.printf("\n다른 서비스를 이용하시겠습니까? (Y/N): ");
	                        otherService = input.next().charAt(0);
	                    }
	                    // !다른 서비스 선택
	                }
	            }
	        }
	        // !출금
	
	        // 계좌이체
	        else if (service == 3) {
	            // 계좌이체 화면
	            System.out.printf("\n<----계좌이체---->");
		        System.out.printf("\n송금 계좌를 입력해주세요: ");
		        bankAccount = input.next();
	            User dest_user = user_data.getData(bankAccount);
	            if(dest_user == null) {
	            	System.out.printf("송금 계좌가 잘못되었습니다.\n");
	            	System.out.printf("다른 서비스를 이용하시겠습니까? (Y/N): ");
	                otherService = input.next().charAt(0);
	                continue;
	            }
	            
	            System.out.printf("송금 금액을 입력해주세요: ");
	            amount = input.nextInt();
	            // !계좌이체 화면
	
	            // 잔액 부족시
	            if (amount > user.getUserBalance()) {
	                System.out.printf("잔액이 부족합니다\n고객님의 현재 잔액은 %d원입니다\n", user.getUserBalance());
	                System.out.printf("다른 서비스를 이용하시겠습니까? (Y/N): ");
	                otherService = input.next().charAt(0);
	                continue;
	            }
	            // !잔액 부족시
	
	            // 송금 수행
	            else {
	                // 비밀번호 확인
	                System.out.printf("\n비밀번호를 입력해주세요: ");
	                pwd = input.nextInt();
	
	                // 비밀번호 불일치
	                if (pwd != user.getPwd()) {
	                    System.out.println("\n비밀번호 불일치");
	                    System.out.printf("\n다른 서비스를 이용하시겠습니까? (Y/N): ");
	                    otherService = input.next().charAt(0);
	                }
	                // !비밀번호 불일치
	
	                // 비밀번호 일치
	                else {
	                    System.out.printf("\n송금 계좌: %s\n송금 금액: %d원\n\n", bankAccount, amount);
	                    user.withdraw(amount);
	                    dest_user.deposit(amount);
	                    // !송금 수행
	
	                    // 기록
	                    record.recordingCon(3, amount);               
	    
	                    // !기록
	
	                    // 명세표 출력
	                    System.out.printf("명세표를 출력하시겠습니까? (Y/N): ");
	                    specification = input.next().charAt(0);
	
	                    if (specification == 'Y') {
	                        System.out.printf("\n거래 금액: %d\n", amount);
	                        System.out.printf("잔액: %d\n", user.getUserBalance());
	                        System.out.printf("\n다른 서비스를 이용하시겠습니까? (Y/N): ");
	                        otherService = input.next().charAt(0);
	                    }
	                    // ! 명세표 출력
	
	                    // 다른 서비스 선택
	                    else {
	                        System.out.printf("\n다른 서비스를 이용하시겠습니까? (Y/N): ");
	                        otherService = input.next().charAt(0);
	                    }
	                    // !다른 서비스 선택
	                }
	            }
	            // !송금 수행
	        }
	        // !계좌이체
	
	        // 잔액 조회
	        else if (service == 4) {
	            // 비밀번호 확인
	            System.out.printf("\n비밀번호를 입력해주세요: ");
	            pwd = input.nextInt();
	
	            // 비밀번호 불일치
	            if (pwd != user.getPwd()) {
	                System.out.println("비밀번호 불일치");
	                System.out.printf("\n다른 서비스를 이용하시겠습니까? (Y/N): ");
	                otherService = input.next().charAt(0);
	            }
	            // !비밀번호 불일치
	
	            // 비밀번호 일치
	            else {
	                System.out.printf("잔액: %d원\n", user.getUserBalance());
	                record.recordingCon(4,0);
	                
	                System.out.printf("\n다른 서비스를 이용하시겠습니까? (Y/N): ");
	                otherService = input.next().charAt(0);
	            }
	        }
	        // !잔액조회
	    }
	    // 종료
	    System.out.printf("%s님 감사합니다.\n", user.getName());
	    user_data.InputData(user, record);
	    // !종료
	}
}