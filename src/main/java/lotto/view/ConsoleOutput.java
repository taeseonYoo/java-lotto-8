package lotto.view;

import static lotto.common.constants.OutputConstants.BONUS_NUMBER_MESSAGE;
import static lotto.common.constants.OutputConstants.END_BRACKET;
import static lotto.common.constants.OutputConstants.LOTTO_AMOUNT_MESSAGE;
import static lotto.common.constants.OutputConstants.LOTTO_EXCEPTION_PREFIX;
import static lotto.common.constants.OutputConstants.LOTTO_QUANTITY_MESSAGE;
import static lotto.common.constants.OutputConstants.LOTTO_RATE_OF_RETURN;
import static lotto.common.constants.OutputConstants.LOTTO_WINNING_DETAILS;
import static lotto.common.constants.OutputConstants.LOTTO_WINNING_DETAILS_WITH_BONUS;
import static lotto.common.constants.OutputConstants.LOTTO_WINNING_NUMBERS_MESSAGE;
import static lotto.common.constants.OutputConstants.RATE_OF_RETURN_FORMAT;
import static lotto.common.constants.OutputConstants.SEPARATOR;
import static lotto.common.constants.OutputConstants.START_BRACKET;
import static lotto.common.constants.OutputConstants.WINNING_RESULT_MESSAGE;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import lotto.model.Lotto;

public final class ConsoleOutput {
    private ConsoleOutput() {
    }

    public static void printLottoAmountGuide() {
        System.out.println(LOTTO_AMOUNT_MESSAGE);
    }

    public static void printLottoWinningNumbersGuide() {
        printEmptyLine();
        System.out.println(LOTTO_WINNING_NUMBERS_MESSAGE);
    }

    public static void printBonusNumberGuide() {
        printEmptyLine();
        System.out.println(BONUS_NUMBER_MESSAGE);
    }

    public static void printWinningResultGuide() {
        printEmptyLine();
        System.out.println(WINNING_RESULT_MESSAGE);
    }

    public static void printPurchaseHistory(int issuedCount, List<Lotto> history) {
        printEmptyLine();
        System.out.printf(LOTTO_QUANTITY_MESSAGE, issuedCount);
        printEmptyLine();

        history.stream()
                .map(ConsoleOutput::formatLotto)
                .forEach(System.out::println);
    }

    private static String formatLotto(Lotto lotto) {
        String nums = lotto.getNumbers().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(SEPARATOR));
        return START_BRACKET + nums + END_BRACKET;
    }

    public static void printLottoRank(int matchCount, int prize, int count, boolean bonus) {
        String prizeFormatted = NumberFormat.getNumberInstance(Locale.KOREA).format(prize);
        if (!bonus) {
            System.out.printf(LOTTO_WINNING_DETAILS, matchCount, prizeFormatted, count);
        } else if (bonus) {
            System.out.printf(LOTTO_WINNING_DETAILS_WITH_BONUS, matchCount, prizeFormatted, count);
        }
        printEmptyLine();
    }

    public static void printRateOfReturn(double rateOfReturn) {
        String formattedResult = formatRateOfReturn(rateOfReturn);
        System.out.printf(LOTTO_RATE_OF_RETURN, formattedResult);
    }

    private static String formatRateOfReturn(double rateOfReturn) {
        return new DecimalFormat(RATE_OF_RETURN_FORMAT).format(rateOfReturn);
    }

    private static void printEmptyLine() {
        System.out.println();
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(LOTTO_EXCEPTION_PREFIX + errorMessage);
    }
}
