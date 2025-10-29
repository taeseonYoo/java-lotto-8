package lotto.model;

import java.util.List;

public interface NumberGenerator {
    List<Integer> generate(int min, int max,int count);
}
