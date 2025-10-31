package lotto.model;

import static lotto.constants.LottoRules.PURCHASE_AMOUNT_UNIT;

public class Money {
    private static final String AMOUNT_UNIT_EXCEPTION = "구입 금액은 " + PURCHASE_AMOUNT_UNIT + "원 단위로 입력해야합니다.";
    private final int amount;

    public Money(int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    public int calculateCount() {
        return amount / PURCHASE_AMOUNT_UNIT;
    }

    private void validateAmount(int inputMoney) {
        if (inputMoney % PURCHASE_AMOUNT_UNIT != 0) {
            throw new IllegalArgumentException(AMOUNT_UNIT_EXCEPTION);
        }
    }
}
