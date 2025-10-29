package lotto.model;

import lotto.constants.LottoRules;

public class BonusNumber {
    private final int number;

    public BonusNumber(int number) {
        validate(number);
        this.number = number;
    }

    public int getBonusNumber() {
        return number;
    }

    private void validate(int number) {
        if (number < LottoRules.LOTTO_MIN_NUMBER || number > LottoRules.LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException("보너스 숫자는 1~45사이의 숫자만 입력할 수 있습니다.");
        }
    }

}
