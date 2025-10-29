package lotto.view;

import java.util.List;
import java.util.stream.Collectors;
import lotto.model.Lotto;
import lotto.model.LottoMachine;

public final class Output {
    private static final String LOTTO_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String LOTTO_WINNING_NUMBERS_MESSAGE = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_MESSAGE = "보너스 번호를 입력해 주세요.";
    private static final String WINNING_RESULT_MESSAGE = "당첨 통계\n" + "---";
    private static final String LOTTO_QUANTITY_MESSAGE = "%d개를 구매했습니다.";
    private static final String LOTTO_WINNING_DETAILS = "%d개 일치 (%s원) - %d개";
    private static final String LOTTO_WINNING_DETAILS_WITH_BONUS = "%d개 일치, 보너스 볼 일치 (%s원) - %d개";
    private static final String LOTTO_RATE_OF_RETURN = "총 수익률은 %f%%입니다.";
    private static final String LOTTO_EXCEPTION_MESSAGE = "[ERROR] ";

    public static void printLottoAmountGuide() {
        System.out.println(LOTTO_AMOUNT_MESSAGE);
    }

    public static void printLottoWinningNumbersGuide() {
        System.out.println(LOTTO_WINNING_NUMBERS_MESSAGE);
    }

    public static void printBonusNumberGuide() {
        System.out.println(BONUS_NUMBER_MESSAGE);
    }

    public static void printWinningResultGuide() {
        System.out.println(WINNING_RESULT_MESSAGE);
    }

    public static void printPurchaseHistory(LottoMachine lottoMachine) {
        printEmptyLine();
        System.out.printf(LOTTO_QUANTITY_MESSAGE, lottoMachine.getIssuedLottoCount());
        printEmptyLine();
        List<Lotto> history = lottoMachine.getHistory();
        history.stream()
                .map(lotto -> lotto.getNumbers().stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", ")))
                .map(nums->"["+nums+"]")
                .forEach(System.out::println);
    }

    public static void printRateOfReturn(double rateOfReturn) {
        System.out.printf(LOTTO_RATE_OF_RETURN, rateOfReturn);
    }

    private static void printEmptyLine() {
        System.out.println();
    }
}
