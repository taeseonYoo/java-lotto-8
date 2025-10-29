package lotto.model;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AmountTest {
    @Test
    @DisplayName("정수 범위 내의 1000단위 정수를 입력하면 성공적으로 객체가 생성된다.")
    void createAmount_success() {
        //given
        String input = "8000";
        //when & then
        assertDoesNotThrow(() -> new Amount(input));
    }

    @Test
    @DisplayName("정수 범위를 넘어선 입력 값이 주어지면, 예외가 발생한다.")
    void createAmount_fail_max() {
        //given
        String input = String.valueOf((long) Integer.MAX_VALUE + 1);
        //when & then
        Assertions.assertThatThrownBy(() -> new Amount(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"999", "1001"})
    @DisplayName("1,000원으로 나누어 지지 않는 값이 주어지면, 예외가 발생한다.")
    void createAmount_fail_zero(String input) {
        //when & then
        Assertions.assertThatThrownBy(() -> new Amount(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 개수는 금액 / 1000을 반환한다.")
    void getLottoCount() {
        //given
        String input = "1000000";
        Amount amount = new Amount(input);
        //when
        int lottoCount = amount.getLottoCount();
        //then
        Assertions.assertThat(lottoCount).isEqualTo(1000);
    }
}