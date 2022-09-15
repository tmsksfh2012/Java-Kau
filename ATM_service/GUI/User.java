package GUI;
// ���� ����
// ���� ������ ������

import java.util.ArrayList;

public class User {
    // ����� �������� (0 => �����, 1 => ���⿹��)
    private boolean isDaw;
    // ��й�ȣ
    private int password;
    // ���� �ܾ�
    private int userBalance;
    // ���� �̸�
    private String name;

    // constructor
    public User(boolean daw, String uName, int userPassword, int balance) {
        isDaw = daw;
        password = userPassword;
        userBalance = balance;
        name = uName;
    }

    // �ܾ� ��ȯ
    public int getUserBalance() {
        return userBalance;
    }
    // ��й�ȣ ��ȯ
    public int getPwd() {
        return password;
    }
    // ���� ���� ��ȯ
    public boolean kindOfAccount() {
        return isDaw;
    }
    // �̸� ��ȯ
    public String getName() {
        return name;
    }
    //�Ա�
    public void deposit(int amount) { //
        userBalance += amount;
    }
    // ���, ���� ��ü
    public void withdraw(int amount) { //
        userBalance -= amount;
    }
}
