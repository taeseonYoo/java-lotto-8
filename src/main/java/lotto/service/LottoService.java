package lotto.service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.common.constants.LottoRules;
import lotto.model.Lotto;
import lotto.model.LottoMachine;
import lotto.model.LottoRank;
import lotto.model.Money;
import lotto.model.NumberGenerator;
import lotto.model.WinningNumbers;
import lotto.model.WinningResult;

public class LottoService {

    public WinningResult aggregateWinningResult(List<Lotto> lottos, WinningNumbers winningNumbers) {
        WinningResult winningResult = new WinningResult();

        for (Lotto lotto : lottos) {
            LottoRank lottoRank = evaluate(lotto, winningNumbers);
            winningResult.record(lottoRank);
        }

        return winningResult;
    }

    private LottoRank evaluate(Lotto lotto, WinningNumbers winningNumbers) {
        int matchCount = winningNumbers.countMatchingNumbers(lotto);
        boolean bonus = winningNumbers.isMatchBonus(lotto);
        return LottoRank.valueOf(matchCount, bonus);
    }
}
