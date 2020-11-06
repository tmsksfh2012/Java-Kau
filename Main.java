package test;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Database user_data = new Database();
		while(true) {
			Scanner input = new Scanner(System.in);
			String Lname="";
			int Lpassword=0;
			System.out.printf("----------<ATM 작동>----------\n안녕하세요.\n데이터를 입력하십시오\n(이름,비밀번호)\n입력: ");
			input = new Scanner(input.next()).useDelimiter(",");
			
			Lname = input.next();
			Lpassword = input.nextInt();
			
			User Luser = user_data.getData(Lname, Lpassword);
			if(Luser == null) { //반환값이 null인 경우 -> 입력받은 이름 혹은 비밀번호가 데이터 베이스 정보와 다른 경우
				System.out.println("이름 혹은 비밀번호가 잘못되었습니다.");
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
			System.out.println("---------<ATM 사용 완료>---------");
		}
	}
}
