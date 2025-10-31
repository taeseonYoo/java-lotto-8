package lotto.model;

import lotto.common.constants.LottoRules;

public record BonusNumber(int number) {
    private static final String INVALID_BONUS_NUMBER_RANGE_EXCEPTION = "보너스 숫자는 " + LottoRules.LOTTO_MIN_NUMBER + "~" +
            LottoRules.LOTTO_MAX_NUMBER + "사이의 숫자만 입력할 수 있습니다.";

    public BonusNumber {
        validate(number);
    }

    private void validate(int number) {
        if (number < LottoRules.LOTTO_MIN_NUMBER || number > LottoRules.LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException(INVALID_BONUS_NUMBER_RANGE_EXCEPTION);
        }
    }

}
