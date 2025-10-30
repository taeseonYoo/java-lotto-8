package lotto.utils;

import java.util.Arrays;
import java.util.List;

public class Parser {
    private final static String SEPARATOR = ",";

    public static List<Integer> parsingWinningNumbers(String inputNumbers) {
        try {
            return Arrays.stream(inputNumbers.split(SEPARATOR))
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("당첨 번호는 정수만 입력할 수 있습니다.");
        }
    }

    public static int parsingBonusNumber(String inputBonusNumber) {
        try {
            return Integer.parseInt(inputBonusNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("보너스 숫자는 정수만 입력할 수 있습니다.");
        }
    }
    public static int parsingMoney(String inputAmount) {
        try {
            return Integer.parseInt(inputAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구입 금액은 int 범위 내의 값만 입력 가능합니다.");
        }
    }
}
