package lotto.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoMachine {
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final int LOTTO_COUNT = 6;
    private final List<Lotto> lottos;

    public LottoMachine(int count, NumberGenerator numberGenerator) {
        this.lottos = createLottos(count, numberGenerator);
    }

    private List<Lotto> createLottos(int count, NumberGenerator numberGenerator) {
        return IntStream.range(0, count)
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
