package lotto.controller;

import lotto.model.BonusNumber;
import lotto.model.Lotto;
import lotto.model.LottoMachine;
import lotto.infra.RandomNumberGenerator;
import lotto.model.Money;
import lotto.model.LottoRank;
import lotto.model.WinningResult;
import lotto.service.LottoService;
import lotto.common.utils.Parser;
import lotto.model.WinningNumbers;
import lotto.view.Input;
import lotto.view.ConsoleOutput;

public class LottoController {
    private final LottoService lottoService = new LottoService();

    public void run() {
        Money money = readMoney();
        LottoMachine lottoMachine = LottoMachine.create(money, new RandomNumberGenerator());

        ConsoleOutput.printPurchaseHistory(lottoMachine.getIssuedLottoCount(), lottoMachine.getHistory());

        Lotto winningNumber = readWinningNumber();
        BonusNumber bonusNumber = readBonusNumber();
        WinningNumbers winningNumbers = new WinningNumbers(winningNumber, bonusNumber);

        WinningResult winningResult = showWinningResults(lottoMachine, winningNumbers);
        showProfitRate(money, winningResult);
    }

    private WinningResult showWinningResults(LottoMachine lottoMachine, WinningNumbers winningNumbers) {
        ConsoleOutput.printWinningResultGuide();
        WinningResult winningResult = lottoService.aggregateWinningResult(lottoMachine.getHistory(), winningNumbers);

        for (LottoRank rank : LottoRank.values()) {
            if (rank == LottoRank.NONE) {
                continue;
            }

            int count = winningResult.getMatchCount(rank);
            ConsoleOutput.printLottoRank(rank.getMatchCount(), rank.getPrize(), count, rank.isMatchBonus());
        }
        return winningResult;
    }

    private void showProfitRate(Money money, WinningResult winningResult) {
        double profitRate = winningResult.calculateProfitRate(money);
        ConsoleOutput.printRateOfReturn(profitRate);
    }

    private Money readMoney() {
        while (true) {
            try {
                ConsoleOutput.printLottoAmountGuide();
                int amount = Parser.parsingAmount(Input.readAmount());
                return new Money(amount);
            } catch (IllegalArgumentException e) {
                ConsoleOutput.printErrorMessage(e.getMessage());
            }
        }
    }

    private Lotto readWinningNumber() {
        while (true) {
            try {
                ConsoleOutput.printLottoWinningNumbersGuide();
                return new Lotto(Parser.parsingWinningNumbers(Input.readWinningNumbers()));
            } catch (IllegalArgumentException e) {
                ConsoleOutput.printErrorMessage(e.getMessage());
            }
        }
    }

    private BonusNumber readBonusNumber() {
        while (true) {
            try {
                ConsoleOutput.printBonusNumberGuide();
                return new BonusNumber(Parser.parsingBonusNumber(Input.readBonusNumber()));
            } catch (IllegalArgumentException e) {
                ConsoleOutput.printErrorMessage(e.getMessage());
            }
        }
    }
}
