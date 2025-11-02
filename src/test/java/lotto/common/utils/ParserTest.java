package lotto.common.utils;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    @DisplayName("당첨 번호가 분리되어 리스트로 반환된다.")
    void parsingWinningNumbers_success() {
        //given
        String input = "1,2,3,4,5,6";
        //when
        List<Integer> numbers = Parser.parsingWinningNumbers(input);
        //then
        assertThat(numbers).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("당첨 번호에 int 범위 이상의 값이 입력되면 오류가 발생한다.")
    void parsingWinningNumbers_fail() {
        //given
        String input = "1,2,3,4,5,2147483648";
        //when & then
        assertThatThrownBy(
                        () -> Parser.parsingWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보너스 숫자가 정수형으로 반환된다.")
    void parsingBonusNumber_success() {
        //given
        String input = "45";
        //when
        int bonusNumber = Parser.parsingBonusNumber(input);
        //then
        assertThat(bonusNumber).isEqualTo(45);

    }

    @Test
    @DisplayName("보너스 숫자에 int 범위 이상의 값이 입력되면 오류가 발생한다.")
    void parsingBonusNumber_fail() {
        //given
        String input = String.valueOf((long) Integer.MAX_VALUE + 1);
        //when & then
        assertThatThrownBy(
                        () -> Parser.parsingBonusNumber(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구매 금액이 정수형으로 반환된다.")
    void parsingAmount_success() {
        //given
        String input = "5000";
        //when
        int amount = Parser.parsingAmount(input);
        //then
        assertThat(amount).isEqualTo(5000);
    }

    @Test
    @DisplayName("구매 금액에 int 범위 이상의 값이 입력되면 오류가 발생한다.")
    void parsingAmount_fail() {
        //given
        String input = String.valueOf((long) Integer.MAX_VALUE + 1);
        //when & then
        assertThatThrownBy(
                        () -> Parser.parsingAmount(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}