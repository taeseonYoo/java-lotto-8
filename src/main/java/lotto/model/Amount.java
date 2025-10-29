package lotto.model;

public class Amount {
    private static final int PURCHASE_AMOUNT_UNIT = 1000;
    private final int money;

    public Amount(String inputAmount) {
        int inputMoney = convertMoney(inputAmount);
        verifyMoney(inputMoney);
        this.money = inputMoney;
    }

    public int getLottoCount() {
        return money / PURCHASE_AMOUNT_UNIT;
    }

    private void verifyMoney(int inputMoney) {
        if (inputMoney % PURCHASE_AMOUNT_UNIT != 0) {
            throw new IllegalArgumentException("구입 금액은 " + PURCHASE_AMOUNT_UNIT + "원 단위로 입력해야합니다.");
        }
    }

    private int convertMoney(String inputAmount) {
        try {
            return Integer.parseInt(inputAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구입 금액은 int 범위 내의 값만 입력 가능합니다.");
        }
    }
}
