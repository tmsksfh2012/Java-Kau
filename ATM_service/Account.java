package test;
// ���� ���� (�Ա�, ���, ������ü, �ܾ���ȸ)

public class Account {
    // �ܾ�
    private int balance;

    // constructor
    public Account(int initBalance) {
        if (initBalance > 0) {
            balance = initBalance;
        }
    }

    // �Ա�
    public void deposit(int amount) {
        balance = balance + amount;
    }
    // ���, ���� ��ü
    public void withdraw(int amount) {
        balance = balance - amount;
    }
    // �ܾ� ��ȸ
    public int getBalance() {
        return balance;
    }
}