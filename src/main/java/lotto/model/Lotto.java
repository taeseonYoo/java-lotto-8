package lotto.model;

import static lotto.common.constants.LottoRules.LOTTO_MAX_NUMBER;
import static lotto.common.constants.LottoRules.LOTTO_MIN_NUMBER;

import java.util.Collections;
import java.util.List;
import lotto.common.constants.LottoRules;

public class Lotto {
    private static final String LOTTO_NUMBER_DUPLICATE_EXCEPTION = "로또 번호는 중복되지 않은 숫자로 구성되어야 합니다.";
    private static final String LOTTO_COUNT_EXCEPTION = "로또 번호는 6개여야 합니다.";
    private static final String LOTTO_RANGE_EXCEPTION = "로또 번호는 1~45사이의 숫자만 입력할 수 있습니다.";
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        verifyNumberSize(numbers);
        verifyNumberRange(numbers);
        verifyNumberDuplication(numbers);
    }

    private void verifyNumberDuplication(List<Integer> numbers) {
        if (numbers.stream().distinct().count() != LottoRules.LOTTO_COUNT) {
            throw new IllegalArgumentException(LOTTO_NUMBER_DUPLICATE_EXCEPTION);
        }
    }
    private void verifyNumberSize(List<Integer> numbers) {
        if (numbers.size() != LottoRules.LOTTO_COUNT) {
            throw new IllegalArgumentException(LOTTO_COUNT_EXCEPTION);
        }
    }

    private void verifyNumberRange(List<Integer> numbers) {
        if (!numbers.stream().allMatch(num -> num >= LOTTO_MIN_NUMBER && num <= LOTTO_MAX_NUMBER)) {
            throw new IllegalArgumentException(LOTTO_RANGE_EXCEPTION);
        }
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    public boolean contains(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }
}
