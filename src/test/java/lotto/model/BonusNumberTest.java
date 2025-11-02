package lotto.model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BonusNumberTest {
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46})
    void createBonusNumber_fail(int number) {
        //when & then
        assertThatThrownBy(() ->
                new BonusNumber(number));
    }

    @Test
    @DisplayName("범위 내의 숫자가 입력되면, 객체가 정상적으로 생성된다.")
    void createBonusNumber_success() {
        //given
        int number = 7;

        //when & then
        assertDoesNotThrow(() -> new BonusNumber(number));
    }
}