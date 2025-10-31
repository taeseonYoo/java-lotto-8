package lotto.controller;

import java.util.Map;
import lotto.model.BonusNumber;
import lotto.model.Lotto;
import lotto.model.LottoMachine;
import lotto.infra.RandomNumberGenerator;
import lotto.model.Money;
import lotto.model.LottoRank;
import lotto.service.LottoService;
import lotto.common.utils.Parser;
import lotto.model.WinningNumbers;
import lotto.view.Input;
import lotto.view.Output;

public class LottoController {
    private final LottoService lottoService = new LottoService();

    public void run() {
        Money money = readMoney();
        LottoMachine lottoMachine = lottoService.createLottoMachine(money, new RandomNumberGenerator());

        Output.printPurchaseHistory(lottoMachine.getIssuedLottoCount(), lottoMachine.getHistory());

        Lotto winningNumber = readWinningNumber();
        BonusNumber bonusNumber = readBonusNumber();
        WinningNumbers winningNumbers = new WinningNumbers(winningNumber, bonusNumber);

        Map<LottoRank, Integer> winningResults = showWinningResults(lottoMachine, winningNumbers);
        showProfitRate(lottoMachine.getIssuedLottoCount(), winningResults);
    }

    private Map<LottoRank, Integer> showWinningResults(LottoMachine lottoMachine, WinningNumbers winningNumbers) {
        Output.printWinningResultGuide();
        Map<LottoRank, Integer> result = lottoService.aggregateWinningResult(lottoMachine.getHistory(),
                winningNumbers);

        for (LottoRank rank : LottoRank.values()) {
            if (rank == LottoRank.NONE) continue;

            int count = result.get(rank);
            Output.printLottoRank(rank.getMatchCount(), rank.getPrize(), count, rank.isMatchBonus());
        }
        return result;
    }

    private void showProfitRate(int count,Map<LottoRank, Integer> result) {
        double profitRate = lottoService.calculateProfitRate(count, result);
        Output.printRateOfReturn(profitRate);
    }

    private Money readMoney() {
        while (true) {
            try {
                Output.printLottoAmountGuide();
                int amount = Parser.parsingAmount(Input.readAmount());
                return new Money(amount);
            } catch (IllegalArgumentException e) {
                Output.printErrorMessage(e.getMessage());
            }
        }
    }

    private Lotto readWinningNumber() {
        while (true) {
            try {
                Output.printLottoWinningNumbersGuide();
                return new Lotto(Parser.parsingWinningNumbers(Input.readWinningNumbers()));
            } catch (IllegalArgumentException e) {
                Output.printErrorMessage(e.getMessage());
            }
        }
    }

    private BonusNumber readBonusNumber() {
        while (true) {
            try {
                Output.printBonusNumberGuide();
                return new BonusNumber(Parser.parsingBonusNumber(Input.readBonusNumber()));
            } catch (IllegalArgumentException e) {
                Output.printErrorMessage(e.getMessage());
            }
        }
    }
}
