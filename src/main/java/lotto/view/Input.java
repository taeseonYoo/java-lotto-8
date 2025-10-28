package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public final class Input {
    public static String readAmount() {
        String inputAmount = Console.readLine();
        //TODO 문자열을 검증한다. -> 숫자만 입력했는 지
        return inputAmount;
    }

    public static String readWinningNumbers() {
        String inputWinningNumbers = Console.readLine();
        //TODO 문자열을 검증한다. -> 숫자와 쉼표로만 이루어졌는 지
        return inputWinningNumbers;
    }

    public static String readBonusNumber() {
        String inputBonusNumber = Console.readLine();
        //TODO -> 숫자로만 이루어졌는 지
        return inputBonusNumber;
    }

}
