package lotto.model;

import static lotto.constants.LottoRules.LOTTO_MAX_NUMBER;
import static lotto.constants.LottoRules.LOTTO_MIN_NUMBER;

import java.util.Collections;
import java.util.List;
import lotto.constants.LottoRules;

public class Lotto {
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
            throw new IllegalArgumentException("로또 번호는 중복되지 않은 6개로 구성되어야 합니다.");
        }
    }
    private void verifyNumberSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private void verifyNumberRange(List<Integer> numbers) {
        if (!numbers.stream().allMatch(num -> num >= LOTTO_MIN_NUMBER && num <= LOTTO_MAX_NUMBER)) {
            throw new IllegalArgumentException("로또 번호는 1~45사이의 숫자만 입력할 수 있습니다.");
        }
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
