package lotto.model;

import java.util.regex.Pattern;

public final class InputValidator {
    private static final String LOTTO_NUMBER_REGEX = "^[0-9,]+$";
    private static final String NUMBER_REGEX = "^[0-9]+$";

    private InputValidator() {
    }

    public static void validateWinningNumbers(String inputWinningNumbers) {
        if (!Pattern.matches(LOTTO_NUMBER_REGEX, inputWinningNumbers)) {
            throw new IllegalArgumentException("당첨 번호는 숫자와 (,)쉼표만 입력할 수 있습니다.");
        }
    }

    public static void validateAmount(String inputAmount) {
        if (!verifyIsDigitsOnly(inputAmount)) {
            throw new IllegalArgumentException("로또 구입 금액은 숫자만 입력할 수 있습니다.");
        }
    }

    public static void validateBonusNumber(String inputBonusNumber) {
        if (!verifyIsDigitsOnly(inputBonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 숫자만 입력할 수 있습니다.");
        }
    }

    private static boolean verifyIsDigitsOnly(String input) {
        return Pattern.matches(NUMBER_REGEX, input);
    }
}
