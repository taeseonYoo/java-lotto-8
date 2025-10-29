package lotto.controller;

import lotto.model.BonusNumber;
import lotto.model.Lotto;
import lotto.model.LottoMachine;
import lotto.infra.RandomNumberGenerator;
import lotto.model.Amount;
import lotto.model.Parser;
import lotto.model.WinningNumbers;
import lotto.view.Input;
import lotto.view.Output;

public class LottoController {
    public void run() {
        Amount amount = checkLottoPurchaseAmount();
        LottoMachine lottoMachine = new LottoMachine(amount.getLottoCount(), new RandomNumberGenerator());

        Output.printPurchaseHistory(lottoMachine);

        Lotto winningNumber = checkLottoWinningNumber();
        BonusNumber bonusNumber = checkBonusNumber();
        WinningNumbers winningNumbers = new WinningNumbers(winningNumber, bonusNumber);

        aggregateWinningResult();
    }

    private Amount checkLottoPurchaseAmount() {
        while (true) {
            try {
                Output.printLottoAmountGuide();
                return new Amount(Input.readAmount());
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    private Lotto checkLottoWinningNumber() {
        while(true){
            try {
                Output.printLottoWinningNumbersGuide();
                return new Lotto(Parser.parsingWinningNumbers(Input.readWinningNumbers()));
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    private BonusNumber checkBonusNumber() {
        while (true){
            try {
                Output.printBonusNumberGuide();
                return new BonusNumber(Parser.parsingBonusNumber(Input.readBonusNumber()));
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    private void aggregateWinningResult() {
        Output.printWinningResultGuide();
//        Output.
//        Output.printRateOfReturn();
    }
}
