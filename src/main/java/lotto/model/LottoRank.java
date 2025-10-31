package lotto.model;


import java.util.Arrays;

public enum LottoRank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0);
    private final int matchCount;
    private final boolean matchBonus;
    private final int prize;

    LottoRank(int matchCount, boolean matchBonus, int prize) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.prize = prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return prize;
    }

    public boolean isMatchBonus() {
        return matchBonus;
    }

    public static LottoRank valueOf(int matchCount, boolean matchBonus) {
        if (matchCount == FIRST.getMatchCount()) return FIRST;
        if (matchBonus && matchCount == SECOND.getMatchCount()) return SECOND;
        if (!matchBonus && matchCount == THIRD.getMatchCount()) return THIRD;
        if (matchCount == FOURTH.getMatchCount()) return FOURTH;
        if (matchCount == FIFTH.getMatchCount()) return FIFTH;
        return NONE;
    }
}
