package lotto.utils;

import java.util.Arrays;
import java.util.List;

public class Parser {
    private static final String SEPARATOR = ",";
    private static final String INVALID_WINNING_NUMBER_EXCEPTION = "당첨 번호는 int 범위 내의 정수만 입력할 수 있습니다.";
    private static final String INVALID_BONUS_NUMBER_EXCEPTION = "보너스 숫자는 int 범위 내의 정수만 입력할 수 있습니다.";
    private static final String INVALID_AMOUNT_EXCEPTION = "구입 금액은 int 범위 내의 정수만 입력할 수 있습니다.";

    public static List<Integer> parsingWinningNumbers(String inputNumbers) {
        try {
            return Arrays.stream(inputNumbers.split(SEPARATOR))
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_WINNING_NUMBER_EXCEPTION);
        }
    }

    public static int parsingBonusNumber(String inputBonusNumber) {
        try {
            return Integer.parseInt(inputBonusNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_BONUS_NUMBER_EXCEPTION);
        }
    }

    public static int parsingAmount(String inputAmount) {
        try {
            return Integer.parseInt(inputAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_AMOUNT_EXCEPTION);
        }
    }
}
