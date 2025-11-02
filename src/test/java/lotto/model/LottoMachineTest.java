package lotto.model;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import lotto.infra.RandomNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMachineTest {
    @Test
    @DisplayName("복권이 정상적으로 발급된다.")
    void create_success() {
        //given
        List<Integer> expectedResult = Arrays.asList(1, 2, 3, 4, 5, 6);
        Money money = new Money(1000);
        //when
        LottoMachine lottoMachine = LottoMachine.create(money, (min, max, count) -> expectedResult);
        //then
        assertThat(lottoMachine.getHistory().get(0).getNumbers()).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("복권은 중복된 번호 조합이 생성될 수 있다.")
    void create_duplicate() {
        //given
        List<Integer> expectedResult = Arrays.asList(1, 2, 3, 4, 5, 6);
        Money money = new Money(2000);
        //when
        LottoMachine lottoMachine = LottoMachine.create(money, (min, max, count) -> expectedResult);
        //then
        assertThat(lottoMachine.getHistory().get(0).getNumbers())
                .isEqualTo(lottoMachine.getHistory().get(1).getNumbers());
    }

    @Test
    @DisplayName("(구매 금액 / 복권 가격)만큼 복권이 발급된다.")
    void getIssuedLottoCount() {
        //given & when
        Money money = new Money(3000);
        LottoMachine lottoMachine = LottoMachine.create(money, new RandomNumberGenerator());
        //then
        assertThat(lottoMachine.getIssuedLottoCount()).isEqualTo(3);
    }
    
}