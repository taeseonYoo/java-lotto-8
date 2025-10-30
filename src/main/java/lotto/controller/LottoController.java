package lotto.controller;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.constants.LottoRules;
import lotto.model.BonusNumber;
import lotto.model.Lotto;
import lotto.model.LottoMachine;
import lotto.infra.RandomNumberGenerator;
import lotto.model.Money;
import lotto.model.LottoRank;
import lotto.service.LottoService;
import lotto.utils.Parser;
import lotto.model.WinningNumbers;
import lotto.view.Input;
import lotto.view.Output;

public class LottoController {
    private final LottoService lottoService = new LottoService();
    public void run() {
        Money money = checkLottoPurchaseAmount();
        LottoMachine lottoMachine = lottoService.createLottoMachine(money.getLottoCount(), new RandomNumberGenerator());

        Output.printPurchaseHistory(lottoMachine.getIssuedLottoCount(),lottoMachine.getHistory());

        Lotto winningNumber = checkLottoWinningNumber();
        BonusNumber bonusNumber = checkBonusNumber();
        WinningNumbers winningNumbers = new WinningNumbers(winningNumber, bonusNumber);

        Output.printWinningResultGuide();
        Map<LottoRank, Integer> result = lottoService.aggregateWinningResult(lottoMachine.getHistory(),
                winningNumbers);
        for (LottoRank rank : result.keySet()) {
            if (rank == LottoRank.NONE) continue;
            int count = result.get(rank);
            Output.printLottoRank(rank.getMatchCount(), rank.getPrize(), count, rank.isMatchBonus());
        }

        double profitRate = lottoService.calculateProfitRate(money.getLottoCount(), result);
        Output.printRateOfReturn(profitRate);
    }

    private Money checkLottoPurchaseAmount() {
        while (true) {
            try {
                Output.printLottoAmountGuide();
                int amount = Parser.parsingMoney(Input.readAmount());
                return new Money(amount);
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    private Lotto checkLottoWinningNumber() {
        while (true) {
            try {
                Output.printLottoWinningNumbersGuide();
                return new Lotto(Parser.parsingWinningNumbers(Input.readWinningNumbers()));
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    private BonusNumber checkBonusNumber() {
        while (true) {
            try {
                Output.printBonusNumberGuide();
                return new BonusNumber(Parser.parsingBonusNumber(Input.readBonusNumber()));
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }
}
