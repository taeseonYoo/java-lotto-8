package lotto.infra;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import lotto.model.NumberGenerator;

public class RandomNumberGenerator implements NumberGenerator {
    @Override
    public List<Integer> generate(int min, int max, int count) {
        return Randoms.pickUniqueNumbersInRange(min, max, count).stream().sorted().toList();
    }
}
