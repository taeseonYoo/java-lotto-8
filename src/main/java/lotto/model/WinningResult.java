package lotto.model;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class WinningResult {
    private static final int COUNT_INIT = 0;
    private static final int COUNT_INCREMENT = 1;
    private static final int PERCENTAGE_MULTIPLIER = 100;

    private final Map<LottoRank, Integer> winningResult;

    public WinningResult() {
        winningResult = new EnumMap<>(LottoRank.class);
        initResult();
    }

    private void initResult() {
        Arrays.stream(LottoRank.values())
                .forEach(rank -> winningResult.put(rank, COUNT_INIT));
    }

    public void win(LottoRank rank) {
        winningResult.put(rank, winningResult.get(rank) + COUNT_INCREMENT);
    }

    public double calculateProfitRate(Money money) {
        long totalPrize = calculateTotalPrize();
        long totalSpent = money.getAmount();
        return (double) totalPrize / totalSpent * PERCENTAGE_MULTIPLIER;
    }

    private long calculateTotalPrize() {
        return winningResult.entrySet().stream()
                .mapToLong(rank -> (long) rank.getKey().getPrize() * rank.getValue())
                .sum();
    }

    public int getMatchCount(LottoRank rank) {
        return winningResult.get(rank);
    }
}
