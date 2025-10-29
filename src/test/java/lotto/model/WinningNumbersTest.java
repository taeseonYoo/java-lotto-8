package lotto.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {
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
}