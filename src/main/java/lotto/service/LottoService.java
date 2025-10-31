package lotto.service;

import java.util.List;
import lotto.model.Lotto;
import lotto.model.LottoRank;
import lotto.model.WinningNumbers;
import lotto.model.WinningResult;

public class LottoService {

    public WinningResult aggregateWinningResult(List<Lotto> lottos, WinningNumbers winningNumbers) {
        WinningResult winningResult = new WinningResult();

        for (Lotto lotto : lottos) {
            LottoRank lottoRank = evaluate(lotto, winningNumbers);
            winningResult.win(lottoRank);
        }

        return winningResult;
    }

    private LottoRank evaluate(Lotto lotto, WinningNumbers winningNumbers) {
        int matchCount = winningNumbers.countMatchingNumbers(lotto);
        boolean bonus = winningNumbers.isMatchBonus(lotto);
        return LottoRank.valueOf(matchCount, bonus);
    }
}
