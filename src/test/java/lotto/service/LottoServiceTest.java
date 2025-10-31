package lotto.service;


import java.util.List;
import lotto.model.BonusNumber;
import lotto.model.Lotto;
import lotto.model.LottoRank;
import lotto.model.WinningNumbers;
import lotto.model.WinningResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoServiceTest {
    LottoService lottoService;
    WinningNumbers winningNumbers;

    @BeforeEach
    void setUp() {
        lottoService = new LottoService();
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);
        winningNumbers = new WinningNumbers(winningLotto, bonusNumber);
    }

    @Test
    @DisplayName("등수 결과가 제대로 종합되는 지 검증한다.")
    void aggregateWinningResult() {
        //given
        Lotto firstLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto fifthLotto = new Lotto(List.of(1, 2, 3, 8, 9, 10));
        Lotto losingLotto = new Lotto(List.of(39, 40, 41, 42, 43, 44));
        List<Lotto> lottos = List.of(firstLotto, fifthLotto, losingLotto);
        //when
        WinningResult winningResult = lottoService.aggregateWinningResult(lottos, winningNumbers);
        //then
        Assertions.assertThat(winningResult.getMatchCount(LottoRank.FIRST)).isEqualTo(1);
        Assertions.assertThat(winningResult.getMatchCount(LottoRank.SECOND)).isEqualTo(0);
        Assertions.assertThat(winningResult.getMatchCount(LottoRank.THIRD)).isEqualTo(0);
        Assertions.assertThat(winningResult.getMatchCount(LottoRank.FOURTH)).isEqualTo(0);
        Assertions.assertThat(winningResult.getMatchCount(LottoRank.FIFTH)).isEqualTo(1);
        Assertions.assertThat(winningResult.getMatchCount(LottoRank.NONE)).isEqualTo(1);
    }
}