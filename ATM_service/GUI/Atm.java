package GUI;
// atm 내부 금액
// 만원권, 오만원권은 초기에 정해진대로
// 천원권은 100으로 가정

public class Atm {
    // 천원권
    private int thousand;
    // 만원권
    private int ten;
    // 오만원권
    private int fifty;

    public Atm() {
        ten = 1000;
        fifty = 200;
        thousand = 100;
    }

    // 출금 금액 빼기
    public void minusMoney(int amount) {
        while (amount > 0) {
            // 5만원권부터 순차 입금
            if (amount / 50000 > 0) {
                fifty = fifty - amount / 50000;
                amount = amount % 50000;
            }
            // 만원권
            else if (amount / 10000 > 0) {
                ten = ten - amount / 10000;
                amount = amount % 10000;
            }
            // 천원권
            else if (amount / 1000 > 0) {
                thousand = thousand - amount / 1000;
                amount = amount % 1000;
            }
        }
    }
    // 입금 금액 넣기
    public void plusMoney(int amount) {
        while (amount > 0) {
            // 5만원권부터 순차 입금
            if (amount / 50000 > 0) {
                fifty = fifty + amount / 50000;
                amount = amount % 50000;
            }
            // 만원권
            else if (amount / 10000 > 0) {
                ten = ten + amount / 10000;
                amount = amount % 10000;
            }
            // 천원권
            else if (amount / 1000 > 0) {
                thousand = thousand + amount / 1000;
                amount = amount % 1000;
            }
        }
    }

    // 만원권 개수
    public int getTen() {
        return ten;
    }
    // 오만원권 개수
    public int getFifty() {
        return fifty;
    }
}