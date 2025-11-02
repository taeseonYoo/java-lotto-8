package lotto.model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {
    @Test
    @DisplayName("정수 범위 내의 1000단위 정수를 입력하면 성공적으로 객체가 생성된다.")
    void createAmount_success() {
        //given
        int input = 8000;
        //when & then
        assertDoesNotThrow(() -> new Money(input));
    }

    @ParameterizedTest
    @ValueSource(ints = {999, 1001})
    @DisplayName("1,000원으로 나누어 지지 않는 값이 주어지면, 예외가 발생한다.")
    void createAmount_fail_unit(int input) {
        //when & then
        assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("최소 1,000원 이상의 값이 주어져야한다.")
    void createAmount_fail_zero() {
        //given
        int input = 0;
        //when & then
        assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 개수는 금액 / 단위를 반환한다.")
    void getLottoCount() {
        //given
        int input = 1_000_000;
        Money money = new Money(input);
        //when
        int lottoCount = money.calculateCount();
        //then
        assertThat(lottoCount).isEqualTo(1000);
    }
}