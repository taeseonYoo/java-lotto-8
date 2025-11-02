package lotto.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class WinningResultTest {
    @ParameterizedTest
    @EnumSource(LottoRank.class)
    @DisplayName("초기화되면 Map 내부의 value 는 0으로 초기화된다.")
    void initResult(LottoRank rank) {
        //when
        WinningResult winningResult = new WinningResult();
        //then
        assertThat(winningResult.getMatchCount(rank))
                .isEqualTo(0);
    }

    @ParameterizedTest
    @EnumSource(LottoRank.class)
    @DisplayName("당첨되면 해당 랭크에 일치하는 value는 1 증가한다.")
    void win(LottoRank rank) {
        //given
        WinningResult winningResult = new WinningResult();
        //when
        winningResult.win(rank);
        //then
        assertThat(winningResult.getMatchCount(rank))
                .isEqualTo(1);
    }

    @Test
    @DisplayName("수익률을 계산한다. 8000원 사용 -> 5000원 당첨")
    void calculateProfitRate() {
        Money money = new Money(8000);
        WinningResult winningResult = new WinningResult();
        winningResult.win(LottoRank.FIFTH);

        //when
        double profitRate = winningResult.calculateProfitRate(money);
        //then
        assertThat(profitRate).isEqualTo((double) 5000 / 8000 * 100);
    }
}