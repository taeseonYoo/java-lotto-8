package lotto.service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.constants.LottoRules;
import lotto.model.Lotto;
import lotto.model.LottoMachine;
import lotto.model.LottoRank;
import lotto.model.NumberGenerator;
import lotto.model.WinningNumbers;

public class LottoService {
    public LottoMachine createLottoMachine(int count, NumberGenerator numberGenerator) {
        return new LottoMachine(count, numberGenerator);
    }

    public Map<LottoRank, Integer> aggregateWinningResult(List<Lotto> lottos, WinningNumbers winningNumbers) {
        Map<LottoRank, Integer> winningResult = initalizeRankMap();

        for (Lotto lotto : lottos) {
            int count = winningNumbers.countMatchingNumbers(lotto);
            boolean bonus = winningNumbers.isMatchBonus(lotto);

            LottoRank lottoRank = LottoRank.valueOf(count, bonus);
            winningResult.put(lottoRank, winningResult.get(lottoRank) + 1);
        }

        return winningResult;
    }

    private static Map<LottoRank, Integer> initalizeRankMap() {
        Map<LottoRank, Integer> winningResult = new EnumMap<>(LottoRank.class);
        for (LottoRank rank : LottoRank.values()) {
            winningResult.put(rank, 0);
        }
        return winningResult;
    }

    public double calculateProfitRate(int matchCount, Map<LottoRank, Integer> result) {
        long totalPrize = result.entrySet().stream()
                .mapToLong(e -> e.getKey().getTotalPrize(e.getValue()))
                .sum();

        long totalSpent = (long) matchCount * LottoRules.PURCHASE_AMOUNT_UNIT;
        return (double) totalPrize / totalSpent * 100;
    }
}
