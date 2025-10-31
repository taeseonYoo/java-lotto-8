package lotto.model;

import static lotto.common.constants.LottoRules.PURCHASE_AMOUNT_UNIT;

public class Money {
    private static final String AMOUNT_UNIT_EXCEPTION = "구입 금액은 " + PURCHASE_AMOUNT_UNIT + "원 단위로 입력해야합니다.";
    private static final String AMOUNT_MIN_EXCEPTION = "구입 금액은 최소 " + PURCHASE_AMOUNT_UNIT + "원 이상 입력해야합니다.";
    private final int amount;

    public Money(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        validateMinAmount(amount);
        validateUnit(amount);
    }

    public int getAmount() {
        return amount;
    }

    public int calculateCount() {
        return amount / PURCHASE_AMOUNT_UNIT;
    }

    private void validateUnit(int inputAmount) {
        if (inputAmount % PURCHASE_AMOUNT_UNIT != 0) {
            throw new IllegalArgumentException(AMOUNT_UNIT_EXCEPTION);
        }
    }
    private void validateMinAmount(int inputAmount){
        if (inputAmount < PURCHASE_AMOUNT_UNIT) {
            throw new IllegalArgumentException(AMOUNT_MIN_EXCEPTION);
        }
    }
}
