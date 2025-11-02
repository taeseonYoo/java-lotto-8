package lotto.model;

import static lotto.common.constants.LottoRules.LOTTO_COUNT;
import static lotto.common.constants.LottoRules.LOTTO_MAX_NUMBER;
import static lotto.common.constants.LottoRules.LOTTO_MIN_NUMBER;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoMachine {
    private static final String LOTTO_COUNT_EXCEPTION = "로또는 최소 1장 이상 발급되어야 합니다.";
    private static final int START_INDEX = 0;
    private static final int MINIMUM_LOTTO_GENERATE_COUNT = 1;
    private final List<Lotto> lottos;

    private LottoMachine(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static LottoMachine create(Money money, NumberGenerator numberGenerator) {
        validateCount(money);
        return new LottoMachine(generateLottos(money.calculateCount(), numberGenerator));
    }

    private static void validateCount(Money money) {
        if (money.calculateCount() < MINIMUM_LOTTO_GENERATE_COUNT) {
            throw new IllegalArgumentException(LOTTO_COUNT_EXCEPTION);
        }
    }

    private static List<Lotto> generateLottos(int count, NumberGenerator numberGenerator) {
        return IntStream.range(START_INDEX, count)
                .mapToObj(i -> new Lotto(numberGenerator.generate(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER, LOTTO_COUNT)))
                .collect(Collectors.toList());
    }

    public int getIssuedLottoCount() {
        return lottos.size();
    }

    public List<Lotto> getHistory() {
        return Collections.unmodifiableList(lottos);
    }
}
