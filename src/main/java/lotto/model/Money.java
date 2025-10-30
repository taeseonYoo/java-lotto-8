package lotto.model;

import static lotto.constants.LottoRules.PURCHASE_AMOUNT_UNIT;

public class Money {
    private final int amount;

    public Money(int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    public int getLottoCount() {
        return amount / PURCHASE_AMOUNT_UNIT;
    }

    private void validateAmount(int inputMoney) {
        if (inputMoney % PURCHASE_AMOUNT_UNIT != 0) {
            throw new IllegalArgumentException("구입 금액은 " + PURCHASE_AMOUNT_UNIT + "원 단위로 입력해야합니다.");
        }
    }
}
