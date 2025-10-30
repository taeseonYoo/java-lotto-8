package lotto.view;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import lotto.model.Lotto;
import lotto.model.LottoMachine;
import lotto.model.LottoRank;

public final class Output {
    private static final String LOTTO_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String LOTTO_WINNING_NUMBERS_MESSAGE = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_MESSAGE = "보너스 번호를 입력해 주세요.";
    private static final String WINNING_RESULT_MESSAGE = "당첨 통계\n" + "---";
    private static final String LOTTO_QUANTITY_MESSAGE = "%d개를 구매했습니다.";
    private static final String LOTTO_WINNING_DETAILS = "%d개 일치 (%s원) - %d개";
    private static final String LOTTO_WINNING_DETAILS_WITH_BONUS = "%d개 일치, 보너스 볼 일치 (%s원) - %d개";
    private static final String LOTTO_RATE_OF_RETURN = "총 수익률은 %.1f%%입니다.";
    private static final String LOTTO_EXCEPTION_MESSAGE = "[ERROR] ";

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
                .map(Output::formatLotto)
                .forEach(System.out::println);
    }

    private static String formatLotto(Lotto lotto) {
        String nums = lotto.getNumbers().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        return "[" + nums + "]";
    }

    public static void printLottoRank(int matchCount, int prize, int count, boolean bonus) {
        String prizeFormatted = NumberFormat.getNumberInstance(Locale.KOREA).format(prize);
        if (!bonus) {
            System.out.println(String.format(LOTTO_WINNING_DETAILS, matchCount, prizeFormatted, count));
        } else if (bonus) {
            System.out.println(String.format(LOTTO_WINNING_DETAILS_WITH_BONUS, matchCount, prizeFormatted, count));
        }
    }

    public static void printRateOfReturn(double rateOfReturn) {
        System.out.printf(LOTTO_RATE_OF_RETURN, rateOfReturn);
    }

    private static void printEmptyLine() {
        System.out.println();
    }
}
