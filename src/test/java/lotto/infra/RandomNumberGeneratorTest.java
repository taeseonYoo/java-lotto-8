package lotto.infra;


import java.util.Comparator;
import java.util.List;
import lotto.common.constants.LottoRules;
import lotto.model.NumberGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomNumberGeneratorTest {
    @Test
    @DisplayName("랜덤 숫자 생성기는 정렬된 숫자를 반환한다.")
    void generate_sort() {
        //given
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        //when
        List<Integer> generatedNumbers = numberGenerator.generate(LottoRules.LOTTO_MIN_NUMBER,
                LottoRules.LOTTO_MAX_NUMBER,
                LottoRules.LOTTO_COUNT);
        //then
        Assertions.assertThat(generatedNumbers).isSortedAccordingTo(Comparator.naturalOrder());
    }
}