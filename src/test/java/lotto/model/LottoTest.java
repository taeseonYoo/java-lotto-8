package lotto.model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {
    @Test
    @DisplayName("로또 객체 생성에 성공한다.")
    void createLotto_success() {
        assertDoesNotThrow(
                () -> new Lotto(List.of(1, 2, 3, 4, 5, 6))
        );
    }

    @Test
    @DisplayName("로또 번호가 중복된다면, 예외가 발생한다.")
    void createLotto_fail_duplicate() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 번호가 6개 주어지지 않는다면, 예외가 발생한다.")
    void createLotto_fail_size() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 번호가 범위내에 존재하지 않는다면, 예외가 발생한다.")
    void createLotto_fail_range() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    @DisplayName("보너스 넘버를 포함하고 있다면, true를 반환한다.")
    void contains_success() {
        //given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 6;
        //when
        boolean contains = lotto.contains(bonusNumber);
        //then
        assertThat(contains).isTrue();
    }

    @Test
    @DisplayName("보너스 넘버를 포함하지 않는다면, false를 반환한다.")
    void contains_fail() {
        //given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;
        //when
        boolean contains = lotto.contains(bonusNumber);
        //then
        assertThat(contains).isFalse();
    }
}