package lotto.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinningNumbersTest {
    WinningNumbers winningNumbers;

    @BeforeEach
    void setUp() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);
        winningNumbers = new WinningNumbers(winningLotto, bonusNumber);
    }

    @Test
    @DisplayName("당첨 번호에 보너스 번호가 포함되면 에러가 발생한다.")
    void createWinningNumbers_fail() {
        //given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto winningLotto = new Lotto(numbers);
        BonusNumber bonusNumber = new BonusNumber(1);
        //when & then
        Assertions.assertThatThrownBy(() -> new WinningNumbers(winningLotto, bonusNumber))
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
    @CsvSource(value = {
            "1,2,3,4,5,6^6",
            "1,2,3,4,5,8^5",
            "1,2,3,4,8,9^4",
            "1,2,3,8,9,10^3",
            "8,9,10,11,12,13^0",
    }, delimiter = '^')
    @DisplayName("구매한 로또와 당첨 번호가 일치하는 개수를 정확히 반환하는 지 검증한다.")
    void countMatchingNumbers(String inputNumbers, int answer) {
        //given
        List<Integer> numbers = Arrays.stream(inputNumbers.split(",")).map(Integer::parseInt).toList();
        Lotto purchasedLotto = new Lotto(numbers);
        //when
        int match = winningNumbers.countMatchingNumbers(purchasedLotto);
        //then
        Assertions.assertThat(match).isEqualTo(answer);
    }

    @Test
    @DisplayName("보너스 번호와 일치하지 않으면 false를 반환한다.")
    void isMatchBonus_false() {
        //given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        //when
        boolean matchBonus = winningNumbers.isMatchBonus(lotto);
        //then
        Assertions.assertThat(matchBonus).isFalse();
    }

    @Test
    @DisplayName("보너스 번호와 일치하면 true를 반환한다.")
    void isMatchBonus_true() {
        //given
        Lotto lotto = new Lotto(List.of(7, 8, 9, 10, 11, 12));
        //when
        boolean matchBonus = winningNumbers.isMatchBonus(lotto);
        //then
        Assertions.assertThat(matchBonus).isTrue();
    }
}