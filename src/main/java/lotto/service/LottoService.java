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
            LottoRank lottoRank = winningNumbers.evaluate(lotto);
            winningResult.win(lottoRank);
        }

        return winningResult;
    }
}
