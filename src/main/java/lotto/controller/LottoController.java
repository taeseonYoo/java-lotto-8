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
import lotto.utils.Parser;
import lotto.model.WinningNumbers;
import lotto.view.Input;
import lotto.view.Output;

public class LottoController {
    public void run() {
        Money money = checkLottoPurchaseAmount();
        LottoMachine lottoMachine = new LottoMachine(money.getLottoCount(), new RandomNumberGenerator());

        Output.printPurchaseHistory(lottoMachine);

        Lotto winningNumber = checkLottoWinningNumber();
        BonusNumber bonusNumber = checkBonusNumber();
        WinningNumbers winningNumbers = new WinningNumbers(winningNumber, bonusNumber);

        aggregateWinningResult(lottoMachine.getHistory(), winningNumbers);
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

    private void aggregateWinningResult(List<Lotto> lottos, WinningNumbers winningNumbers) {
        Map<LottoRank, Integer> result = new EnumMap<>(LottoRank.class);

        Output.printWinningResultGuide();
        for (Lotto lotto : lottos) {
            int count = 0;
            boolean bonus = false;

            for (Integer number : winningNumbers.getWinningLotto().getNumbers()) {
                if (lotto.contains(number)) {
                    count++;
                }
            }
            if (lotto.contains(winningNumbers.getBonusNumber().getBonusNumber())) {
                bonus = true;
            }
            LottoRank lottoRank = LottoRank.valueOf(count, bonus);
            result.put(lottoRank, result.getOrDefault(lottoRank, 0) + 1);
        }

        long totalPrize = 0;
        for (LottoRank rank : LottoRank.values()) {
            if (rank == LottoRank.NONE) {
                continue;
            }
            int count = result.getOrDefault(rank, 0);
            Output.printLottoRank(rank.getMatchCount(), rank.getPrize(), count, rank.isMatchBonus());
            totalPrize += rank.getTotalPrize(count);
        }

        long totalSpent = (long) lottos.size() * LottoRules.PURCHASE_AMOUNT_UNIT;
        double profitRate = (double) totalPrize / totalSpent * 100;

        Output.printRateOfReturn(profitRate);
    }
}
