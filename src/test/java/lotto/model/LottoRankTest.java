package lotto.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoRankTest {
    @Test
    @DisplayName("6개가 일치하고, 보너스 번호가 맞지 않는다면 1등")
    void FIRST() {
        //when
        LottoRank lottoRank = LottoRank.valueOf(6, false);
        //then
        assertThat(lottoRank).isEqualTo(LottoRank.FIRST);
    }
    @Test
    @DisplayName("5개가 일치하고, 보너스 번호가 맞으면 2등")
    void SECOND() {
        //when
        LottoRank lottoRank = LottoRank.valueOf(5, true);
        //then
        assertThat(lottoRank).isEqualTo(LottoRank.SECOND);
    }
    @Test
    @DisplayName("5개가 일치하고, 보너스 번호가 맞지 않는다면 3등")
    void THIRD() {
        //when
        LottoRank lottoRank = LottoRank.valueOf(5, false);
        //then
        assertThat(lottoRank).isEqualTo(LottoRank.THIRD);
    }
    @Test
    @DisplayName("4개가 일치하고, 보너스 번호가 맞지 않는다면 4등")
    void FOURTH() {
        //when
        LottoRank lottoRank = LottoRank.valueOf(4, false);
        //then
        assertThat(lottoRank).isEqualTo(LottoRank.FOURTH);
    }
    @Test
    @DisplayName("3개가 일치하고, 보너스 번호가 맞지 않는다면 5등")
    void FIFTH() {
        //when
        LottoRank lottoRank = LottoRank.valueOf(3, false);
        //then
        assertThat(lottoRank).isEqualTo(LottoRank.FIFTH);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName("다른 조건은 NONE")
    void NONE(int matchCount) {
        //when
        LottoRank lottoRank = LottoRank.valueOf(matchCount, false);
        //then
        assertThat(lottoRank).isEqualTo(LottoRank.NONE);
    }
}