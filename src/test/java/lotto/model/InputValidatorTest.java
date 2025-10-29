package lotto.model;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {
    @ParameterizedTest
    @ValueSource(strings = {"-1,2,3,4,5,6", "1,2,three,4,5,6",
            "1:2:3:4:5:6", ""})
    void validateWinningNumbers_fail(String inputWinningNumbers) {
        //when & then
        Assertions.assertThatThrownBy(
                        () -> InputValidator.validateWinningNumbers(inputWinningNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-5000", "thousand", "5,000", "5000₩", ""})
    void validateAmounts_fail(String inputAmount) {
        //when & then
        Assertions.assertThatThrownBy(
                        () -> InputValidator.validateAmount(inputAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"$", "seven", " "})
    void validateBonusNumber_fail(String inputBonusNumber) {
        //when & then
        Assertions.assertThatThrownBy(
                        () -> InputValidator.validateAmount(inputBonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,6"})
    void validateWinningNumbers_success(String inputWinningNumbers) {
        //when & then
        assertDoesNotThrow(
                () -> InputValidator.validateWinningNumbers(inputWinningNumbers));
    }

    @ParameterizedTest
    @ValueSource(strings = {"5000", "2147483647"})
    void validateAmounts_success(String inputAmount) {
        //when & then
        assertDoesNotThrow(
                () -> InputValidator.validateAmount(inputAmount));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1"})
    void validateBonusNumber_success(String inputBonusNumber) {
        //when & then
        assertDoesNotThrow(
                () -> InputValidator.validateAmount(inputBonusNumber));

    }
}