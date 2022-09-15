package test;
// 은행 업무 (입금, 출금, 계좌이체, 잔액조회)

public class Account {
    // 잔액
    private int balance;

    // constructor
    public Account(int initBalance) {
        if (initBalance > 0) {
            balance = initBalance;
        }
    }

    // 입금
    public void deposit(int amount) {
        balance = balance + amount;
    }
    // 출금, 계좌 이체
    public void withdraw(int amount) {
        balance = balance - amount;
    }
    // 잔액 조회
    public int getBalance() {
        return balance;
    }
}