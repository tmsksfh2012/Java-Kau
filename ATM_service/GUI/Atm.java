package GUI;
// atm ���� �ݾ�
// ������, ���������� �ʱ⿡ ���������
// õ������ 100���� ����

public class Atm {
    // õ����
    private int thousand;
    // ������
    private int ten;
    // ��������
    private int fifty;

    public Atm() {
        ten = 1000;
        fifty = 200;
        thousand = 100;
    }

    // ��� �ݾ� ����
    public void minusMoney(int amount) {
        while (amount > 0) {
            // 5�����Ǻ��� ���� �Ա�
            if (amount / 50000 > 0) {
                fifty = fifty - amount / 50000;
                amount = amount % 50000;
            }
            // ������
            else if (amount / 10000 > 0) {
                ten = ten - amount / 10000;
                amount = amount % 10000;
            }
            // õ����
            else if (amount / 1000 > 0) {
                thousand = thousand - amount / 1000;
                amount = amount % 1000;
            }
        }
    }
    // �Ա� �ݾ� �ֱ�
    public void plusMoney(int amount) {
        while (amount > 0) {
            // 5�����Ǻ��� ���� �Ա�
            if (amount / 50000 > 0) {
                fifty = fifty + amount / 50000;
                amount = amount % 50000;
            }
            // ������
            else if (amount / 10000 > 0) {
                ten = ten + amount / 10000;
                amount = amount % 10000;
            }
            // õ����
            else if (amount / 1000 > 0) {
                thousand = thousand + amount / 1000;
                amount = amount % 1000;
            }
        }
    }

    // ������ ����
    public int getTen() {
        return ten;
    }
    // �������� ����
    public int getFifty() {
        return fifty;
    }
}