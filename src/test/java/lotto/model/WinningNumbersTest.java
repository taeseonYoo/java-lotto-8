package lotto.model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WinningNumbersTest {

    @Test
    @DisplayName("당첨 번호에 보너스 번호가 포함되면 에러가 발생한다.")
    void createWinningNumbers_fail() {
        //given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto winningLotto = new Lotto(numbers);
        BonusNumber bonusNumber = new BonusNumber(1);
        //when & then
        assertThatThrownBy(() -> new WinningNumbers(winningLotto, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 번호에 보너스 번호가 포함되지 않는다면, 객체가 성공적으로 생성된다.")
    void createWinningNumbers_success() {
        //given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto winningLotto = new Lotto(numbers);
        BonusNumber bonusNumber = new BonusNumber(7);
        //when & then
        assertDoesNotThrow(() -> new WinningNumbers(winningLotto, bonusNumber));
    }

    @ParameterizedTest
    @MethodSource("getLottosForEachRank")
    void evaluate(Lotto lotto,LottoRank expectedRank) {
        //given
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);
        //when
        LottoRank resultRank = winningNumbers.evaluate(lotto);
        //then
        assertThat(resultRank).isEqualTo(expectedRank);
    }
    private static Stream<Arguments> getLottosForEachRank() {
        return Stream.of(
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), LottoRank.FIRST),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 7)), LottoRank.SECOND),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 8)), LottoRank.THIRD),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 8, 9)), LottoRank.FOURTH),
                Arguments.of(new Lotto(List.of(1, 2, 3, 8, 9, 10)), LottoRank.FIFTH),
                Arguments.of(new Lotto(List.of(10, 11, 12, 13, 14, 15)), LottoRank.NONE)
        );
    }
}