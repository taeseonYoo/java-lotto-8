package lotto.controller;

import lotto.model.LottoMachine;
import lotto.model.NumberGenerator;
import lotto.RandomNumberGenerator;
import lotto.model.Amount;
import lotto.view.Input;
import lotto.view.Output;

public class LottoController {
    public void run() {
        Amount amount = checkLottoPurchaseAmount();

        NumberGenerator numberGenerator = new RandomNumberGenerator();
        LottoMachine lottoMachine = new LottoMachine(amount.getLottoCount(),numberGenerator);
        //구매 내역
        Output.printPurchaseHistory(lottoMachine);
        //당첨 번호
        Output.printLottoWinningNumbersGuide();
        Input.readWinningNumbers();
        //보너스 번호
        Output.printBonusNumberGuide();
        Input.readBonusNumber();
        //당첨 통계
        Output.printWinningResultGuide();
//        Output.
//        Output.printRateOfReturn();
    }

    private Amount checkLottoPurchaseAmount() {
        while (true) {
            try {
                Output.printLottoAmountGuide();
                return new Amount(Input.readAmount());
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] "+e.getMessage());
            }
        }
    }
}
