package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.utils.InputValidator;

public final class Input {
    public static String readAmount() {
        String inputAmount = Console.readLine();
        InputValidator.validateAmount(inputAmount);
        return inputAmount;
    }

    public static String readWinningNumbers() {
        String inputWinningNumbers = Console.readLine();
        InputValidator.validateWinningNumbers(inputWinningNumbers);
        return inputWinningNumbers;
    }

    public static String readBonusNumber() {
        String inputBonusNumber = Console.readLine();
        InputValidator.validateBonusNumber(inputBonusNumber);
        return inputBonusNumber;
    }

}
